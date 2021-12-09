package bcp.spring.springboot.simple;

import javax.inject.Inject;

public class SpringBean {

  @Inject
  private MyService myService;
  
  @Inject
  public SpringBean(MyService myService) {
    this.myService = myService;
  }
  
  @Inject
  public void setMyService(MyService myService) {
    this.myService = myService;
  }
  
  public void doStuff() {
    myService.doStuff();
  }
  
}
