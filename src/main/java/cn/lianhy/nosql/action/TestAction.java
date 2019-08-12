package cn.lianhy.nosql.action;

import cn.lianhy.nosql.utils.RedisUtils;
import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "test")
public class TestAction {

    @Resource
    private RedisUtils redisUtils;

    @GetMapping(value = "setName")
    public String setName(String name){
        redisUtils.rpush("test",name);
        return "success";
    }

    @GetMapping(value = "getName")
    public String getName(){
        List<String> list=redisUtils.range("test",0,3);
        if(list!=null && list.size()>0){
            return JSON.toJSONString(list);
        }
        return "";
    }
}
