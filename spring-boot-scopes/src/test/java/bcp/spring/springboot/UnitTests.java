package bcp.spring.springboot;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import bcp.spring.springboot.services.InjectedService;
import bcp.spring.springboot.services.SingletonService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SingletonService.class, 
    InjectedService.class})
public class UnitTests {

  @Inject
  SingletonService service;
  
  @Test
  void testSingleton() {
    service.doStuff();
  }
  
}



