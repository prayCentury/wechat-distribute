package com.pray.service;

import com.alibaba.fastjson.JSONObject;
import com.pray.model.AccountInfoModel;
import com.pray.model.GetOpenIdModel;

import java.util.List;

/**
 * Package Name: com.pray.service
 * Created by Liu xi on 2018/5/19.
 * Version: V1.0
 * Des:
 */
public interface AccountInfoService {
    JSONObject findByOpenId(GetOpenIdModel getOpenIdModel);


    JSONObject testService(String code);
}
