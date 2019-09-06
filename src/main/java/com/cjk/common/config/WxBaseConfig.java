package com.cjk.common.config;

import org.springframework.beans.factory.annotation.Value;

public class WxBaseConfig {
	
	@Value("wx.appid")
	private String wx_appid;
	
	@Value("wx.secret")
	private String wx_app_secret;
	
	public String getWxAppid() {
		return wx_appid;
	}

	public String getWxSecret() {
		return wx_app_secret;
	}
	
	public WxBaseConfig getInstance() {
		return WxBaseConfigHolder.UNIQUEINSTANCE;
	}
	
	/**
	 * 内部静态类实现单例模式
	 * @author chenjk
	 */
	private static class WxBaseConfigHolder {
		static final WxBaseConfig UNIQUEINSTANCE = new WxBaseConfig();
		private WxBaseConfigHolder(){}
	}
}
