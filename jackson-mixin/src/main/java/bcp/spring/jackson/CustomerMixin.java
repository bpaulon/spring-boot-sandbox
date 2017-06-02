package bcp.spring.jackson;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(value = { "secondName, firstName" })
@JsonFilter("propertyAnnotatedFilter")
public class CustomerMixin {

	@JsonProperty("firstName")
	private String firstName;
	
	@JsonProperty("familyName")
	private String secondName; 
	
}
