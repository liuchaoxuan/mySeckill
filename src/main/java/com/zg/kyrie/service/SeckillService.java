package com.zg.kyrie.service;

import com.zg.kyrie.dto.Exposer;
import com.zg.kyrie.dto.SeckillExecution;
import com.zg.kyrie.entity.Seckill;

import java.util.List;

/**
 * @Auther: kyrie
 * @Date: 2018/7/23 20:23
 */
public interface SeckillService {

    List<Seckill> getList();

    Seckill findById(long SeckillId);

    Exposer exportSeckillUrl(long seckillId);

    SeckillExecution executeSeckill(long seckillId, String md5, long userPhone);

    String getMD5(long seckillId);
}
