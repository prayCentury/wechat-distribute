package com.pray.controller;

import com.alibaba.fastjson.JSONObject;
import com.pray.model.AccountInfoModel;
import com.pray.model.GetOpenIdModel;
import com.pray.service.AccountInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
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
        logger.debug(getOpenIdModel.toString());
        JSONObject jsonObject = accountInfoService.findByOpenId(getOpenIdModel);
        logger.debug("返回结果：" + jsonObject.toJSONString());
        return jsonObject.toJSONString();
    }

    @RequestMapping(value = "/distribute",method = RequestMethod.GET)
    @ApiOperation(notes = "distribute", value = "distribute", tags = "已分配账户数")
    public String distributeAccount(Integer type , String secretStr ,String dateTime){
        String returnStr = "";
        JSONObject jsonObject = new JSONObject();
        if(type ==1){
            if(!secretStr.equals("yulutongsecretStr")){
                returnStr = "提示：育路通密钥有误！" ;
            }else {
                jsonObject.put("已分配账户总数",accountInfoService.distributeAccount());
                returnStr = jsonObject.toJSONString();
            }
        }else if(type ==2){
            if( StringUtils.isEmpty(secretStr) || !secretStr.equals("yulutongsecretStr")){
                returnStr = "提示：育路通密钥有误！" ;
            }else if(StringUtils.isEmpty(dateTime) || dateTime.length() != 8){
                returnStr = "提示：日期格式输入有误！如：20180808" ;
            }else {
                jsonObject.put(dateTime + "分配账户数:",accountInfoService.oneDayDistributeAccount(dateTime));

                returnStr = jsonObject.toJSONString();
            }
        }else {
            returnStr = "提示：输入查询类型有误！" ;
        }

        return returnStr;
    }

}
