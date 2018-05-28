package com.pray.dao;

import com.pray.model.AccountInfoModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

/**
 * Package Name: com.pray.dao
 * Created by Liu xi on 2018/5/19.
 * Version: V1.0
 * Des:
 */
@Mapper
@Component
public interface AccountInfoDao {

    /**
     * 判断数据库中是否在openid
     * @param openid
     * @return
     */
    @Select("select count(1) from taccount where openid = #{openid}")
    Integer existOpenId(@Param("openid") String openid);

    @Select("select min(id) from taccount t where isnull(t.isdistribute)")
    Integer selMinId();

    /**
     * 查询未分发的id最小的账号密码信息
     * @param openid
     * @return
     */
    @Select("select t.scusername,t.scpassword from taccount t " +
            "where t.id = #{id}")
    AccountInfoModel distributeInfo(@Param("openid") String openid ,@Param("id") Integer id);

    /**
     * 不存在 插入用户信息，将是否分发状态设置为1
     * @param openid
     * @return
     */
    @Update("update taccount t set t.isdistribute = 1,t.openid = #{openid},t.time = #{time},t.systime = SYSDATE(), " +
            " t.nickname = #{nickName} where t.id = #{id}")
    void insertInfo(@Param("openid") String openid,@Param("time") Long time,@Param("id") Integer id,@Param("nickName") String nickName);


    /**
     * 存在  直接返回原来分发账号信息
     * @param openid
     * @return
     */
    @Select("select t.scusername,t.scpassword from taccount t where openid = #{openid}")
    AccountInfoModel findByOpenId(@Param("openid") String openid);


    /**
     * 查询已分配账户数
     * @return
     */
    @Select("select COUNT(1) from taccount t where t.isdistribute ='1'")
    Integer distributeAccount();

    /**
     * 查询某天分配的账户数
     * @return
     */
    @Select("select COUNT(1) from taccount t where t.time = #{dateTime}")
    Integer oneDayDistributeAccount(String dateTime);
}
