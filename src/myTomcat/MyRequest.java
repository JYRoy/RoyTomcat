package myTomcat;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author jyroy
 *
 */
public class MyRequest {
	
	//请求路径
	private String url;
	//请求方法
	private String method;
	
	//读取输入字节流，封装成字符串格式的请求内容
	public MyRequest(InputStream inputStream) throws IOException{
		String httpRequest = "";
		
		byte[] httpRequestBytes = new byte[1024];
		
		int length = 0;
		
		if((length = inputStream.read(httpRequestBytes)) > 0) {
			httpRequest = new String(httpRequestBytes, 0, length);
		}
		//HTTP请求协议:首行的内容依次为：请求方法、请求路径以及请求协议及其对应版本号
		//                      GET    /favicon.ico  HTTP/1.1
		String httpHead = httpRequest.split("\n")[0];
		System.out.println(httpHead);
		method = httpHead.split("\\s")[0];
		url = httpHead.split("\\s")[1];
		System.out.println(this.toString());
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	@Override
	public String toString() {
		return "MyRequest [url=" + url + ", method=" + method + "]";
	}
	
	
}
