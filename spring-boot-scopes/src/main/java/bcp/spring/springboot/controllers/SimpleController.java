package bcp.spring.springboot.controllers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import bcp.spring.springboot.services.SingletonService;

@RestController
public class SimpleController {

  @Autowired
  private SingletonService service;
  
  @GetMapping("/simpleController")
  public String findUser() {
    service.doStuff();
    return "DONE " + LocalDateTime.now();
  }
}
