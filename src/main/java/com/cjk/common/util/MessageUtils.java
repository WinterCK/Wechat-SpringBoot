package com.cjk.common.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.cjk.common.pojo.TextMessage;
import com.thoughtworks.xstream.XStream;
import net.sf.json.JSONObject;

public class MessageUtils {

	private static final Logger logger = LoggerFactory.getLogger(MessageUtils.class);

	public static String inputstream2Str(InputStream ins) {
		try {
			return IOUtils.toString(ins, "UTF-8");
		} catch (Exception e) {
			return "";
		}
	}

	public static Map<String, String> xmlToMap(String cfgtype, HttpServletRequest request)
			throws IOException, DocumentException {
		logger.debug("xmlToMap start call...");
		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		InputStream ins = request.getInputStream();

		String signature = request.getParameter("msg_signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		logger.debug("signature={}", signature);
		logger.debug("timestamp={}", timestamp);
		logger.debug("nonce={}", nonce);
		String sPacket = inputstream2Str(ins);
		logger.debug("sPacket={}", sPacket);

		// LOG.debug("start decrypt message...");
		//// String msg = MsgEncryptTool.msgDecrypt(sPacket, signature,
		// timestamp, nonce);
		// LOG.debug("decrypt message finish:{}", msg);

		ByteArrayInputStream bis = new ByteArrayInputStream(sPacket.getBytes());
		logger.debug("start convert message to map...");
		Document doc = reader.read(bis);
		Element root = doc.getRootElement();
		@SuppressWarnings("unchecked")
		List<Element> list = root.elements();
		for (Element e : list) {
			map.put(e.getName(), e.getText());
		}
		logger.debug("convert message to map finish:{}", JSONObject.fromObject(map).toString());
		return map;
	}

	public static Map<String, String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException {
		return xmlToMap("1", request);
	}

	public static String textMessageToXML(String cfgtype, TextMessage textMessage) {
		logger.debug("textMessageToXML start call...");
		XStream xstream = new XStream();
		xstream.alias("xml", textMessage.getClass());
		String sXml = xstream.toXML(textMessage);
		logger.debug("XML:{}", sXml);

		// LOG.debug("start encrypt message...");
		// sXml = MsgEncryptTool.msgEncrypt(cfgtype, sXml);
		// LOG.debug("encrypt message finish:{}", sXml);

		return sXml;
	}

	public static String textMessageToXML(TextMessage textMessage) {
		return textMessageToXML("1", textMessage);
	}
}
