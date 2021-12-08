package bcp.spring;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableScheduling
@Slf4j
public class Application {

  private static ConfigurableApplicationContext context;

  public static void main(String[] args) {

    Map<String, String> env = System.getenv();
    log.debug("Env variables: {}", env);

    context = SpringApplication.run(Application.class, args);

    log.info("App context {} started.", context.getApplicationName());
  }

  static LocalTime lastDetectedTime;
  static long delta = 1;
  static final long CHECK_INTERVAL = 5000;
  
  @Scheduled(fixedDelay = CHECK_INTERVAL, initialDelay = CHECK_INTERVAL)
  public static void restartIfTimeMismatch() {

    if(lastDetectedTime == null) {
      lastDetectedTime = LocalTime.now();
    }
    LocalTime currentTime = LocalTime.now();
    long millis = Duration.between(lastDetectedTime, currentTime).toMillis();
    lastDetectedTime = currentTime;
    
    System.out.println(millis);
    if(millis > CHECK_INTERVAL + delta) {
      System.out.println("Detected time mismatch. Restart");
      Thread thread = new Thread(() -> {
        context.close();
        context = SpringApplication.run(Application.class);
      });

      lastDetectedTime = null;
      thread.setDaemon(false);
      thread.start();
      
    }
  }
}
