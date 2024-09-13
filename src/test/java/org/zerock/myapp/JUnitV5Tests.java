package org.zerock.myapp;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * JUnit Test Framework 에서 요구하는 테스트 클래스는 아래와 같은 규칙을 반드시
 * 지켜야 합니다.
 * 
 * (1) 반드시 매개변수 없는 "기본" 생성자를 선언해야 합니다. (당연히 명시적으로)
 * (2) 다른 클래스나 인터페이스를 임의대로 extends 또는 implements 해서는 안됩니다.
 *     즉, 쉽게 얘기하면, POJO이어야 합니다.
 * (3) 전처리/후처리/단위테스트 메소드의 접근제한자는 default이어도 상관없다!!
 * (4) 전처리/후처리/단위테스트 메소드의 이름도 여러분 맘대로...
 *     따로 v5에서는 관례가 없습니다.
 * (5) 주의사항1: 단위테스트에 붙일 수 잇는 어노테이션들 중에, 함께 동시에 사용불가한
 *                어노테이션: @Test, @RepeatedTest(N)
 * (6) 주의사항2: @Disabled 어노테이션의 활성화여부를 잘 체크하시라!!!
 * (7) 어떤 때에는, 전처리/후처리 메소드에 static 이란 키워드를 붙여야 한다!!! 
 * 	   여기서, 어떤 때란, 타입선언부에 붙힌, @TestInstance(PER_METHOD) 일때만,
 * 	   @BeforeAll 과 @AfterAll 메소드는 정적으로 선언해야만 합니다. 
 */

@Log4j2
@NoArgsConstructor

// 타입선언부 위에 붙일 새로운 어노테이션 2개:

// (1) 테스트 클래스의 객체를 생성하는 기준 설정
@TestInstance(Lifecycle.PER_CLASS)	// 클래스당 1개의 객체생성 -> 테스트 수행
//@TestInstance(Lifecycle.PER_METHOD)		// 단위테스트 메소드당 1개의 객체생성 -> 테스트 수행

// (2) 단위테스트 메소드의 수행순서를 어떤 기준으로 정할 것인지 설정
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JUnitV5Tests {	// POJO
	
	// version5 에서 사용가능한 어노테이션 목록 나열
	
	// (1) "전처리용" 어노테이션
	@BeforeAll
//	static	// 타입선언부에 붙힌, @TestInstance(PER_METHOD)로 된 경우에만 반드시 static이어야 함
	void beforeAll() {	// 오로직 딱 한번만 수행되는 전처리 로직을 수행
		log.trace("beforeAll() invoked.");
		
	}
	
	@BeforeEach
	void beforeEach() { // 각각의 단위 테스트 수행시마다, 반복적 수행 전처리
		log.trace("beforeEach() invoked.");
		
	}
	
	// (2) "후처리용" 어노테이션
	@AfterAll
//	static // 타입선언부에 붙힌, @TestInstance(PER_METHOD)로 된 경우에만 반드시 static이어야 함
	void afterAll() {	// 오로직 딱 한번만 수행되는 사후처리 로직 수행
		log.trace("afterAll() invoked.");
		
	} // afterAll
	
	@AfterEach
	void afterEach() {	// 각각의 단위 테스트 수행시마다, 반복적 수행 후처리
		log.trace("afterEach() invoked.");
		
	} // afterEach
	
	// (3) 단위테스트용 어노테이션
//	@Disabled			// 이 단위테스트는 비활성화시켜라!!! -> 수행하지 마라!!
	@Order(2)			// 이 단위테스트의 실행순서 지정 -> 시나리오 순서대로 테스트 수행
	
//	@Test				// 어제 사용한 것과 동일 -> 이 메소드가 단위테스트이다!!
	@RepeatedTest(3)	// 지정된 숫자만큼 동일한 단위테스트를 반복수행하라!!!
						// 바로 위의 @Test 어노테이션과는 동시에 사용불가!!!
	
	// JUnit View 같은, 개발도구에 표시될 단위테스트에 대한 설명/이름을 설정
	@DisplayName("(1) 테스트1")
	
	@Timeout(value=1L, unit=TimeUnit.SECONDS)	// 테스트의 만료시간 설정 
	void contextLoads1() {
		log.trace("contextLoads1() invoked.");
		
	} // contextLoads1
	
	// (3) 단위테스트용 어노테이션
	@Disabled			// 이 단위테스트는 비활성화시켜라!!! -> 수행하지 마라!!
	@Order(1)			// 이 단위테스트의 실행순서 지정 -> 시나리오 순서대로 테스트 수행
	
	@Test				// 어제 사용한 것과 동일 -> 이 메소드가 단위테스트이다!!
//	@RepeatedTest(1)	// 지정된 숫자만큼 동일한 단위테스트를 반복수행하라!!!
						// 바로 위의 @Test 어노테이션과는 동시에 사용불가!!!
	
	// JUnit View 같은, 개발도구에 표시될 단위테스트에 대한 설명/이름을 설정
	@DisplayName("(2) 테스트2")
	
	@Timeout(value=1L, unit=TimeUnit.SECONDS)	// 테스트의 만료시간 설정 
	void contextLoads2() {
		log.trace("contextLoads2() invoked.");
		
	} // contextLoads2
	

} // end class
