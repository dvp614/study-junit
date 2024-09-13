package org.zerock.myapp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BCryptPasswordEncoderTests {
	private PasswordEncoder encoder;

	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");

		PasswordEncoder encoder = 
				PasswordEncoderFactories
					.createDelegatingPasswordEncoder();

		Objects.requireNonNull(encoder);
		log.info("\t+ encoder : {}", encoder);

		this.encoder = encoder;
	} // beforeAll

	@Disabled
	@Order(1)
	@Test
	@RepeatedTest(1)
	@DisplayName("1. 사용자 입력암호를 BCrypt 해쉬알고리즘으로 해쉬값 생성")
	@Timeout(1L)
	void testBCryptPasswordEncoder() {
		log.trace("testBCryptPasswordEncoder() invoked.");

		String password = "Yoon12334@";
		String hashValue = this.encoder.encode(password);

		log.info("\t+ hashValue : {}", hashValue);
	} // testBCryptPasswordEncoder

	@Disabled
	@Order(2)
	@Test
	@RepeatedTest(1)
	@DisplayName("2. 입력암호 + salt 를 BCrypt 해쉬알고리즘으로 해쉬값 생성")
	@Timeout(1L)
	void testBCryptPasswordEncoderWithSalt() {
		log.trace("testBCryptPasswordEncoderWithSalt() invoked.");

		String originalPassword = "Yoon12334@";
		String salt = "__SALT__";

		String password = originalPassword + salt;
		String hashValue = this.encoder.encode(password);

		log.info("\t+ hashValue : {}", hashValue);
	} // testBCryptPasswordEncoderWithSalt

	@Disabled
	@Order(3)
	@Test
	@RepeatedTest(1)
	@DisplayName("3. 기존 저장된 해쉬값과 인증을 위해 입력된 암호가 같은지 검즌")
	@Timeout(value = 1L, unit=TimeUnit.MINUTES)
	void testPasswordMatching() {
		log.trace("testPasswordMatching() invoked.");

		// 테이블에 저장된 해쉬값과 인증을 위해 입력된 암호의 검증
		String password = "Yoon12334@";
//		String password2 = "salfdljfds@@#$";
		
		String hash = this.encoder.encode(password);
		log.trace("\t+ hash : {}", hash);
		
		boolean isMatched = this.encoder.matches(password, hash);
		log.info("\t+ isMatch : {}", isMatched);
		assertEquals(true, isMatched);
		
//		for(int i = 0; i <= 5; i++) {
//			String hash2 = this.encoder.encode(password);
//			if(i==5) {
//				log.trace("\t+ hash : {}", hash);
//				log.trace("\t+ hash2 : {}", hash2);
//				boolean isHashMatched = this.encoder.matches(hash, hash2);
//				boolean isPasswordMatched = this.encoder.matches(password, hash2);
//				assertEquals(false, isHashMatched);
//				log.info("\t+ isMatch : {}", isHashMatched);
//				assertEquals(true, isPasswordMatched);
//				log.info("\t+ isMatch : {}", isPasswordMatched);
//			} // if
//		} // for
		
		for(int i = 0; i <= 100; i++) {
			String hash2 = this.encoder.encode(password);
			
			boolean isMatched2 = this.encoder.matches(password, hash2);
			
			assertEquals(true, isMatched2);
		} // for 
	} // testPasswordMatching
	
//	@Disabled
	@Order(4)
	@Test
	@RepeatedTest(1)
	@DisplayName("4. 같은 암호에 대해서, 2개의 해쉬를 생성하고, 각각해쉬와 암호를 비교")
	@Timeout(1L)
	void testPasswordMatchingWithTwoHash() {
		log.trace("testPasswordMatchingWithTwoHash() invoked.");
		String originalPassword = "Yoon12334@";
		String salt = "__SALT__";
		
		String password = salt + originalPassword;
		
		String hash1 = this.encoder.encode(password);
		String hash2 = this.encoder.encode(password);
		log.info("\t+ hash1 : {}", hash1);
		log.info("\t+ hash2 : {}", hash2);
		
		boolean isMatched = this.encoder.matches(hash1, hash2);
		log.info("\t+ isMatched : {}", isMatched);
		
		assertEquals(true, isMatched);
	} // testPasswordMatchingWithTwoHash

} // end class
