package com.example.ex01.dependency;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component//해당 객체를 Spring에서 관리하도록 설정 -> dependency 부분에 관리할 수 있도록 추가함
@Data // 룸북으로 다이렉트로 생김 > 특정 버그가 있음 전체 : @data / 일부: @Getter
public class Computer {

}
