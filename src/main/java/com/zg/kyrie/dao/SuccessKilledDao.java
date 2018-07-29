package com.zg.kyrie.dao;

import com.zg.kyrie.entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: kyrie
 * @Date: 2018/7/22 11:06
 */
public interface SuccessKilledDao {

    int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

    SuccessKilled queryByIdWithSuccessKill(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);
}
