package bcp.spring.jpa;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;
import lombok.ToString;

@Entity(name="products")
@DiscriminatorValue("1")
@Data
@ToString(callSuper=true, includeFieldNames=true)
public class GoldCustomer extends Customer {

}
