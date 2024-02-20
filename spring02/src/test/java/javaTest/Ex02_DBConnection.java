package javaTest;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class Ex02_DBConnection {
	
	// static, return  값이 있는 경우 (@Test 불허) 
	//	=> Test 메서드를 작성해서 Test 
	public static Connection getConnection() {
		try {
			//Class란 클래스 이름의 클래스 = java 내부에 있는 자체 클래스
			Class.forName("com.mysql.cj.jdbc.Driver"); //생성자를 생성하지 않고, 하는 방법 중 하나
													   //원래 생성자는 new 하여서 작성해야함 주의%%
//			Connection cn = new Connection(); -> 작성 불가능함. 
			String url="jdbc:mysql://localhost:3306/mydb?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true"; // 길을 알려주는 변수임. ->getConnection 함수에서 작성함으로써, 연결을 도와줌
		      // => allowPublicKeyRetrieval=true : local DB open 하지 않아도 connection 허용
	         // => localhost -> 동일값(ip주소) @127.0.0.1
			Connection cn = DriverManager.getConnection(url, "root", "mysql"); //커넥션 타입으로 정의해 두었기 때문에 메서드 자동완성이 알아서 위에 띄어줌
			System.out.println("**DB Connection 성공 **" );
			return cn;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("**DB Connection Exception => " + e.toString());
			return null;
			
		} //try
		
	}
	
	@Test
	public void connectionTest() {
		System.out.println("**DB_Connection => " + getConnection() );
		assertNotNull(getConnection());
	}
}
