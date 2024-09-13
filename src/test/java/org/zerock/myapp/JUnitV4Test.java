package org.zerock.myapp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


/**
 * JUnit Test Framework 에서 요구하는 테스트 클래스는 아래와 같은 규칙을 반드시
 * 지켜야 합니다.
 * 
 * (1) 반드시 매개변수 없는 "기본" 생성자를 선언해야 합니다. (당연히 명시적으로)
 * (2) 다른 클래스나 인터페이스를 임의대로 extends 또는 implements 해서는 안됩니다.
 *     즉, 쉽게 얘기하면, POJO이어야 합니다.
 * (3) 전처리/후처리/테스트 메소드는 접근제한자 반드시 public으로 선언하셔야 합니다.
 * (4) 테스트용도로 만든 메소드의 이름은 관례상, 접두사로 "test"를 붙입니다.
 *     예: testLombok()
 * (5) JUnit 4에서, 전처리/후처리 메소드의 이름은 관례상 아래와 같이
 *     정해서 사용합니다:
 *     가. 전처리: setup
 *     나. 후처리: tearDown
 *     
 */

@Log4j2
@NoArgsConstructor
public class JUnitV4Test {	// POJO
	
	@Before
	public
	void setup() {	// 테스트 메소드가 수행되기 전에, 우선 수행되는 (사)전처리 메소드
		log.trace("(1) setup() invoked.");;
		
	} // setup
	
	@Test(timeout = 500L)
	public
	void 단위테스트3() throws InterruptedException { // 테스트 수행 메소드
		log.trace("(2-1) 단위테스트3() invoked.");
		
		Thread.sleep(1000L * 1);
	} // 단위테스트3
	
	@Test
	public
	void 단위테스트2() { // 테스트 수행 메소드
		log.trace("(2-2) 단위테스트2() invoked.");
		
	} // 단위테스트2
	
	@Test
	public
	void 단위테스트1() { // 테스트 수행 메소드
		log.trace("(2-3) 단위테스트1() invoked.");
		
	} // 단위테스트1
	
	@After
	public
	void tearDown() {	// 테스트 메소드가 수행되기 후에, 수행되는 (사)후처리 메소드
		log.trace("(3) tearDown() invoked.");
		
	} // tearDown
	
} // end class
