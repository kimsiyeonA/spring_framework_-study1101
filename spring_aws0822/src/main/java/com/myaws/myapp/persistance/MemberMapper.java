package com.myaws.myapp.persistance;

import com.myaws.myapp.domain.MemberVo;

// ���̹�Ƽ������ ����� �޼ҵ带 ������ ���� ��
// �������̽� : �԰ݿ� ���߾ ������ �� �ְ� ��
// �޼ҵ��� �̸�, �ڷ���, �޴� �ڷ����� ���س���
public interface MemberMapper {

		public int memberInsert(MemberVo mv);
}
