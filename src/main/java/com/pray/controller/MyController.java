package com.pray.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Package Name: com.pray.controller
 * Created by Liu xi on 2018/5/17.
 * Version: V1.0
 * Des:
 */
@RestController
public class MyController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String getInfo23(String openid,String openid2,String openid3){

        return openid;
    }

    @ApiOperation(value="执行sql", notes="查询sql数据", tags = "查询sql数据")
    @RequestMapping(value = "/getInfo2",method = RequestMethod.GET)
    public String getInfo(){
        List list = jdbcTemplate.queryForList("SELECT NAME FROM tuser");
        return list.toString();
    }


    @ApiOperation(value="调用存储过程", notes="测试调用存储过程", tags = "测试调用存储过程")
    @RequestMapping(value = "/testProduce",method = RequestMethod.GET)
    public String testProduce(){
        List list = jdbcTemplate.queryForList("call pr_add(12,34)");
        return list.toString();
    }

}
