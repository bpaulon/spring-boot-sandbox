package bcp.spring.springboot.lookup;

import static bcp.spring.springboot.util.LogUtil.CALLED_MSG;
import static bcp.spring.springboot.util.LogUtil.identity;
import static org.apache.log4j.component.helpers.MessageFormatter.format;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public abstract class AbstractLookupController {
  
  @RequestMapping("/abstractLookupController")
  public String serveRequest() {
    PrototypeBean prototypeBean = getPrototypeBean();
    log.debug(CALLED_MSG, identity(this), identity(prototypeBean));
    log.debug("{}", identity(prototypeBean.injectedBean));
    return format(CALLED_MSG, identity(this), identity(prototypeBean));
  }
  
  @Lookup
  public abstract PrototypeBean getPrototypeBean();
  
}
