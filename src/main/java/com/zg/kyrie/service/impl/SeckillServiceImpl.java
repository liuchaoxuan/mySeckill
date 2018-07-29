package com.zg.kyrie.service.impl;

import com.zg.kyrie.dao.SeckillDao;
import com.zg.kyrie.dao.SuccessKilledDao;
import com.zg.kyrie.dao.cache.RedisDao;
import com.zg.kyrie.dto.Exposer;
import com.zg.kyrie.dto.SeckillExecution;
import com.zg.kyrie.entity.Seckill;
import com.zg.kyrie.entity.SuccessKilled;
import com.zg.kyrie.enums.SeckillStatEnum;
import com.zg.kyrie.exception.RepeatKillException;
import com.zg.kyrie.exception.SeckillCloseException;
import com.zg.kyrie.exception.SeckillException;
import com.zg.kyrie.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * @Auther: kyrie
 * @Date: 2018/7/23 20:24
 */
@Service
public class SeckillServiceImpl implements SeckillService {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String salt = "fdsljafkdkshgalsdjgl;lasd%^&*(^&*()(*&";

    @Autowired
    private SeckillDao seckillDao;
    @Autowired
    private SuccessKilledDao successKilledDao;
    @Autowired
    private RedisDao redisDao;
    @Override
    public List<Seckill> getList() {
        return seckillDao.findAll(0, 4);
    }

    @Override
    public Seckill findById(long seckillId) {
        return seckillDao.findById(seckillId);
    }

    /**
     * * 优化：1. 在暴露秒杀url时，需要根据商品id查询商品是否存在，因为是大量用户在该时间点同时请求，
     *            所以并发量很大，而且数据又都是相同的，可以放入redis缓存进行优化；
     * *       2. 在执行秒杀时，先执行插入操作，再执行更新库存操作，因为后者会占用行级锁，其他用户就要等待
     * @param seckillId
     * @return
     */
    @Override
    public Exposer exportSeckillUrl(long seckillId) {
        //做redis缓存优化
        //超时的基础上维护一致性，TODO 其他方式
        Seckill seckill = redisDao.getSeckill(seckillId);
        if (seckill == null){
            seckill = seckillDao.findById(seckillId);
            if (seckill != null){
                redisDao.putSeckill(seckill);
            } else {
                return new Exposer(false, seckillId);
            }
        }
        String md5 = getMD5(seckillId);
        Date now_time = new Date();
        Date start_time = seckill.getStartTime();
        Date end_time = seckill.getEndTime();
        if (start_time.getTime() > now_time.getTime() || end_time.getTime() < now_time.getTime()) {
            return new Exposer(false,seckillId);
        }
        return new Exposer(true, seckillId, md5);
    }

    /**
     * 尽量使用注解，方便所有开发人员查看；
     * 事务方法尽可能少，不要穿插其他网络连接和http请求
     * 不是所有方法都需要事务，如果只是一条更新操作或者查询操作，不需要使用事务
     * @return
     */
    @Transactional
    @Override
    public SeckillExecution executeSeckill(long seckillId, String md5, long userPhone){

        if (md5 == null || !md5.equals(getMD5(seckillId))){
            throw new SeckillException("url数据被篡改了！");
        }
        //执行秒杀操作，减库存，插入购买明细
        Date nowTime = new Date();
        try{
            //返回插入的行数，正常为1，重复秒杀时因为id和phone联合主键唯一，返回0
            int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
            //检查是否有重复秒杀
            if (insertCount <= 0){
                throw new RepeatKillException("重复秒杀了！");
            } else {
                //如果正常，就执行减库存操作
                int reduceCount = seckillDao.reduceNumber(seckillId, nowTime);
                if (reduceCount <= 0){
                    throw new SeckillCloseException("秒杀已经结束，下次早点来！");
                } else {
                    //如果正常，则返回秒杀成功的订单信息并commit;
                    SuccessKilled successKilled = successKilledDao.queryByIdWithSuccessKill(seckillId, userPhone);
                    return new SeckillExecution(seckillId, SeckillStatEnum.SUCCESS, successKilled);
                }
            }
        }catch (RepeatKillException e1){
            throw e1;
        }catch (SeckillCloseException e2){
            throw e2;
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            throw new SeckillException("内部错误！", e);
        }

    }

    @Override
    public String getMD5(long seckillId){
        String base = seckillId + "/" + salt;
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }
}
