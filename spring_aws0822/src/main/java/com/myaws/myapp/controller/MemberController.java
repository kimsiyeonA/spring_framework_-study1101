package com.myaws.myapp.controller;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
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

@Controller // ���������� ��Ʈ�ѷ� �뵵�� ����� �� �ְ� ��û��
@RequestMapping(value = "/member/") // FrontController ó��
public class MemberController {
	
	// ����� �ڵ�ó�� �� �� ����
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	//@Autowired // test�� ������ ��ü�� ��������
	//private Test tt; //Test Ŭ������ tt��� ���� ����
	
	@Autowired // �θ�� �����ϰ� �������̵� �� �ڽ��� �ҷ����� ����, �θ� �μ�Ʈ�� �������̵� �� Ŭ�������� ������� 
	private MemberService memberService;
	
	//��й�ȣ�� ��ȣȭ�Ͽ� ���ڵ��ϴ� ����
	@Autowired(required=false)
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
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
		// �𵨷� ���� ������ �ٴ� �� ���� > ���� �����Ͱ� ������ ����
		// �ѹ� ���� �ȳ�Ÿ���� �ϴ� ��ȸ�� Ŭ���� ����� �ִ� Ŭ������ ���
		// �α����� ���� �� �ٽ� �α���â�� �ȳ�Ÿ���� ��
		
		
		return "WEB-INF/member/memberLogin"; 
	}
	
	@RequestMapping(value = "memberJoinAction.aws", method = RequestMethod.POST)
	public String memberJoinAction(MemberVo mv) { // usebean ó�� �ٿ�� �޾� ����
		logger.info("memberJoinAction ����");
		logger.info("bCryptPasswordEncoder"+bCryptPasswordEncoder);
		// ��й�ȣ ��ȣȭ ó��
		// ���� ��й�ȣ�� ��ȣȭ��Ű�� ��ȣȭ��Ų���� ��´�.
		String memberpwd_enc = bCryptPasswordEncoder.encode(mv.getMemberpwd());
		mv.setMemberpwd(memberpwd_enc);
		
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

	@ResponseBody //json���� ����� ��Ÿ����.
	@RequestMapping(value = "memberIdCheck.aws", method = RequestMethod.POST) // ����� ����Ʈ,get �Ѵ� �ν���
	// ������ �� ��� Ȯ�� ���� http://localhost:8080/myapp/member/memberIdCheck.aws?memberId=%E3%84%B9%E3%84%B9%E3%84%B9
	// Post�� �����ϸ� Ȯ�� ����
	public JSONObject memberIdCheck(@RequestParam("memberId") String memberId) {
		logger.info("memberIdCheck ����");
		logger.info("memberId"+memberId);
		
		// ��쿡 ���� ��ü������ �� ���� ���� -> ���� ���
		// �ɹ� ���� �������̽� ���� �� ���� ���̹�Ƽ���� ���� �Ͽ� ���� �Ἥ ����
		int cnt = memberService.memberIdCheck(memberId);
		logger.info("memberIdCheck cnt"+cnt);
		
		// json �ۼ��ϴ� ���̺� �ٿ�޾Ƽ� Ȱ���ϱ�
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
			// ��ȸ�� + ���� ���
			RedirectAttributes rttr
			) { // usebean ó�� �ٿ�� �޾� ����

		MemberVo mv = memberService.memberLoginCheck(memberid);
		// ����� ��й�ȣ�� ������ �´�.
		String path = "";
		if(mv != null) { // ���̵� �ȵ��� ������
			String reservedPwd = mv.getMemberpwd();
		
			
			if(bCryptPasswordEncoder.matches(memberpwd, reservedPwd)  ){
				//System.out.println("��й�ȣ ��ġ");
				/*
				 * rttr.addAttribute("midx",mv.getMidx());
				 * rttr.addAttribute("memberId",mv.getMemberid());
				 * rttr.addAttribute("memberName",mv.getMembername());
				 */
				// ������ input ������ ���ε��ϱ� ���ؼ� �ҹ��ڷ� �ۼ���
				// 
				path="redirect:/";
				
			}else {
				/*
				 * rttr.addAttribute("midx",""); 
				 * rttr.addAttribute("memberId","");
				 * rttr.addAttribute("memberName","");
				 */
				rttr.addFlashAttribute("msg","���̵�/��й�ȣ�� Ȯ�����ּ���.");
				// ��Ƴ����� �ѹ��� �� �� ���� (������ ������ ���ٰ� �ѹ� ���� �����)
				path="redirect:/member/memberLogin.aws";
				
			}
		}else {
			/* �α����ϸ� ���ǿ� ��� ���ؼ�
			 * rttr.addAttribute("midx",""); 
			 * rttr.addAttribute("memberId","");
			 * rttr.addAttribute("memberName","");
			 */
			rttr.addFlashAttribute("msg","�ش��ϴ� ���̵� �����ϴ�.");
			path="redirect:/member/memberLogin.aws";
			
		}
		logger.info("memberLoginAction path"+path);
		// ȸ�������� ���ǿ� ��´�.
		// �α����� �ȵǸ� �ٽ� �α����������� ���� (��й�ȣ�� ��ȣȭ �ؼ� �ѱ�� ���� �� �ְ� �����)
		// �α����� �Ǹ� �������� ����
	
		return path; 
	}
	
}
















