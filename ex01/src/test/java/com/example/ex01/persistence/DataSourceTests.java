package com.example.ex01.persistence;

import static org.junit.Assert.fail;

import java.sql.Connection;

//import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class DataSourceTests {
	
	//@Autowired
	//private DataSource dataSource;
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Test
	public void testConnection() {
		try
		(
			SqlSession sqlSession = sqlSessionFactory.openSession(true); // 오토 커밋이 된다.
			Connection connection = sqlSession.getConnection();
		){
			log.info(sqlSession);
			log.info(connection);
		}catch (Exception e) {
			fail(e.getMessage());
		}
		
		
//		try(Connection connection = dataSource.getConnection()){
//			log.info(connection);
//			
//		}catch(Exception e) {
//			fail(e.getMessage());
//		}
	}
}
