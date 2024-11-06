package com.example.ex01.dependency;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

// was�� ��� ���� junit �̶�� �����׽�Ʈ ���α׷��� ������ �ָܼ� ��� ���� ��

// RunWith : � ���α׷����� ���� ������ ��� ����
// ���⼭ �����ϸ� SpringJUnit4ClassRunner.class �� ���α׷��� Ȱ���Ͽ� ����

// ContextConfiguration
// SpringJUnit4ClassRunner.class�� root-context��θ� �˷���� ������ �����ؼ� ���ư� �� ����
// ���ư� �� root-context�� �����Ͽ� ���ư�
// root-context�� �ش� ��ü���� ���� �ְ� �Ǿ�����

// Log4j
// �ڹ� : sysout / logger.info > �ڵ��ٹٲ�, �ܼ� ����� �� �ְ� ������
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class DependencyTest {
	
	@Autowired
	private Coding coding;
	
	@Test // test�� �ٿ��� �����׽�Ʈ�� �޼ҵ�� �ν��� �� > junit ���� ����
	public void checkDependencyInjection() {//�����������׽�Ʈ
		log.info("======================");
		log.info("coding==>"+coding);
		log.info("computer==>"+coding.getComputer());
		log.info("======================");
		
		// UnsatisfiedDependencyException (������ ������ ���� ����)
		// @ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml") root-context.xml���� @Component�پ��ִ� ���� �˻��� �� �ְ� ���������
		// �ش� ��Ű������ @Component�� ã����� ���� �����ؾ� �� �� ����
		
		
	}
	
}
