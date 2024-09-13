package org.zerock.myapp;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Calculator {
	
	public int 덧셈(int num1, int num2) {
		log.trace("덧셈({},{}) invoked.", num1, num2);
		return num1 + num2;
	} // 덧셈

	public int 뺄셈(int num1, int num2) {
		log.trace("뺄셈({},{}) invoked.", num1, num2);
		return num1 - num2;
	} // 덧셈
	
	public double 나눗셈(double num1, double num2) {
		log.trace("나눗셈({},{}) invoked.", num1, num2);
		return num1 / num2;
	} // 나눗셈
	
	public int 곱셈(int num1, int num2) {
		log.trace("곱셈({},{}) invoked.", num1, num2);
		return num1 * num2;
	} // 곱셈

} // end class
