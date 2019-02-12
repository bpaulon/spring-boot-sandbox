package bcp.spring;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import bcp.spring.jpa.CustomerRepository;

@Component
public class TotalCustomersInfoContributor implements InfoContributor {

  @Autowired
  CustomerRepository userRepository;

  @Override
  public void contribute(Info.Builder builder) {
      Map<String, Long> userDetails = new HashMap<>();
      userDetails.put("total", userRepository.count());

      builder.withDetail("customers", userDetails);
  }
  
}
