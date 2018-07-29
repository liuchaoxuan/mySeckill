package com.zg.kyrie.dao;

import com.zg.kyrie.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.sql.SQLOutput;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Auther: kyrie
 * @Date: 2018/7/21 18:32
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {

    @Resource
    private SeckillDao seckillDao;
    @Test
    public void reduceNumber() {
        long seckillId = 1000;
        Date killTime = new Date();
        int result = seckillDao.reduceNumber(seckillId, killTime);
        System.out.println(result);
    }

    @Test
    public void findById() {
        long seckillId = 1000;
        Seckill seckill = seckillDao.findById(seckillId);
        System.out.println(seckill);
    }

    @Test
    public void findAll() {
        int offset = 0;
        int limit = 4;
        List<Seckill> list = seckillDao.findAll(offset, limit);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}