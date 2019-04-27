package myTomcat;

import java.io.IOException;
import java.io.OutputStream;

public class MyResponse {
	private OutputStream outputStream;
	
	public MyResponse(OutputStream outputStream) {
		this.outputStream = outputStream;
	}
	
	//将文本转换为字节流
	public void write(String content) throws IOException{
		StringBuffer httpResponse = new StringBuffer();
		httpResponse.append("HTTP/1.1 200 OK\n")
					.append("Content-Type:text/html\n")
					.append("\r\n")
					.append("<html><head><link rel=\"icon\" href=\"data:;base64,=\"></head><body>")
					.append(content)
					.append("</body></html>");
		outputStream.write(httpResponse.toString().getBytes());
		outputStream.close();
	}
	
}
