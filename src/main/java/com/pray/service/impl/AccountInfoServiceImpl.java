package com.pray.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.pray.dao.AccountInfoDao;
import com.pray.model.AccountInfoModel;
import com.pray.model.Constant;
import com.pray.model.GetOpenIdModel;
import com.pray.model.Result;
import com.pray.service.AccountInfoService;
import com.pray.utils.WXAppletUserInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Package Name: com.pray.service.impl
 * Created by Liu xi on 2018/5/19.
 * Version: V1.0
 * Des:
 */
@Service
public class AccountInfoServiceImpl implements AccountInfoService {

    final static String secretStr = "123";

    @Autowired
    private AccountInfoDao accountInfoDao;

    @Override
    public JSONObject findByOpenId(GetOpenIdModel getOpenIdModel) {
        String weChatCode = getOpenIdModel.getWeChatCode();
        String nickName = getOpenIdModel.getNickName();
        JSONObject jsonObject = new JSONObject();
        //首先判断是对应小程序发过来的请求
        String isTrueSecretStr = getOpenIdModel.getSecretStr();

        if(StringUtils.isEmpty(isTrueSecretStr) || !isTrueSecretStr.equals(secretStr)){
            jsonObject.put("code",Constant.ERROR_CODE);
            jsonObject.put("note","加密串为空！");
        }else if(StringUtils.isEmpty(weChatCode)){
            jsonObject.put("code",Constant.ERROR_CODE);
            jsonObject.put("note","微信code不能为空！");
        }else {
            //根据code 获取 openId
            JSONObject openIdJson = WXAppletUserInfo.getSessionKeyOropenid(weChatCode);
            String openId = openIdJson.getString("openid");
            String session_key = openIdJson.getString("session_key");
            if(StringUtils.isEmpty(openId) && openIdJson.getString("errcode").equals("40163")){
                jsonObject.put("code",Constant.ERROR_CODE);
                jsonObject.put("note","重复的wechatcode请求！");
            }else {
                AccountInfoModel accountInfoModel = new AccountInfoModel();
                //1.判断数据库中是否存在openid
                try {
                    Integer isExistOpenId = accountInfoDao.existOpenId(openId);
                    //不存在分配新的账号密码
                    if(isExistOpenId == 0){
                        Integer minId = accountInfoDao.selMinId();
                        //(1)分发账号密码
                        accountInfoModel = accountInfoDao.distributeInfo(openId,minId);
                        //(2)更新该次分发的账号密码状态、openid 、时间
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                        accountInfoDao.insertInfo(openId,Long.valueOf(formatter.format(new Date())),minId,nickName);
                    }else {//存在查询原分配的账号密码
                        accountInfoModel = accountInfoDao.findByOpenId(openId);
                    }
                    jsonObject.put("code",Constant.SUCCESS_CODE);
                    jsonObject.put("note",Constant.SUCCESS_NOTE);
                    jsonObject.put("result",accountInfoModel);
                }catch (Exception e){
                    jsonObject.put("code",Constant.ERROR_CODE);
                    jsonObject.put("note",e.getMessage());
                    e.printStackTrace();
                }
            }

        }
        return jsonObject;
    }
}
