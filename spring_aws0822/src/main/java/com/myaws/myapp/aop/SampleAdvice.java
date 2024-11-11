package com.myaws.myapp.aop;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect //Aspect 모듈이다 전 영역을 쿡쿡 찌르며 감시할 것이다.
public class SampleAdvice {
	
	//어느어느 지점에 해당하면 실행해라는 메소드
	public void startLog() {
		
	}
	
}
