package com.cjk.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cjk.common.pojo.TextMessage;
import com.cjk.common.util.MessageUtils;
import com.cjk.common.vo.FanVO;

import net.sf.json.JSONObject;

@Controller
public class AutoRespMsgController {

	private Logger LOG = LoggerFactory.getLogger(VerifyController.class);

	@RequestMapping(value = "/autoresp", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
	@ResponseBody
	public void bcfpWxServerBusiness(HttpServletRequest request, HttpServletResponse response) throws IOException {
		LOG.debug("bcfpWxServerBusiness start call...");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter out = response.getWriter();
		String openid = request.getParameter("openid");
		LOG.debug("bcfpWxServerBusiness openid=" + openid);

		LOG.debug("bcfpWxServerBusiness start get user info...");
		FanVO fans = new FanVO();
		// UserReader.getFans(openid);
		if (fans != null)
			LOG.debug("bcfpWxServerBusiness get user info success!");
		else
			LOG.debug("bcfpWxServerBusiness get user info fail!");

		try {
			try {
				Map<String, String> map = MessageUtils.xmlToMap(request);
				String ToUserName = map.get("ToUserName");
				String FromUserName = map.get("FromUserName");
				String MsgType = map.get("MsgType");
				String content = map.get("Content");
				String message = null;

				JSONObject jsonObj = JSONObject.fromObject(map);
				LOG.debug("parameter:{}", jsonObj.toString());

				if ("text".equals(MsgType)) {
					/*
					 * LOG.debug("start generate message..."); TextMessage text
					 * = new TextMessage(); text.setFromUserName(ToUserName);
					 * text.setToUserName(FromUserName);
					 * text.setMsgType("text"); SimpleDateFormat ds = new
					 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					 * text.setCreateTime(ds.format(new Date()));
					 * text.setContent("消息内容：" + content + sExt); message =
					 * MessageUtil.textMessageToXML(text);
					 */
					// logger.debug("generate message end:" + message);
				} else {
					if ("image".equals(MsgType)) {
						/*
						 * LOG.debug("start generate message..."); TextMessage
						 * text = new TextMessage();
						 * text.setFromUserName(ToUserName);
						 * text.setToUserName(FromUserName);
						 * text.setMsgType("text"); SimpleDateFormat ds = new
						 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						 * text.setCreateTime(ds.format(new Date()));
						 * text.setContent("不要发送图片好不好！" + sExt); message =
						 * MessageUtil.textMessageToXML(text);
						 */
						// logger.debug("generate message end:" + message);
					} else if ("event".equals(MsgType)) {
						// 事件
						String eventType = map.get("Event");
						LOG.debug("doPost event=" + eventType);
						if ("subscribe".equals(eventType)) {
							TextMessage text = new TextMessage();
							text.setFromUserName(ToUserName);
							text.setToUserName(FromUserName);
							text.setMsgType("text");
							SimpleDateFormat ds = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							text.setCreateTime(ds.format(new Date()));
							text.setContent("欢迎" + (fans == null ? "" : fans.getNickName()) + "关注公众号！");
							message = MessageUtils.textMessageToXML(text);
							try {
								LOG.debug("start addFuns(openid={})...", fans.getOpenId());
								// LOG.debug("fansService={}", (fansService ==
								// null ? "null" : "not null"));
								// fansService.addFans(fans);
								LOG.debug("addFuns finish!");
							} catch (Exception e) {
								LOG.error("addFans error:{}!", e);
							}
							LOG.debug("generate message end:" + message);
						} else if ("unsubscribe".equals(eventType)) {
							TextMessage text = new TextMessage();
							text.setFromUserName(ToUserName);
							text.setToUserName(FromUserName);
							text.setMsgType("text");
							SimpleDateFormat ds = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							text.setCreateTime(ds.format(new Date()));
							text.setContent("谢谢" + (fans == null ? "" : fans.getNickName()) + "惠顾本公众号！");
							message = MessageUtils.textMessageToXML(text);
							if (fans != null) {
								try {
									LOG.debug("start removeFans...");
									// fansService.removeFans(fans.getUnionId());
									LOG.debug("removeFans finish!");
								} catch (Exception e) {
									LOG.error("removeFans error:{}", e);
								}
							}
						}
					}
					LOG.debug("is not text message!");
				}
				// logger.debug("doPost message=" + (message != null ?
				// message:""));
				out.print(message);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} finally {
			out.flush();
			out.close();
			LOG.debug("doPost call finish!");
		}
	}
}
