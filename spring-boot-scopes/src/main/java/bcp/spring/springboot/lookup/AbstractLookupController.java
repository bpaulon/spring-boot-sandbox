package bcp.spring.springboot.lookup;

import static bcp.spring.springboot.util.LogUtil.CALLED_MSG;
import static bcp.spring.springboot.util.LogUtil.identity;
import static org.apache.log4j.component.helpers.MessageFormatter.format;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public abstract class AbstractLookupController {
  
  @GetMapping("/abstractLookupController")
  public String serveRequest() {
    PrototypeBean prototypeBean = getPrototypeBean();
    String msg = format(CALLED_MSG, identity(this), identity(prototypeBean));
    log.debug(msg);
    log.debug("{}", identity(prototypeBean.injectedBean));
    return msg;
  }
  
  @Lookup
  public abstract PrototypeBean getPrototypeBean();
  
}
