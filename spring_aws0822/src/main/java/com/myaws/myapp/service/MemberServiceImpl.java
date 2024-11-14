package com.myaws.myapp.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myaws.myapp.domain.MemberVo;
import com.myaws.myapp.persistance.MemberMapper;

//첫번째 부터 인것은 파스칼
//인터페이스를 구현한한다.
@Service //>>@Component/@Bean가 부모 : 서비스 용도의 빈이다.
public class MemberServiceImpl implements MemberService{
	
	private MemberMapper mm; 
	
	@Autowired //스프링 코어에다가 빈으로 만든 세션을 부름 > 객체로 생성되어 있는 것중에 같은 클래스 타입을 찾아서 주입시킴
	public MemberServiceImpl(SqlSession sqlSession) { // 생성자를 통해서 매개변수로 SqlSession을 받음
		// 만들어진 메퍼를 쓸꺼임 com.myaws.myapp.persistance > MemberMapper.java
		// 특이한 점은 .class을 붙여야함
		// 맴버변수에 연결
		this.mm = sqlSession.getMapper(MemberMapper.class); 
		
	}
	
	@Override /// 여기서 재정의 해라 - mybatis와 연동
	public int memberInsert(MemberVo mv) {
		
		int value = mm.memberInsert(mv);

		return value;
	}

	@Override
	public int memberIdCheck(String memberid) {
		
		int value = mm.memberIdCheck(memberid);
		return value;
	}
	
	@Override
	public MemberVo memberLoginCheck(String memberid) {
		 MemberVo mv = mm.memberLoginCheck(memberid);
		 System.out.println("MemberServiceImpl.java memberLoginCheck mv"+mv);
		return mv;
	}
	
	@Override
	public ArrayList<MemberVo> memberSelectAll() {
		ArrayList<MemberVo> alist = mm.memberSelectAll();
		return alist;
	}
}
