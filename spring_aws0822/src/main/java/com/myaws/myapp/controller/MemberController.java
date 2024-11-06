package com.myaws.myapp.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myaws.myapp.domain.MemberVo;
import com.myaws.myapp.service.MemberService;
import com.myaws.myapp.service.Test;

import netscape.javascript.JSException;
import netscape.javascript.JSObject;

@Controller // 스프링에게 컨트롤러 용도로 사용할 수 있게 요청함
@RequestMapping(value = "/member/")// FrontController 처럼
public class MemberController {
	
	// 디버깅 코드처럼 찍어볼 수 있음
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	//@Autowired // test로 생성된 객체를 주입해줘
	//private Test tt; //Test 클래스에 tt라는 변수 선언
	
	@Autowired // 부모로 지정하고 오버라이딩 된 자식을 불러오고 있음, 부모 인서트를 오버라이딩 한 클래스들을 가지고옴 
	private MemberService memberService;
	
	//비밀번호를 암호화하여 인코딩하는 역할
	@Autowired(required=false)
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	// 가상경로와 메소드들을 매핑시킴
	// 해당되는 가상경로와 메소드를 매핑 시켜고 get 방식으로 넘겨 받음
	// 핸들러 매핑 안에 핸들러 어댑터가 찾아서 실행시킴
	@RequestMapping(value = "memberJoin.aws", method = RequestMethod.GET)
	public String memberJoin() {
	
		 //sysout보다 리소스를 적게 가지고옴
		// 해당 경로를 알려준 다음 작성되어 있는 값을 알려줌
		logger.info("memberJoin ����");
		//logger.info("tt값은"+tt.test()); // tt 객체에 있는 메서드 불러오기
		
		return "WEB-INF/member/memberJoin"; // .jsp는 WEB-INF/spring/appServlet/servlet-context.xml > 에서 붇어짐
	
	}
	
	@RequestMapping(value = "memberLogin.aws", method = RequestMethod.GET)
	public String memberLogin() {
		
		logger.info("memberLogin ����");
		// 모델로 값을 가지고 다닐 수 있음 > 담은 데이터가 가지고 있음
		// 한번 쓰면 안나타나게 하는 일회용 클래스 기능이 있는 클래스를 사용
		// 로그인을 했을 때 다시 로그인창이 안나타나게 함
		
		
		return "WEB-INF/member/memberLogin"; 
	}
	
	@RequestMapping(value = "memberJoinAction.aws", method = RequestMethod.POST)
	public String memberJoinAction(MemberVo mv) { // usebean 처럼 바운딩 받아 쓰기
		logger.info("memberJoinAction ����");
		logger.info("bCryptPasswordEncoder"+bCryptPasswordEncoder);
		// 비밀번호 암호화 처리
		// 들어온 비밀번호를 암호화시키고 암호화시킨것을 담는다.
		String memberpwd_enc = bCryptPasswordEncoder.encode(mv.getMemberpwd());
		mv.setMemberpwd(memberpwd_enc);
		
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

	@ResponseBody //json���� ����� ��Ÿ����.
	@RequestMapping(value = "memberIdCheck.aws", method = RequestMethod.POST) // ����� ����Ʈ,get �Ѵ� �ν���
	// 지웠을 때 결과 확인 가능 http://localhost:8080/myapp/member/memberIdCheck.aws?memberId=%E3%84%B9%E3%84%B9%E3%84%B9
	// Post로 지정하면 확인 못함
	public JSONObject memberIdCheck(@RequestParam("memberId") String memberId) {
		logger.info("memberIdCheck 들어옴");
		logger.info("memberId"+memberId);
		
		// 경우에 따라 객체생성을 할 수도 있음 -> 포조 방식
		// 맴버 서비스 인터페이스 선언 후 구현 마이바티스에 접속 하여 쿼리 써서 실행
		int cnt = memberService.memberIdCheck(memberId);
		logger.info("memberIdCheck cnt"+cnt);
		
		// json 작성하는 메이븐 다운받아서 활용하기
		//PrintWriter out = response.getWriter();
		//out.print("{\"cnt\": \""+cnt+"\"}");
		
		JSONObject obj = new JSONObject();
		obj.put("cnt", cnt);
		
		return obj; 
	}
	
	@RequestMapping(value = "memberLoginAction.aws", method = RequestMethod.POST)
	public String memberLoginAction(
			@RequestParam("memberid") String memberid, 
			@RequestParam("memberpwd") String memberpwd,
			// 일회용 + 모델의 기능
			RedirectAttributes rttr,
			HttpSession session
			) {// usebean 처럼 바운딩 받아 쓰기

		MemberVo mv = memberService.memberLoginCheck(memberid);
		// 저장된 비밀번호를 가지고 온다.
		String path = "";
		if(mv != null) { // 아이디가 안들어가져 있으면
			String reservedPwd = mv.getMemberpwd();
		
			
			if(bCryptPasswordEncoder.matches(memberpwd, reservedPwd)  ){

				
				// 세션 : 하나의 연결성 ip는 같아도 세션id값은 다 다르다 / 회원정보를 세션에 넣음
				  
				rttr.addAttribute("midx",mv.getMidx()); 
				rttr.addAttribute("memberId",mv.getMemberid());
				rttr.addAttribute("memberName",mv.getMembername());
				 
				// 쿼리와 input 네임을 바인딩하기 위해서 소문자로 작성함
				logger.info("saveUrl===> "+session.getAttribute("saveUrl"));
				if(session.getAttribute("saveUrl") != null) {
					path="redirect:"+session.getAttribute("saveUrl").toString();
				}else {
					path="redirect:/";	
				}
				
			}else {
				rttr.addFlashAttribute("msg","아이디/비밀번호를 확인해주세요.");
				// 담아놓으면 한번만 쓸 수 있음 (세션을 가지고 갔다가 한번 쓰면 사라짐)
				path="redirect:/member/memberLogin.aws";
				
			}
		}else {
			rttr.addFlashAttribute("msg","해당하는 아이디가 없습니다.");
			path="redirect:/member/memberLogin.aws";
			
		}
		logger.info("memberLoginAction path"+path);
		// 회원정보를 세션에 담는다.
		// 로그인이 안되면 다시 로그인페이지로 가고 (비밀번호를 암호화 해서 넘기고 받을 수 있게 만들기)
		// 로그인이 되면 메인으로 가라
	
		return path; 
	}
	
	@RequestMapping(value = "memberList.aws", method = RequestMethod.GET)
	public String memberList(Model model) {
		
		logger.info("memberList 들어옴");
		ArrayList<MemberVo> alist = memberService.memberSelectAll();
		
		model.addAttribute("alist", alist);
		return "WEB-INF/member/memberList"; 
	}
	
	@RequestMapping(value = "memberLogout.aws", method = RequestMethod.GET)
	public String memberLogout(HttpSession session) {
		
		session.removeAttribute("midx");
		session.removeAttribute("memberId");
		session.removeAttribute("memberName");
		session.invalidate();//초기화

		return "redirect:/"; 
	}
}
















