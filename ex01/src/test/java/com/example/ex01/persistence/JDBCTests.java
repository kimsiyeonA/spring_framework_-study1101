package com.example.ex01.persistence;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class JDBCTests {
	
	static { // 스테틱 필드 쓰기> 실행이 되자마자 바로 한번만 실행됨
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			// 드라이버를 메모리에 올리기
//			// try-catch 입히기
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 드라이버를 메모리에 올리기
			// try-catch 입히기
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	
	@Test
	public void testConnection() { 
		//  여기서 커넥션 객체 가지고 오기, 보통 try-catch 잡을 때 try문에서 작성하고
		// 연결객체를 가지고 오니깐 close 까지 해주어야함
		// 외부장치를 여는 문장을 try에 넣는게 아니라 try 옆에 ()에 넣을거임
		// ()에 작성된 것중 외부장치를 열었다면 자동으로 닫아줌
		// try스테이트문트
		/*
		 * try(Connection connection =
		 * DriverManager.getConnection("jdbc:oracle:thin:@localhost:1251:XE","hr","hr"))
		 * { log.info(connection); }catch(Exception e){ fail(e.getMessage()); // - JUnit
		 * 의 메소드로서, 무조건 실패로 처리한 뒤 실행을 중지한다. // junit 에 있는 메소드 // catch 들어왔을 때 무조건 실패로 해서
		 * 강제종료 됨 }
		 */
		
		try(Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/aws0822?serverTimezone=UTC","root","1234")){
			log.info(connection);
		}catch(Exception e){
			fail(e.getMessage());
			// - JUnit 의 메소드로서, 무조건 실패로 처리한 뒤 실행을 중지한다.
			// junit 에 있는 메소드
			// catch 들어왔을 때 무조건 실패로 해서 강제종료 됨
		}
		
	}
	
	// 위에서 DBMS 드라이버를 먼저 할당 해주면
	// 그 드라이버에 대해 url을 써주면 해당 주소가 잘 나온다.
	
}
