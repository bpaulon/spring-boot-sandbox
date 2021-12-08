package bcp.spring.springboot.services;

import static bcp.spring.springboot.util.LogUtil.CONSTRUCTOR_MSG;
import static bcp.spring.springboot.util.LogUtil.POSTCONSTRUCT_MSG;
import static bcp.spring.springboot.util.LogUtil.identity;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
// No need to annotate this class with @Component. Instances of this class
// are produced by the provider in ApplicationResources

public class PrototypeService {

  public PrototypeService() {
    log.debug(CONSTRUCTOR_MSG, identity(this));
  }
  
  public void doStuff() {
    log.debug("doStuff");
  }

  @PostConstruct
  public void postConstruct() {
    log.debug(POSTCONSTRUCT_MSG, identity(this));
  }
  
  @PreDestroy
  public void preDestroy() {
    log.debug("this is not called in prototypes");
  }
}
