package com.example.ex01.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/*
 * Mapper 인터페이스
 * 
 * 	SQL를 작성하는 작업은 XML을 이용할 수도 있지만, 최소한의 코드를 작성하기 위해서는
 * 	Mapper 인터페이스를 사용한다.
 *  연결 방법은 많지만 최적의 경로로 알려주시겠다고 하심
 *  
 *  TimeMapper <> xml
 * 
 *  @Mapper를 붙여야 인식을함
 *  component 스캔이 되어야함
 */
@Mapper
public interface TimeMapper {
	
	//xml를 안붙여도 쿼리가 가능함
	// 메소드를 실행시키면 연결된 스키마에서 이 쿼리가 실행된다.
	// 쿼리가 길어지면 복잡하고 더러워지니 xml에서 사용할 수 있도록 한다.
	@Select("SELECT now()") 
	public String getTime();
	
	
}
