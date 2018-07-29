package com.zg.kyrie.dao;

import com.zg.kyrie.entity.SuccessKilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Auther: kyrie
 * @Date: 2018/7/22 16:38
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {

    @Resource
    private SuccessKilledDao successKilledDao;

    @Test
    public void insertSuccessKilled() {
        long seckillId = 1000;
        long userPhone = 12345678901L;
        int result = successKilledDao.insertSuccessKilled(seckillId, userPhone);
        System.out.println(result);
    }

    @Test
    public void queryByIdWithSuccessKill() {
        long seckillId = 1000;
        long userPhone = 12345678901L;
        SuccessKilled successKilled = successKilledDao.queryByIdWithSuccessKill(seckillId, userPhone);
        System.out.println(successKilled);
    }
}