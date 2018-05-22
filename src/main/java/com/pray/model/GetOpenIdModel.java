package com.pray.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Package Name: com.pray.model
 * Created by Liu xi on 2018/5/22.
 * Version: V1.0
 * Des:
 */
@ApiModel
public class GetOpenIdModel {

    @ApiModelProperty(value = "约定的加密串" , required = true)
    private String secretStr;

    @ApiModelProperty(value = "微信code" , required = true)
    private String weChatCode;

    @ApiModelProperty(value = "用户昵称" , required = true)
    private String nickName;

    public String getSecretStr() {
        return secretStr;
    }

    public void setSecretStr(String secretStr) {
        this.secretStr = secretStr;
    }

    public String getWeChatCode() {
        return weChatCode;
    }

    public void setWeChatCode(String weChatCode) {
        this.weChatCode = weChatCode;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
