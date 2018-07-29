package com.zg.kyrie.service.impl;

import com.zg.kyrie.dto.Exposer;
import com.zg.kyrie.dto.SeckillExecution;
import com.zg.kyrie.entity.Seckill;
import com.zg.kyrie.service.SeckillService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Auther: kyrie
 * @Date: 2018/7/23 21:11
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"})
public class SeckillServiceImplTest {

    @Autowired
    private SeckillService seckillService;
    @Test
    public void getList() {
        List<Seckill> list = seckillService.getList();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    @Test
    public void findById() {
        Seckill seckill = seckillService.findById(1000);

        System.out.println(seckill);
    }

    @Test
    public void exportUrl() {
        long seckillId = 1001;
        Exposer exposer = seckillService.exportSeckillUrl(seckillId);
        System.out.println(exposer.getMd5());
    }

    @Test
    public void execute(){
        Long seckillId = 1000L;
        Long userPhone = 12345678901L;
        String md5 = "e5a60a592c04bf46d811526f6f6ffb28";
        SeckillExecution seckillExecution = seckillService.executeSeckill(seckillId, md5, userPhone);
        System.out.println(seckillExecution.getState());
    }

    @Test
    public void getMd5(){
        long seckillId = 1000;
        String md5 = seckillService.getMD5(seckillId);
        System.out.println(md5);
    }
}