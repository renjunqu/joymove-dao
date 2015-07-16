package  com.joymove.util.BaiduOCR;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

/***
 * Http请求客户端
 * @author wx
 *
 */
public class HttpUtil {


	public static String get(String url) throws Exception {
		String result = "{}";
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		HttpResponse httpResponse;

		httpResponse = httpClient.execute(httpGet);
		if(httpResponse.getStatusLine().getStatusCode() == 200) {
			HttpEntity entity = httpResponse.getEntity();
			result = EntityUtils.toString(entity);
		}
		return result;
	}


	public static String post(String url, Map<String, String> params) throws Exception{
		String result = "{}";
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();

		Set<String> keySet = params.keySet();
		for (String key : keySet) {
			nvps.add(new BasicNameValuePair(key, params.get(key)));
		}

		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		HttpResponse httpResponse = httpClient.execute(httpPost);
		if(httpResponse.getStatusLine().getStatusCode() == 200) {
			HttpEntity entity = httpResponse.getEntity();
			result = EntityUtils.toString(entity);
		}
		return result;
	}

	public static String post(String url,Map<String,String> headers, Map<String, String> params) throws Exception{
		String result = "{}";
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();

		Set<String> headerKeys = headers.keySet();
		for(String key : headerKeys){
			httpPost.addHeader(key, headers.get(key));
		}

		Set<String> keySet = params.keySet();
		for (String key : keySet) {
			nvps.add(new BasicNameValuePair(key, params.get(key)));
		}

		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		HttpResponse httpResponse = httpClient.execute(httpPost);
		if(httpResponse.getStatusLine().getStatusCode() == 200) {
			HttpEntity entity = httpResponse.getEntity();
			result = EntityUtils.toString(entity);
		}
		return result;
	}

	public static String uploadFile(String url,Map<String,String> headers, Map<String, Object> params) throws Exception{
		String result = "{}";
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);

		Set<String> headerKeys = headers.keySet();
		for(String key : headerKeys){
			httpPost.addHeader(key, headers.get(key));
		}

		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

		Set<String> keySet = params.keySet();
		for (String key : keySet) {
			Object val = params.get(key);
			if(val instanceof File){
				builder.addPart(key,new FileBody((File)val));
			}else{
				builder.addTextBody(key, String.valueOf(val),ContentType.DEFAULT_BINARY);
			}
		}

		httpPost.setEntity(builder.build());
		HttpResponse httpResponse = httpClient.execute(httpPost);
		if(httpResponse.getStatusLine().getStatusCode() == 200) {
			HttpEntity entity = httpResponse.getEntity();
			result = EntityUtils.toString(entity);
		}
		return result;
	}


}
