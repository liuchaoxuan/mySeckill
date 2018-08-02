package com.zg.kyrie.web;

import com.zg.kyrie.dto.Exposer;
import com.zg.kyrie.dto.SearchResult;
import com.zg.kyrie.dto.SeckillExecution;
import com.zg.kyrie.dto.SeckillResult;
import com.zg.kyrie.entity.Seckill;
import com.zg.kyrie.enums.SeckillStatEnum;
import com.zg.kyrie.exception.RepeatKillException;
import com.zg.kyrie.exception.SeckillCloseException;
import com.zg.kyrie.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * @Auther: kyrie
 * @Date: 2018/7/23 22:31
 */
@Controller
//@RequestMapping("/seckill") //模块/资源/细分
public class SeckillController {
    @Autowired
    private SeckillService seckillService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getList(Model model){
        List<Seckill> list = seckillService.getList();
        model.addAttribute("list", list);
        return "list";
    }

    @RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") Long seckillId, Model model){
        if (seckillId == null){
            return "redirect:/seckill/list";
        }
        Seckill seckill = seckillService.findById(seckillId);
        if (seckill == null){
            return "forward: /seckill/list";
        }
        model.addAttribute("seckill", seckill);
        return "detail";
    }

    @RequestMapping(value = "/{seckillId}/exposer",
            method = RequestMethod.GET,
            produces = {"application/json; charset=utf-8"})
    @ResponseBody
    public SeckillResult<Exposer> expose(@PathVariable("seckillId") long seckillId){
        SeckillResult<Exposer> result = null;
        Exposer exposer = seckillService.exportSeckillUrl(seckillId);
        result = new SeckillResult<>(true, exposer);
        return result;
    }

    @RequestMapping(value = "/{seckillId}/{md5}/execution",
            method = RequestMethod.POST,
            produces = {"application/json; charset=utf-8"})
    @ResponseBody
    public SeckillResult<SeckillExecution> execute(@PathVariable("seckillId") Long seckillId,
                                                   @PathVariable("md5") String md5,
                                                   @CookieValue(value = "userPhone") Long userPhone)
    {
        if (userPhone == null){
            return new SeckillResult<>(false, "未注册！");
        }
        try {
            SeckillExecution seckillExecution = seckillService.executeSeckill(seckillId, md5, userPhone);
            return new SeckillResult<>(true, seckillExecution);
        } catch (RepeatKillException e1) {
            SeckillExecution execution = new SeckillExecution(seckillId, SeckillStatEnum.REPEAT_KILL);
            return new SeckillResult<>(true, execution);
        } catch (SeckillCloseException e2){
            SeckillExecution execution = new SeckillExecution(seckillId, SeckillStatEnum.END);
            return new SeckillResult<>(true, execution);
        } catch (Exception e){
            SeckillExecution execution = new SeckillExecution(seckillId, SeckillStatEnum.INNER_ERROR);
            return new SeckillResult<>(true, execution);
        }


    }

    @RequestMapping(value = "/time/now", method = RequestMethod.GET)
    @ResponseBody
    public SeckillResult<Long> time(){
        Long time = new Date().getTime();
        return new SeckillResult<>(true, time);
    }

    @RequestMapping(value = "/searchList", method = RequestMethod.GET)
    @ResponseBody
    public HashSet getSearchList(String skeyword){
        System.out.println(skeyword);
        HashSet hashSet = new HashSet();
        hashSet.add("aaa");
        hashSet.add("bbb");
        hashSet.add("ccc");
//        SearchResult<HashSet> result = new SearchResult<>(true, hashSet);
        return hashSet;
    }
}
