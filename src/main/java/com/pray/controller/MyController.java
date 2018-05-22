package com.pray.controller;

import com.alibaba.fastjson.JSONObject;
import com.pray.model.AccountInfoModel;
import com.pray.model.GetOpenIdModel;
import com.pray.service.AccountInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;
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
@Api
@RestController
public class MyController {

    @Autowired
    private AccountInfoService accountInfoService;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String getInfo23(String openid,String openid2,String openid3){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("scusername","1234567");
        jsonObject.put("scpassword","abcdef");
        return jsonObject.toJSONString();
    }


    @ApiOperation(notes = "getOpenIdModel", value = "getOpenIdModel", tags = "getOpenIdModel")
    @RequestMapping(value = "/getInfo",method = RequestMethod.POST)
    public String getInfo(@ApiParam(name = "getOpenIdModel", value = "", required = true)
                              @RequestBody GetOpenIdModel getOpenIdModel){
        JSONObject jsonObject = accountInfoService.findByOpenId(getOpenIdModel);
        return jsonObject.toJSONString();
    }

}
