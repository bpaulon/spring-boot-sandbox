package bcp.spring.springboot.beanfactory;

import static bcp.spring.springboot.util.LogUtil.CALLED_MSG;
import static bcp.spring.springboot.util.LogUtil.identity;
import static org.apache.log4j.component.helpers.MessageFormatter.format;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import bcp.spring.springboot.services.RequestScopeService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class BeanFactoryAwareController {

  @Autowired
  private BeanFactory beanFactory;

  @GetMapping("/beanFactoryAwareControllerWithRequestService")
  public String serveRequest() {
    RequestScopeService service = beanFactory.getBean(RequestScopeService.class);
    String msg = format(CALLED_MSG, identity(this), identity(service));
    log.debug(msg);
    return msg;
  }
}
