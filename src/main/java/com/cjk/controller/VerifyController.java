package com.cjk.controller;

import com.cjk.common.exception.AesException;
import com.cjk.common.util.WxUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
//@RequestMapping("/")
public class VerifyController {

    @Value("${server.port}")
    private String port;

    @RequestMapping("/hello")
    @ResponseBody
    public String satHello(){
        return "turn to spring boot is success, it's from " + port;

    }
    @GetMapping("/testWechat")
    public String testWechat(HttpServletRequest request, HttpServletResponse resp) throws AesException {
        String msgSignature = request.getParameter("signature");
        String msgTimestamp = request.getParameter("timestamp");
        String msgNonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        System.out.println("signature = " + msgSignature + ", and timestamp = " + msgTimestamp + ", and nonce = " +
                msgNonce + ", echostr = " + echostr);
        if (WxUtils.verifyUrl(msgSignature, msgTimestamp, msgNonce)) {
            return echostr;
        }
        return null;
    }


    @RequestMapping("/index")
    public String index() {
        return "index";
    }
}
