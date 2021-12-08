package bcp.spring.springboot.services;

import static bcp.spring.springboot.util.LogUtil.CONSTRUCTOR_MSG;
import static bcp.spring.springboot.util.LogUtil.POSTCONSTRUCT_MSG;
import static bcp.spring.springboot.util.LogUtil.PREDESTROY_MSG;
import static bcp.spring.springboot.util.LogUtil.identity;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Service
@Scope(WebApplicationContext.SCOPE_REQUEST)
@Slf4j
public class RequestScopeService {

  public RequestScopeService() {
    log.debug(CONSTRUCTOR_MSG, identity(this));
  }
  
  @PostConstruct
  public void postConstruct() {
    log.debug(POSTCONSTRUCT_MSG, identity(this));
  }
  
  @PreDestroy
  public void preDestroy() {
    log.debug(PREDESTROY_MSG, identity(this));
  }
}
