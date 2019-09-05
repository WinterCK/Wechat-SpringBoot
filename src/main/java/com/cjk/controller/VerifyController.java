package com.cjk.controller;

import com.cjk.common.util.WxUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
//@RequestMapping("/")
public class VerifyController {

    private Logger logger = LoggerFactory.getLogger(VerifyController.class);

    @Value("${server.port}")
    private String port;

    @RequestMapping("/hello")
    @ResponseBody
    public String satHello(){
        return "turn to spring boot is success, it's from " + port;

    }


    @RequestMapping(value = "/wxServer", method= RequestMethod.GET, produces="text/html; charset=UTF-8")
    @ResponseBody
    public String bcfpWxServerValid(HttpServletRequest request) {
        logger.debug("WxServerValid start call...");
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        logger.debug("signature={}", signature);
        logger.debug("timestamp={}", timestamp);
        logger.debug("nonce={}", nonce);
        logger.debug("echostr={}", echostr);

        if (signature == null || timestamp == null || nonce == null) {
            return "server";
        }
        else {
            if (WxUtils.checkSignature(signature, timestamp, nonce))
            {
                logger.debug("valid!");
                return echostr;
            }
            else
                return "";
        }

    }
    @RequestMapping("/index")
    public String index() {
        return "index";
    }
}
