package com.zg.kyrie.dao;

import com.zg.kyrie.entity.Seckill;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @Auther: kyrie
 * @Date: 2018/7/10 22:25
 */
public interface SeckillDao {

    int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);

    Seckill findById(@Param("seckillId") long seckillId);

    List<Seckill> findAll(@Param("offset") int offset, @Param("limit") int limit);

}
