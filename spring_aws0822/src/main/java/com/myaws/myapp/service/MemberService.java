package com.myaws.myapp.service;

import com.myaws.myapp.domain.MemberVo;

// ����� �޼ҵ� ����
public interface MemberService {
	
	// ms�� ������ �ٸ� ���� �� �� �ֱ⶧���� �����س���
	public int memberInsert(MemberVo mv);
	
	public int memberIdCheck(String memberid);
	
	public MemberVo memberLoginCheck(String memberid);
}
