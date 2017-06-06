package bcp.spring;

import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@Slf4j
@ComponentScan(basePackages = {"bcp.spring"})
public class Application {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
		printBeanNames(ctx);
	}

	public static void printBeanNames(ApplicationContext ctx) {
		log.info("Beans provided by Spring Boot:");

		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.asList(beanNames).stream().sorted().forEach(beanName -> log.info(beanName));
	}
}
