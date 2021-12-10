package bcp.spring.springboot.services;

import static bcp.spring.springboot.util.LogUtil.CALLED_MSG;
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
    // Bean creation
  }
  
  @PostConstruct
  public void postConstruct() {
    // Called after 
  }
  
  @PreDestroy
  public void preDestroy() {
    log.debug(PREDESTROY_MSG, identity(this));
  }
  
  public void doStuff() {
    log.debug(CALLED_MSG, identity(this));
  }
}
