package bcp.spring.jpa;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Optional;

import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
// Configure an in-memory embedded database
@DataJpaTest
public class TestCustomerRepository {

  @Autowired
  private TestEntityManager em;

  @Autowired
  private CustomerRepository employeeRepository;

  @Before
  public void setup() {
    Query q2 = em.getEntityManager().createNativeQuery("DELETE FROM sample_schema.customer");
    q2.executeUpdate();
    
    Query q3 = em.getEntityManager().createNativeQuery("alter sequence customer_seq restart with 1");
    q3.executeUpdate();
  }
  
  @Test
  public void testPersist() {
    Customer c1 = Customer.builder().firstName("Fae").secondName("Kinit").build();
    Customer c2 = Customer.builder().firstName("Ice").secondName("Dumas").build();
    em.persist(c1);
    em.persist(c2);

    assertEquals(2, employeeRepository.findAll().size());
  }
  
  @Test
  public void testFindPolymorphic() {
    Customer c1 = Customer.builder().firstName("Fae").secondName("Kinit").build();
    Customer c2 = Customer.builder().firstName("Ice").secondName("Dumas").build();
    Customer c3 = new GoldCustomer();
    c3.firstName = "golden";
    c3.secondName = "eye";
    
    em.persist(c1);
    em.persist(c2);
    em.persist(c3);
    
    
    assertEquals(3, employeeRepository.findAll().size());
    
    List<Customer> customers = employeeRepository.findAll();
    customers.stream().forEach(c -> {
      System.out.println(c.getClass() + " - "+ c.toString());
    });
    
    Optional<Customer> customer = employeeRepository.findById(3L);
    System.out.println(customer.get().getClass() + " - " + customer.get());
    System.out.println(customer.get() instanceof GoldCustomer);
  }

  /**
   * Test query using custom MySql syntax
   * It should ignore existing records
   */
  @Test
  public void testExecuteQuery() {
    System.out.println(Customer_.firstName);
    Customer c1 = Customer.builder().firstName("Fae").secondName("Kinit").build();
    em.persist(c1);

    Query query = em.getEntityManager().createNativeQuery(
        "SET MODE MYSQL; insert ignore into sample_schema.customer (id, first_name, second_name) values(" + c1.getId()
            + ", 'fanel', 'fanel@sync.ro');");

    query.executeUpdate();

    assertEquals(1, employeeRepository.findAll().size());
  }
}
