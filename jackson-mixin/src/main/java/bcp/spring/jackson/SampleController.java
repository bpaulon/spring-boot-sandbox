package bcp.spring.jackson;

import javax.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@Slf4j
public class SampleController {
	
	@Autowired
	ObjectMapper objectMapper;

	@PostConstruct
	public void registerMixin() {
		/*
		 * Spring throws java.lang.IllegalArgumentException: No converter found for return 
		 * value of type: class bcp.spring.jackson.Customer without the Mix-in. 
		 * Jackson does not know how to to serialize it because the class has fluent properties
		 * and not getters and setters
		 */
		objectMapper.addMixIn(Customer.class, CustomerMixin.class);
	}
	
	@RequestMapping(value="/"/*, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE*/)
	@ResponseStatus(value = HttpStatus.OK)
	public Customer serviceMethod() {
		Customer customer = new Customer();
		customer.id("007");
		customer.firstName("Mario");
		customer.secondName("Super");
		
		log.debug("Created customer: {}", customer);
		return customer;
	}

}