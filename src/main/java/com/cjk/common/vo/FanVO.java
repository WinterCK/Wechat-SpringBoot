package com.cjk.common.vo;

import java.io.Serializable;
import java.util.Date;

public class FanVO implements Serializable {

	private String openId;

    private String unionId;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 性别 1- 男，2-女
     */
    private String sex;

    /**
     * 语言
     */
    private String language;

    /**
     * 城市
     */
    private String city;

    /**
     * 省
     */
    private String provice;

    /**
     * 国家
     */
    private String country;

    /**
     * 头像
     */
    private String headImgUrl;

    /**
     * 是否关注，1- 已关注；2- 未关注
     */
    private String subscribe;

    /**
     * 备注
     */
    private String remark;

    /**
     * 分组ID
     */
    private Integer groupId;

    /**
     * 关注的渠道来源
     */
    private Integer subscribeScene;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 关注时间
     */
    private Date subscribeTime;

    private static final long serialVersionUID = 1L;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvice() {
        return provice;
    }

    public void setProvice(String provice) {
        this.provice = provice;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public String getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(String subscribe) {
        this.subscribe = subscribe;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getSubscribeScene() {
        return subscribeScene;
    }

    public void setSubscribeScene(Integer subscribeScene) {
        this.subscribeScene = subscribeScene;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(Date subscribeTime) {
        this.subscribeTime = subscribeTime;
    }
}
