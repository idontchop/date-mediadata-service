package com.idontchop.datemediadataservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.idontchop.datemediadataservice.services.JwtTokenService;

@SpringBootTest
class DateMediadataServiceApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
	void testJwt() {
		System.out.println(JwtTokenService.buildToken("6063ca14dc9b033001e118f4"));
	}

}
