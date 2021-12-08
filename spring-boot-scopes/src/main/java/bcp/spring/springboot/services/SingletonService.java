package bcp.spring.springboot.services;

import static bcp.spring.springboot.util.LogUtil.CONSTRUCTOR_MSG;
import static bcp.spring.springboot.util.LogUtil.POSTCONSTRUCT_MSG;
import static bcp.spring.springboot.util.LogUtil.identity;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SingletonService {

  @Inject
  public InjectedService injectedService;
  
  public SingletonService() {
    log.debug(CONSTRUCTOR_MSG, identity(this));
  }

  public void doStuff() {
    log.debug("doStuff called: {}", identity(this));
  }
  
  @PostConstruct 
  public void postConstruct() {
    log.debug(POSTCONSTRUCT_MSG, identity(this));
    log.debug("injected field in postConstruct {}", injectedService);
  }
  
}
