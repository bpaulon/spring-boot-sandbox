package bcp.spring.springboot.controllers;

import static bcp.spring.springboot.util.LogUtil.CALLED_MSG;
import static bcp.spring.springboot.util.LogUtil.identity;
import static org.apache.log4j.component.helpers.MessageFormatter.format;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import bcp.spring.springboot.services.PrototypeService;
import bcp.spring.springboot.services.RequestScopeService;
import bcp.spring.springboot.services.SingletonService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestScope
@Slf4j
public class RequestScopeController {

  @Inject
  // The same object is injected in each instance of this class
  private SingletonService singletonService;
  
  @RequestMapping("/requestControllerWithSingletonService")
  public String serveRequest() {
    String msg = format(CALLED_MSG, identity(this), identity(singletonService));
    log.debug(msg);
    return msg;
  }
  
  
  @Inject
  // new object is injected for each request
  PrototypeService prototypeService;
  
  @RequestMapping("/requestControllerWithPrototypeService")
  public String doStuff() {
    String msg = format(CALLED_MSG, identity(this), identity(prototypeService));
    log.debug(msg);
    return msg;
  }
  
  
  @Inject
  RequestScopeService requestScopeService;
  
  @RequestMapping("/requestControllerWithRequestScopeService")
  public String serveRequestWithRequestScopedService() {
    log.debug(CALLED_MSG, identity(this), identity(requestScopeService));
    return format(CALLED_MSG, identity(this), identity(requestScopeService));
  }
}
