package bcp.spring;

import static bcp.spring.jpa.CustomerSpecifications.hasFirstName;
import static bcp.spring.jpa.CustomerSpecifications.hasSecondName;
import static org.springframework.data.jpa.domain.Specification.where;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bcp.spring.jpa.Customer;
import bcp.spring.jpa.CustomerRepository;

@RestController
public class SampleController {

	@Autowired
	CustomerRepository repository;
	
	@Autowired
	ApplicationContext ctx;

	@RequestMapping("/")
	public List<Customer> getCustomers() {
		Customer customer = Customer.builder().firstName("John").secondName("Ripper").build();
		repository.save(customer);

		Specification<Customer> hasFirstNameAndSecondName = where(hasFirstName("John")).and(hasSecondName("Ripper"));
		List<Customer> customers = repository.findAll(hasFirstNameAndSecondName);
		return customers;
	}
	
	@RequestMapping("/all")
  public List<Customer> getAllCustomers() {
    List<Customer> customers = repository.findAll();
    return customers;
  }
	
}
