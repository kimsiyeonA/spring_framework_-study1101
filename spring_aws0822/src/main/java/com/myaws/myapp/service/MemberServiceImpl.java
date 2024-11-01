package com.myaws.myapp.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myaws.myapp.domain.MemberVo;
import com.myaws.myapp.persistance.MemberMapper;

// ù��° ���� �ΰ��� �Ľ�Į
// �������̽��� �������Ѵ�.
@Service //>>@Component/@Bean�� �θ� : ���� �뵵�� ���̴�.
public class MemberServiceImpl implements MemberService{
	
	private MemberMapper mm; // �ɹ����� �����ϱ�
	
	@Autowired //������ �ھ�ٰ� ������ ���� ������ �θ� > ��ü�� �����Ǿ� �ִ� ���߿� ���� Ŭ���� Ÿ���� ã�Ƽ� ���Խ�Ŵ
	public MemberServiceImpl(SqlSession sqlSession) { // �����ڸ� ���ؼ� �Ű������� SqlSession�� ����
		// ������� ���۸� ������ com.myaws.myapp.persistance > MemberMapper.java
		// Ư���� ���� .class�� �ٿ�����
		// �ɹ������� ����
		this.mm = sqlSession.getMapper(MemberMapper.class); 
		
	}
	
	
	

	@Override // ���⼭ ������ �ض� - mybatis�� ����
	public int memberInsert(MemberVo mv) {
		
		int value = mm.memberInsert(mv);

		return value;
	}

	
}
