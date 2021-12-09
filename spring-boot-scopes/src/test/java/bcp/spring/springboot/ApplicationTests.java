package bcp.spring.springboot;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import bcp.spring.springboot.services.SingletonService;

@SpringBootTest
class ApplicationTests {

  @Inject
  SingletonService service;
  
	@Test
	void testSingleton() {
	  service.doStuff();
	}

}
