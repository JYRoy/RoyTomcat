package myTomcat.test;

import java.io.IOException;

import myTomcat.MyRequest;
import myTomcat.MyResponse;
import myTomcat.MyServelet;

public class MyBlog extends MyServelet {
	@Override
	public void doGet(MyRequest myRequest, MyResponse myResponse) {
		try {
			myResponse.write("Hello, this is my blog");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void doPost(MyRequest myRequest, MyResponse myResponse) {
		try {
			myResponse.write("Hello, this is my blog");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
