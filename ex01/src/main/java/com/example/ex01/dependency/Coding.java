package com.example.ex01.dependency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;



/**
 * @author admin
 *
 */
@Component
@Getter
//@AllArgsConstructor // 안에 잇는 모든 필드를 초기화 할 수 있는 생성자를 만듬
@RequiredArgsConstructor //여러개가 있는데 골라서 주입을 받고 싶을 때 > final, @NonNull 기준으로 생성
public class Coding {
	
	//코딩을 하기 위해서는 컴퓨터가 필요함 > 의존성이 생김
	//Computer computer=new Computer();//결합성이 너무 단단해짐
	
	// 컴퓨터는 객체는 코딩이 의존하고 있음
	// App컨텍스트 객체가 서버를 동작하면서 실행이됨 > 메모리에 올라가면서 @ 처리를 해줌
	// Coding 필드를 메모리에 올리는 순간 컴퓨터를 자동으로 주입함 new를 할 필요가 없음
		
	// 필드 주입
	// 조심해야할 점
	// 굉장히 편하게 주입할 수 있으나 순환 참조(무한 루프)시 오류가 발생하지 않기 때문에 StackOverFlow 발생.
	// 서로의 객체가 주입하다 보면 계속 주입이 됨(생성자와 별도로 주입되기 때문에) null이 들어오면 nullpoint입셉션이 떠야하는데
	// 무조건 주입이 됨. 무조건 Coding이 필드에 올라가면 computer도 필드에 올라감 서로가 계속 부르다보니 입셉션이 안뜸
	// 떠야 프로그램이 종료가 될탠데 강제 종료가 되지 않고 메모리 할당 되어 메모리 박살남
	// 메모리 영역을 넘어버리면 StackOverFlow가 발생한다.
	
	// final을 붙일 수 없기 때문에 다른 곳에서 변경 가능
	// 주입받은 값 말고 다른 값으로 변경시키면 문제가 생길 수 있음 (그래서 보통 final를 씀)
	// 외부에서 다른 값이 들어오지 못하게 막음
	
	// @Autowired//주입을 신청함
	// private Computer computer;
	// private를 붙이는 이유 : 외부에서 직접 접근하지 않고 간접적으로 접근해라 의미
	
	//===========================
	// 사실 final은 오류가 많이 난다. final를 붙일 수 없기 때문에 다른곳에서 변형이 가능하다.
	// final이 실행이 잘 된 이유 > Data를 쓰면 롬복이 생성자가 초기화 생성자가 자동으로 생김 > 오류가 안난건 생성자로도 주입 되기때문에
	// 초기화 생성자 없이 무조건 다이렉트로 필드 주입을 하면 final은 오류가남(필드 주입만 사용했을 때) 
	
	// Data를 없애면 바로 오류가 남
	// final은 상수이기 때문에 선언과 동시에 값을 지정해주어야함
	// 필드 주입을 통해 주입을 했다면 코딩이라는 클래스가 메모리에 할당이 되고 나서 요청
	// new Coding을 하려고 했는데 > 필드를 올릴려고 했는데 상수가 값이 없어서 오류가남
	// 초기화 하는 생성자가 생겼고 코딩 생성자는 롬복이 만들어준 생성자 밖에 없음 (무조건 컴퓨터 값이 들어오면서 바로 값이 초기화됨) 
	// >> 즉 외부에서 들어온 값으로 초기화가 된거임
	
	// 필드 주입만 하면 final을 쓸 수 없음 (생성자로 따로 받으면 안됨) 
	// 기존 주입 받은 값이 바뀔 수 있음
	// @Getter로 주입하면 됨
	
	//============================
	// Setter 주입
	// private Computer computer;

	// Setter을 쓰고 값을 전달해야만 값이 들어감 
	// 메소드를 사용해서 변수에 주입을 하기 위한 것
	
	// 이 필드 중 컴퓨터라는 객체가 필요할 때는 이 메소드를 활용하여 넣는다.
	// 굉장히 편하게 주입할 수 있으나 순환 참조(무한 루프)시 오류가 발생하지 않기 때문에 StackOverFlow 발생.
	// final을 붙일 수 없기 때문에 다른 곳에서 변경 가능
	// 외부에서 직접 주입 가능함(기존 객체가 주입받았던걸 변경해야할 때 씀) 
	// 하지만 대체할 요소들이 많아서 거의 쓰지 않음
	// Computer computer를 쓰게 된다는거에 의의가 있음
	//@Autowired
	//public void setComputer(Computer computer) {
	//	this.computer = computer;
	//}
	
	//=============================
	// 생성자 주입 (생성자에서 받는 것) =>> 올리면서 주입을 받음 // 필드,Setter => 올리고 나서 주입을 받음
	// 순환 참조시 컴파일러가 인지 가능, 오류 발생
	// >> 생성자를 통해서 값이 안뜨면 null입셉션이 뜨기 때문에 그 입셉션으로 순환참조를 막아줌
	// 생성자 주입은 필드를 올릴 때 null이 생김
	// 메모리에 할당하면서 초기값으로 주입되므로 final 키워드 사용 가능, 다른 곳에서 변형 불가능 (안전하다)
	// 의존성 주입이 되지 않으면 객체가 생성되지 않으므로 

	// 올리면서 주입이 되기 때문에 final 키워드 사용해도 그 값을 초기값으로 인식해서 다른 곳에서 변형이 안되게 할 수 있다.
	@NonNull
	private final Computer computer;
	
//	@Autowired // 스프링에서 주입
//	public Coding(Computer computer) {
//		super();
//		this.computer = computer;
//	}
	

	//==>> 세가지 주입 중 가장 안전하고 많이 쓰는 것은 생성자 주입임.
	// 한번 주입을 받으면 변경되는 일이 많지 않아야함
	// 주입을 받으면 주입에 문제가 생겼을 때 큰 입셉션을 만들지 않게 잘 막아줘야함
	// 주입을 하게 되면 new를 하지 않기 때문에 유연한 개발이 가능
	// 유연한 개발을 통해 생성성, 속도가 빨라짐
	// 한줄만 써져있기 때문에 내부적인 동작원리를 이해하기 위해서는 xml 등의 원리를 알아야 한다.


}
