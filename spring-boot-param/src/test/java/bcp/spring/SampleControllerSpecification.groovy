package bcp.spring


import static bcp.spring.RestServices.*
import static bcp.spring.RestServices.HttpCodes.*
import groovy.json.JsonBuilder
import net.sf.json.JSON
import net.sf.json.test.JSONAssert

import org.apache.http.client.HttpResponseException

import spock.lang.Specification
import spock.lang.Stepwise

@Stepwise
public class SampleControllerSpecification extends Specification {

	
	def "Getting home page should return HTTP OK" () {
		when:
			def response = CLIENT.get(path: "/???")
		then:
			HttpResponseException ex = thrown()
			ex.statusCode == 404
	}
	
	
	def "Get the home page should return the passed in correlation headers" () {
		setup:
			def expected = "bla"
		when:
			def response = CLIENT.get(path: "/sandbox/", headers:['correlation-Id': '001', 'user-id':'abc'])
		then:
			the response is HTTP_OK
		and:
			def json = new JsonBuilder(response.data).toPrettyString()
			println json
	}
	
}
