package bcp.spring.jpa;

import org.springframework.data.jpa.domain.Specification;
 
public class CustomerSpecifications {

	public static Specification<Customer> hasFirstName(String name) {
		return (root, query, cb) -> cb.equal(root.get(Customer_.firstName), name);
	}
	
	public static Specification<Customer> hasSecondName(String name) {
		return (root, query, cb) -> cb.equal(root.get(Customer_.secondName), name);
	}
}
