package bcp.spring.springboot.config;

import static bcp.spring.springboot.util.LogUtil.CREATED_MSG;
import static bcp.spring.springboot.util.LogUtil.identity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import bcp.spring.springboot.services.PrototypeService;
import bcp.spring.springboot.services.SingletonService;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@Configuration
public class ApplicationConfiguration {
  
  @Bean
  public SingletonService produceSingletonService() {
    SingletonService service = new SingletonService();
    log.debug(CREATED_MSG, identity(service));
    // service will be initialized by Spring after it is returned from 
    // this method
    return service;
  }

  @Bean
  @Scope("prototype")
  public PrototypeService producePrototypeService() {
    PrototypeService prototypeService = new PrototypeService();
    log.debug(CREATED_MSG, identity(prototypeService));
    return prototypeService;
  }
}
