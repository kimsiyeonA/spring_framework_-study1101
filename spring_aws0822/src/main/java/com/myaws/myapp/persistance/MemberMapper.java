package com.myaws.myapp.persistance;

import com.myaws.myapp.domain.MemberVo;

// 마이바티스에서 사용할 메소드를 정의해 놓은 곳
// 인터페이스 : 규격에 맞추어서 접속할 수 있게 함
// 메소드의 이름, 자료형, 받는 자료형을 정해놓음
public interface MemberMapper {

		public int memberInsert(MemberVo mv);
}
