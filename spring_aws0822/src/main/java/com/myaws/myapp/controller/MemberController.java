package com.myaws.myapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myaws.myapp.domain.MemberVo;
import com.myaws.myapp.service.MemberService;
import com.myaws.myapp.service.Test;

@Controller // ���������� ��Ʈ�ѷ� �뵵�� ����� �� �ְ� ��û��
@RequestMapping(value = "/member/") // FrontController ó��
public class MemberController {
	
	// ����� �ڵ�ó�� �� �� ����
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	//@Autowired // test�� ������ ��ü�� ��������
	//private Test tt; //Test Ŭ������ tt��� ���� ����
	
	@Autowired // �θ�� �����ϰ� �������̵� �� �ڽ��� �ҷ����� ����, �θ� �μ�Ʈ�� �������̵� �� Ŭ�������� ������� 
	MemberService memberService;
	
	
	// �����ο� �޼ҵ���� ���ν�Ŵ
	// �ش�Ǵ� �����ο� �޼ҵ带 ���� ���Ѱ� get ������� �Ѱ� ����
	// �ڵ鷯 ���� �ȿ� �ڵ鷯 ����Ͱ� ã�Ƽ� �����Ŵ
	@RequestMapping(value = "memberJoin.aws", method = RequestMethod.GET)
	public String memberJoin() {
	
		 //sysout���� ���ҽ��� ���� �������
		// �ش� ��θ� �˷��� ���� �ۼ��Ǿ� �ִ� ���� �˷���
		logger.info("memberJoin ����");
		//logger.info("tt����"+tt.test()); // tt ��ü�� �ִ� �޼��� �ҷ�����
		
		return "WEB-INF/member/memberJoin"; // .jsp�� WEB-INF/spring/appServlet/servlet-context.xml > ���� �Ѿ���
	}
	
	@RequestMapping(value = "memberLogin.aws", method = RequestMethod.GET)
	public String memberLogin() {
		
		logger.info("memberLogin ����");
	
		return "WEB-INF/member/memberLogin"; 
	}
	
	@RequestMapping(value = "memberJoinAction.aws", method = RequestMethod.POST)
	public String memberJoinAction(MemberVo mv) { // usebean ó�� �ٿ�� �޾� ����
		logger.info("memberJoinAction ����");
		
		int value = memberService.memberInsert(mv);
		logger.info("memberJoinAction value"+value);
		
		String path = "";
		if(value==1) {
			path = "redirect:/";
		}else if(value==0) {
			path = "redirect:/member/memberJoin.aws";
			// �����ϸ� ������������ ��
		}
		logger.info("memberJoinAction path"+path);
	
		return path; 
	}


}
















