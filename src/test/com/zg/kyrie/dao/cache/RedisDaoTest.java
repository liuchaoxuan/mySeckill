package com.zg.kyrie.dao.cache;

import com.zg.kyrie.dao.SeckillDao;
import com.zg.kyrie.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @Auther: kyrie
 * @Date: 2018/7/29 12:05
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class RedisDaoTest {

    @Autowired
    private RedisDao redisDao;

    @Autowired
    private SeckillDao seckillDao;
    @Test
    public void testRedis() {
        long seckillId = 1000;
        Seckill seckill = redisDao.getSeckill(seckillId);
        if (seckill != null){
            System.out.println(seckill);
        } else {
            seckill = seckillDao.findById(seckillId);
            String result = redisDao.putSeckill(seckill);
            System.out.println(result);
        }
    }
}
