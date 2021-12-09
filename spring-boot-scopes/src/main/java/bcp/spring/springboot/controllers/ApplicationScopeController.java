package bcp.spring.springboot.controllers;

import static bcp.spring.springboot.util.LogUtil.CALLED_MSG;
import static bcp.spring.springboot.util.LogUtil.identity;
import static org.apache.log4j.component.helpers.MessageFormatter.format;

import javax.inject.Inject;
import javax.inject.Provider;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.ApplicationScope;

import bcp.spring.springboot.services.PrototypeService;
import bcp.spring.springboot.services.RequestScopeService;
import lombok.extern.slf4j.Slf4j;

@RestController
@ApplicationScope
@Slf4j
public class ApplicationScopeController {

  @Inject
  // injected once when this controller is created
  PrototypeService prototypeService;

  @GetMapping("/applicationControllerWithPrototypeService")
  public String serveRequest() {
    String msg = format(CALLED_MSG, identity(this), identity(prototypeService));

    log.debug(msg);
    return msg;
  }

  @Inject
  Provider<RequestScopeService> requestScopeServiceProvider;

  @RequestMapping("/applicationControllerWithProvidedService")
  public String serveRequestWithProvidedService() {
    String msg = format(CALLED_MSG, identity(this), identity(requestScopeServiceProvider.get()));

    log.debug(msg);
    return msg;
  }
  
}


