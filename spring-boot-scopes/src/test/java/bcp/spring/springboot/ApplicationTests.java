package bcp.spring.springboot;

import javax.inject.Inject;

import org.springframework.boot.test.context.SpringBootTest;

import bcp.spring.springboot.services.SingletonService;

@SpringBootTest
class ApplicationTests {

  @Inject
  SingletonService service;
  
	@org.junit.jupiter.api.Test
	void testSingleton() {
	  service.doStuff();
	}

}
