package bcp.spring.polymorphism;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode (callSuper = true)
public class HedgeFund extends Fund {

	private String hedgeFundAttribute;

}
