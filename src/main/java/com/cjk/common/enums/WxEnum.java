package com.cjk.common.enums;

public enum WxEnum {
	/** wechat 自定义token*/
	WX_TOKEN("542397apptoken");

    private String code;

    private WxEnum(String code){
        this.code = code;
    };

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
