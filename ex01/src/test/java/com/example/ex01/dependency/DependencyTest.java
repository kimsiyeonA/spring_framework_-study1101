package com.example.ex01.dependency;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

// was와 상관 없이 junit 이라는 단위테스트 프로그램을 돌려서 콘솔만 찍어 내면 됨

// RunWith : 어떤 프로그램으로 돌릴 것인지 경로 설정
// 여기서 실행하면 SpringJUnit4ClassRunner.class 이 프로그램을 활용하여 돌림

// ContextConfiguration
// SpringJUnit4ClassRunner.class에 root-context경로를 알려줘야 파일을 참조해서 돌아갈 수 있음
// 돌아갈 때 root-context를 참조하여 돌아감
// root-context는 해당 객체들을 볼수 있게 되어있음

// Log4j
// 자바 : sysout / logger.info > 자동줄바꿈, 콘솔 출력할 수 있게 도와줌
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class DependencyTest {
	
	@Autowired
	private Coding coding;
	
	@Test // test를 붙여야 단위테스트인 메소드로 인식이 됨 > junit 으로 실행
	public void checkDependencyInjection() {//의존성주입테스트
		log.info("======================");
		log.info("coding==>"+coding);
		log.info("computer==>"+coding.getComputer());
		log.info("======================");
		
		// UnsatisfiedDependencyException (의존성 주입이 되지 않음)
		// @ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml") root-context.xml에서 @Component붙어있는 것을 검색할 수 있게 설정해줘야
		// 해당 패키지에서 @Component를 찾으라는 것을 설정해야 알 수 있음
		
		
	}
	
}
