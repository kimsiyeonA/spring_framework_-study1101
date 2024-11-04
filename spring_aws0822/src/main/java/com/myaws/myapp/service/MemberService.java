package com.myaws.myapp.service;

import com.myaws.myapp.domain.MemberVo;

// 사용할 메소드 정의
public interface MemberService {
	
	// ms와 같지만 다른 경우로 쓸 수 있기때문에 구분해놓음
	public int memberInsert(MemberVo mv);
	
	public int memberIdCheck(String memberid);
	
	public MemberVo memberLoginCheck(String memberid);
}
