package myTomcat;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;
import java.net.Socket;

/**
 * @author jyroy
 * Tomcat的处理流程：把URL对应处理的Servlet关系形成，解析HTTP协议，封装请求/响应对象，
 * 利用反射实例化具体的Servlet进行处理即可。
 */
public class MyTomcat {
	private Integer port = 8080;
	
	private Map<String, String> urlServletMapping = new HashMap<>();

	public MyTomcat(Integer port) {
		super();
		this.port = port;
	}
	
	public void start() {
		initServletMapping();
		
		while(true) {
			ServerSocket serverSocket = null;     //实例化一个 ServerSocket 对象，表示通过服务器上的端口通信
			try {
				serverSocket = new ServerSocket(port);   
				System.out.println("MyTomcat is starting...");
				while(true) {
					Socket socket = serverSocket.accept();     //服务器调用 ServerSocket 类的 accept() 方法，该方法将一直等待，直到客户端连接到服务器上给定的端口 
					InputStream inputStream = socket.getInputStream();
					OutputStream outputStream = socket.getOutputStream();
					
					MyRequest myRequest = new MyRequest(inputStream);
					MyResponse myResponse = new MyResponse(outputStream);
					
					dispatch(myRequest, myResponse);
					socket.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
//		}finally {
//			if(serverSocket != null) {
//				try {
//					serverSocket.close();
//				}catch(Exception e){
//					e.printStackTrace();
//				}
//			}
//		}
	}
	
	public void initServletMapping() {
		for(ServeletMapping servletMapping : ServeletMappingConfig.serveletMappingList) {
			urlServletMapping.put(servletMapping.getUrl(), servletMapping.getClazz());
		}
	}
	
	@SuppressWarnings("unchecked")
	public void dispatch(MyRequest myRequest, MyResponse myResponse) {
		String clazz = urlServletMapping.get(myRequest.getUrl());
		
		try {
			Class<MyServelet> myServletClass = (Class<MyServelet>)Class.forName(clazz); 
			MyServelet myServelet = myServletClass.newInstance();
			myServelet.service(myRequest, myResponse);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(InstantiationException e) {
			e.printStackTrace();
		}catch(IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
        MyTomcat myTomcat = new MyTomcat(8080);
        myTomcat.start();
    }
	
}
