package bcp.spring;

import static org.springframework.data.jpa.domain.Specifications.where;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bcp.spring.jpa.Customer;
import bcp.spring.jpa.CustomerRepository;
import static bcp.spring.jpa.CustomerSpecifications.*;

@RestController
public class SampleController {

	@Autowired
	CustomerRepository repository;

	@RequestMapping("/")
	public List<Customer> getCustomers() {
		Customer customer = Customer.builder().firstName("John").secondName("Ripper").build();
		repository.save(customer);

		Specification<Customer> hasFirstNameAndSecondName = where(hasFirstName("John")).and(hasSecondName("Ripper"));
		List<Customer> customers = repository.findAll(hasFirstNameAndSecondName);
		return customers;
	}
	
}
