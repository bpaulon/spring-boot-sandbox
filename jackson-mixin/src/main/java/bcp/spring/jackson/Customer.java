package bcp.spring.jackson;

import lombok.experimental.Accessors;

@Accessors(fluent = true)
@lombok.Getter
@lombok.Setter
@lombok.ToString
public class Customer {

	private String id;
	private String firstName; 
	private String secondName;
	
}
