package com.myaws.myapp.persistance;

import java.util.ArrayList;

import com.myaws.myapp.domain.MemberVo;

//���̹�Ƽ������ ����� �޼ҵ带 ������ ���� ��
//�������̽� : �԰ݿ� ���߾ ������ �� �ְ� ��
//�޼ҵ��� �̸�, �ڷ���, �޴� �ڷ����� ���س���
public interface MemberMapper {

		public int memberInsert(MemberVo mv);
		
		public int memberIdCheck(String memberid);
		
		public MemberVo memberLoginCheck(String memberid);
		
		public ArrayList<MemberVo> memberSelectAll();
}
