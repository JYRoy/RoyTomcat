package myTomcat;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author jyroy
 *
 */
public class MyRequest {
	
	//����·��
	private String url;
	//���󷽷�
	private String method;
	
	//��ȡ�����ֽ�������װ���ַ�����ʽ����������
	public MyRequest(InputStream inputStream) throws IOException{
		String httpRequest = "";
		
		byte[] httpRequestBytes = new byte[1024];
		
		int length = 0;
		
		if((length = inputStream.read(httpRequestBytes)) > 0) {
			httpRequest = new String(httpRequestBytes, 0, length);
		}
		//HTTP����Э��:���е���������Ϊ�����󷽷�������·���Լ�����Э�鼰���Ӧ�汾��
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
