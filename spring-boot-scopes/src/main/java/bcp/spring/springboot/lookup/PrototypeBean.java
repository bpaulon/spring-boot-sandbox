package bcp.spring.springboot.lookup;

import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class PrototypeBean {

  @Inject
  InjectedBean injectedBean;
  
  public void doStuff() {
    // do stuff
  }
}
