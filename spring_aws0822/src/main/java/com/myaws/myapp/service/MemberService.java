package com.myaws.myapp.service;

import java.util.ArrayList;

import com.myaws.myapp.domain.MemberVo;

//����� �޼ҵ� ����
//���������� MEMBER��ɿ��� ����� �޼ҵ带 �����ϴ� ��
public interface MemberService {
	
	// ms�� ������ �ٸ� ���� �� �� �ֱ⶧���� �����س���
	public int memberInsert(MemberVo mv);
	
	public int memberIdCheck(String memberid);
	
	public MemberVo memberLoginCheck(String memberid);
	
	public ArrayList<MemberVo> memberSelectAll();
}
