package bcp.spring.argresolver;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

//@RestController
@Slf4j
public class SampleController {

	//@RequestMapping(value="/"/*, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE*/)
	//@ResponseStatus(value = HttpStatus.OK)
	public Correlation greet(Correlation correlation) {
		log.debug("correlationId:{}, userId:{}", correlation.getCorrelationId(), correlation.getUserId());
		
		return correlation;
	}

}
