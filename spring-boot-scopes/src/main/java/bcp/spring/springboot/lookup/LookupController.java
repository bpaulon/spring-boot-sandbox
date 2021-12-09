package bcp.spring.springboot.lookup;

import static bcp.spring.springboot.util.LogUtil.*;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class LookupController {

  @RequestMapping("/lookupController")
  public void serveRequest() {
    PrototypeBean prototypeBean = getPrototypeBean();
    log.debug(CALLED_MSG, identity(this), identity(prototypeBean));
    log.debug("{}", identity(prototypeBean.injectedBean));
  }

  @Lookup
  public PrototypeBean getPrototypeBean() {
    // this method is not called by the enhanced Spring controller
    // it instructs Spring to create a factory method to produce PrototypeBean
    // objects
    return null;
  }

}
