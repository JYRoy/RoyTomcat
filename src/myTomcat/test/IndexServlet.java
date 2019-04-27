package myTomcat.test;

import java.io.IOException;

import myTomcat.MyRequest;
import myTomcat.MyResponse;
import myTomcat.MyServelet;

public class IndexServlet extends MyServelet {
	@Override
	public void doGet(MyRequest myRequest, MyResponse myResponse) {
		try {
			myResponse.write("Hello, myTomcat");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void doPost(MyRequest myRequest, MyResponse myResponse) {
		try {
			myResponse.write("Hello, myTomcat");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
