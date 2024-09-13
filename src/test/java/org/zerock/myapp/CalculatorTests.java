package org.zerock.myapp;

import static org.junit.Assert.assertEquals;

import java.util.Objects;

import org.junit.Before;
import org.junit.Test;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor
public class CalculatorTests {
	private Calculator cal;
	
	@Before
	public void setup() { // 테스트 메소드가 수행되기 전에, 우선 수행되는 전처리 메소드
		log.trace("setup() invoked.");
		this.cal = new Calculator();
	} // before
	
//	@Test
//	public void testCaculator() {
//		this.cal.곱셈(100, 200);
//		this.cal.나눗셈(10, 4);
//		this.cal.덧셈(100, 200);
//		this.cal.뺄셈(200, 100);
//	} // testCaculator
	
	@Test(timeout = 1000L * 1)
	public void test덧셈() throws Exception {
		log.trace("test덧셈() invoked.");

//		(1)
		Objects.requireNonNull(this.cal);
//		(2)
//		assert this.cal != null;
//		(3)
//		assertNotNull(this.cal);
		
		int num1 = 100;
		int num2 = 200;
		
		int result = this.cal.덧셈(num1, num2);
		log.trace("\t + {}",result);
		
		assertEquals(300, result); // 검증수행
	} // test덧셈
	
	@Test(timeout = 1000L * 1)
	public void test뺄셈() {
		log.trace("test뺄셈() invoked.");
		this.cal.뺄셈(200, 100);
	} // test뺄셈
	
	@Test(timeout = 1000L * 1)
	public void test곱셈() {
		log.trace("test곱셈() invoked.");
		this.cal.곱셈(100, 200);
	} // test곱셈
	
	@Test(timeout = 1000L * 1)
	public void test나눗셈() {
		log.trace("test나눗셈() invoked.");
		this.cal.나눗셈(10, 4);
	} // test나눗셈
} // end class
