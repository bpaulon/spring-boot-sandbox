package bcp.spring.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "CUSTOMER", schema = "SAMPLE_SCHEMA")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN")
	@SequenceGenerator(name = "SEQ_GEN", sequenceName = "CUSTOMER_SEQ", allocationSize = 1)
	Long id;
	
	@Column(name="FIRST_NAME")
	String firstName;
	
	String secondName;
	
}
