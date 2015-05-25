package com.joymove.util.zhifubao;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class ZhifubaoUtils {

	//商户PID
	public static final String PARTNER = "2088911128852234";
	//商户收款账号
	public static final String SELLER = "2088911128852234";
	//商户私钥，pkcs8格式
	public static final String RSA_PRIVATE =  "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAMBl/EFT/rFrtqG4" + 
			"zO4jIr1etnbYHDvIBD951tbHDJqurrmsgrjk4yacXH9ihzvhgwUTTg/B9fH4pU/S"   +
			"FHpdFtcbJ2VS69a9BKO9O9AvPCJQw9TSeDjKrwUUsnTcGL8Efagas21vuvOeMEyG"   +
			"L9Cz1y/2uw0bMvsXyWi31BTITWOzAgMBAAECgYA9WEc2DTIIH+/7YJD23P0WpDxJ"   +
			"aF6YMQ4prw9xK8Y5C7lqOjbz/QbwKKrVcvTrgtvjomQhYhapMD6FVNZyrXu6TMbV"   +
			"NwbTsRS5Ezs1bAG9VDPs6mHNduMQZWAbpHq3ZifDBYc+QXZvROu0MjTrWj2M24LM"   +
			"obabQZqMTfwGEFI2AQJBAN9zHIJLiFcmZYkKzne0adkZNjB40p8NeKy0EWFNls6t"   +
			"CkRkeJ6YEf1lX/fmKiL1abaP99zl5YqJtyM4Q1pellkCQQDcbOjKy0yJcL2Es9dJ"   +
			"3j8PLF7AZW21bO0iywTxToo1buMQmJzk4GwS79wdqVZnm+eopnmMqzXLUMXcpDKA"   +
			"2GDrAkBCNL8rs+mPuOa4tiT6vs/l7Vxq/EGbssggk7HanLykPA0guowqoXAjHlhT"   +
			"ATVW6lQKLZoJL2zVoXxLyS3bps7xAkA5gOwGI5026cN3QBd0/NbElZWuaYeCYgVH"   +
			"I2GJtiyWHfR8muOrTeTD1/E7h7az1tdLhwqEqRGildLYabmHJWu7AkA0QAuHGzbn"   +
			"mr6cMa1KzllkrE/Pt+H8fUrnpJVPBgvesZRX7CpHEyutdgOWEMyKGjXNyld7ezd0"   +
			"jxxnj5s82QvW";
	//支付宝公钥
	public static final String RSA_ALI_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

	
	public static String RandomOrderId(int length) {  
	    String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";  
	    Random random = new Random();  
	    StringBuffer buf = new StringBuffer();  
	    for (int i = 0; i < length; i++) {  
	        int num = random.nextInt(62);  
	        buf.append(str.charAt(num));  
	    }  
	    return buf.toString();  
	}  
	

    public static String getPayInfo(String subject,String body,Double balance,String orderId){
    	// 订单
    			String orderInfo = getOrderInfo(subject, body, String.valueOf(balance),orderId,false);

    			// 对订单做RSA 签名
    			String sign = sign(orderInfo);
    			try {
    				// 仅需对sign 做URL编码
    				sign = URLEncoder.encode(sign, "UTF-8");
    			} catch (UnsupportedEncodingException e) {
    				e.printStackTrace();
    			}

    			// 完整的符合支付宝参数规范的订单信息
    			final String payInfo = orderInfo + "&sign=\"" + sign + "\"&"
    					+ getSignType(false);
    			return payInfo;
    }
	
	public static void main(String[] args) {
		// 订单
		String orderInfo = getOrderInfo("aaa", "bbb", "0.01","aaaaa",false);

		// 对订单做RSA 签名
		String sign = sign(orderInfo);
		try {
			// 仅需对sign 做URL编码
			sign = URLEncoder.encode(sign, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		// 完整的符合支付宝参数规范的订单信息
		final String payInfo = getOrderInfo("aaa", "bbb", "0.01","aaaaa",true) + "&sign=\\\"" + sign + "\\\"&"
				+ getSignType(true);
		System.out.println(payInfo);
	}
	/**
	 * create the order info. 创建订单信息
	 * 
	 */
	public static String getOrderInfo(String subject, String body, String price,String orderId,boolean quote) {
		// 签约合作者身份ID
		String quote_char = null;
		if(quote){
			quote_char = "\\";
			
		} else {
			quote_char = "";
		}
		String orderInfo = "partner=" + quote_char + "\"" + PARTNER + quote_char+"\"";

		// 签约卖家支付宝账号
		orderInfo += "&seller_id=" + quote_char + "\"" + SELLER + quote_char + "\"";

		// 商户网站唯一订单号
		orderInfo += "&out_trade_no=" + quote_char + "\"" + orderId + quote_char +"\"";

		// 商品名称
		orderInfo += "&subject=" + quote_char + "\"" + subject + quote_char + "\"";

		// 商品详情
		orderInfo += "&body=" + quote_char + "\"" + body + quote_char + "\"";

		// 商品金额
		orderInfo += "&total_fee=" + quote_char + "\"" + price + quote_char + "\"";

		// 服务器异步通知页面路径
		orderInfo += "&notify_url=" + quote_char + "\"" + "http://123.57.151.176:8082/joymove/zhifubao/notify.c"
				+ quote_char + "\"";

		// 服务接口名称， 固定值
		orderInfo += "&service=" + quote_char + "\"mobile.securitypay.pay" + quote_char + "\"";

		// 支付类型， 固定值
		orderInfo += "&payment_type=" + quote_char + "\"1" + quote_char +"\"";

		// 参数编码， 固定值
		orderInfo += "&_input_charset="+ quote_char +"\"utf-8"+ quote_char +"\"";

		// 设置未付款交易的超时时间
		// 默认30分钟，一旦超时，该笔交易就会自动被关闭。
		// 取值范围：1m～15d。
		// m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
		// 该参数数值不接受小数点，如1.5h，可转换为90m。
		orderInfo += "&it_b_pay="+ quote_char +"\"30m"+ quote_char +"\"";

		// extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
		// orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

		// 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
		orderInfo += "&return_url="+ quote_char +"\"http://123.57.151.176:8082/joymove/zhifubao/show.c"+ quote_char +"\"";

		// 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
		// orderInfo += "&paymethod=\"expressGateway\"";

		return orderInfo;
	}

	

	/**
	 * sign the order info. 对订单信息进行签名
	 * 
	 * @param content
	 *            待签名订单信息
	 */
	public static String sign(String content) {
		return SignUtils.sign(content, RSA_PRIVATE);
	}

	/**
	 * get the sign type we use. 获取签名方式
	 * 
	 */
	public static String getSignType(boolean quote) {
		if(quote) {
		return "sign_type=\\\"RSA\\\"";
		} else {
			return "sign_type=\"RSA\"";
		}
	}

}
