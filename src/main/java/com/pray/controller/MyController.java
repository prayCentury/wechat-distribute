package com.pray.controller;

import com.alibaba.fastjson.JSONObject;
import com.pray.model.AccountInfoModel;
import com.pray.model.GetOpenIdModel;
import com.pray.service.AccountInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

    private static Log logger = LogFactory.getLog(MyController.class);

    @Autowired
    private AccountInfoService accountInfoService;

    @RequestMapping(value = "/test2",method = RequestMethod.GET)
    public String getInfo232(String openid){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("scusername","11111111");
        jsonObject.put("scpassword","abcdef");
        return jsonObject.toJSONString();
    }

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String getInfo23(String wechatcode){
        logger.info("wechatcode:" + wechatcode);
        JSONObject jsonObject = new JSONObject();
        JSONObject retJson = accountInfoService.testService(wechatcode);
        Integer o_code = retJson.getInteger("code");
        if(o_code > 0){
            AccountInfoModel accountInfoModel = (AccountInfoModel)retJson.get("result");
            jsonObject.put("scusername",accountInfoModel.getScusername());
            jsonObject.put("scpassword",accountInfoModel.getScpassword());
        }else {
            jsonObject.put("scusername",o_code);
            jsonObject.put("scpassword","失败！");
        }
        return jsonObject.toJSONString();
    }


    @ApiOperation(notes = "getInfo", value = "getInfo", tags = "根据code获取账号密码信息")
    @RequestMapping(value = "/getInfo",method = RequestMethod.POST)
    public String getInfo(@ApiParam(name = "getOpenIdModel", value = "", required = true)
                              @RequestBody GetOpenIdModel getOpenIdModel){
        JSONObject jsonObject = accountInfoService.findByOpenId(getOpenIdModel);
        return jsonObject.toJSONString();
    }

}
