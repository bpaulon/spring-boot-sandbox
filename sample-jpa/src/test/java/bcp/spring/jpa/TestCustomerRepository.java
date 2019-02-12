package bcp.spring.jpa;

import static org.junit.Assert.assertEquals;

import javax.persistence.Query;

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

  @Test
  public void testPersist() {
    Customer c1 = Customer.builder().firstName("Fae").secondName("Kinit").build();
    Customer c2 = Customer.builder().firstName("Ice").secondName("Dumas").build();
    em.persist(c1);
    em.persist(c2);

    assertEquals(2, employeeRepository.findAll().size());
  }

  /**
   * Test query using custom MySql syntax
   */
  @Test
  public void testExecuteQuery() {
    Customer c1 = Customer.builder().firstName("Fae").secondName("Kinit").build();
    em.persist(c1);

    Query query = em.getEntityManager().createNativeQuery(
        "SET MODE MYSQL; insert ignore into sample_schema.customer (id, first_name, second_name) values(" + c1.getId()
            + ", 'fanel', 'fanel@sync.ro');");

    query.executeUpdate();

    assertEquals(1, employeeRepository.findAll().size());
  }
}
