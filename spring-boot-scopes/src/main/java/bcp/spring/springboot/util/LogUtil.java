package bcp.spring.springboot.util;

public class LogUtil {

  public static final String CREATED_MSG = "--> CREATED {}";
  public static final String PREDESTROY_MSG = "/// PreDestroy: {}";
  public static final String POSTCONSTRUCT_MSG = "/// PostConstruct: {}";
  public static final String CONSTRUCTOR_MSG = "/// Constructor {}";
  public static final String CALLED_MSG = ">> called {} - {}";
  
  public static String identity(Object obj) {
    if(obj == null) {
      return "null";
    }
    return obj.getClass().getSimpleName() + " [" + System.identityHashCode(obj) + "]";
  }
  
}
