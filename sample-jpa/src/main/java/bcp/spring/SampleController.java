package bcp.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bcp.spring.jpa.Customer;
import bcp.spring.jpa.CustomerRepository;

@RestController
public class SampleController {

	@Autowired
	CustomerRepository repository;

	@RequestMapping("/")
	public List<Customer> getCustomers() {
		Customer customer = Customer.builder().firstName("John").secondName("Ripper").build();
		repository.save(customer);

		List<Customer> customers = repository.findAll();
		return customers;
	}
	
}
