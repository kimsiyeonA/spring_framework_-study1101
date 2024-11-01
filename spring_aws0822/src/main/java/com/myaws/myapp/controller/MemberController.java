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

@Controller // 스프링에게 컨트롤러 용도로 사용할 수 있게 요청함
@RequestMapping(value = "/member/") // FrontController 처럼
public class MemberController {
	
	// 디버깅 코드처럼 찍어볼 수 있음
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	//@Autowired // test로 생성된 객체를 주입해줘
	//private Test tt; //Test 클래스에 tt라는 변수 선언
	
	@Autowired // 부모로 지정하고 오버라이딩 된 자식을 불러오고 있음, 부모 인서트를 오버라이딩 한 클래스들을 가지고옴 
	MemberService memberService;
	
	
	// 가상경로와 메소드들을 매핑시킴
	// 해당되는 가상경로와 메소드를 매핑 시켜고 get 방식으로 넘겨 받음
	// 핸들러 매핑 안에 핸들러 어댑터가 찾아서 실행시킴
	@RequestMapping(value = "memberJoin.aws", method = RequestMethod.GET)
	public String memberJoin() {
	
		 //sysout보다 리소스를 적게 가지고옴
		// 해당 경로를 알려준 다음 작성되어 있는 값을 알려줌
		logger.info("memberJoin 들어옴");
		//logger.info("tt값은"+tt.test()); // tt 객체에 있는 메서드 불러오기
		
		return "WEB-INF/member/memberJoin"; // .jsp는 WEB-INF/spring/appServlet/servlet-context.xml > 에서 붇어짐
	}
	
	@RequestMapping(value = "memberLogin.aws", method = RequestMethod.GET)
	public String memberLogin() {
		
		logger.info("memberLogin 들어옴");
	
		return "WEB-INF/member/memberLogin"; 
	}
	
	@RequestMapping(value = "memberJoinAction.aws", method = RequestMethod.POST)
	public String memberJoinAction(MemberVo mv) { // usebean 처럼 바운딩 받아 쓰기
		logger.info("memberJoinAction 들어옴");
		
		int value = memberService.memberInsert(mv);
		logger.info("memberJoinAction value"+value);
		
		String path = "";
		if(value==1) {
			path = "redirect:/";
		}else if(value==0) {
			path = "redirect:/member/memberJoin.aws";
			// 실패하면 가입페이지로 들어감
		}
		logger.info("memberJoinAction path"+path);
	
		return path; 
	}


}
















