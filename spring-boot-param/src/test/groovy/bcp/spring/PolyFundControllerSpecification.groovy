package bcp.spring

import groovy.json.JsonBuilder
import spock.lang.Specification
import spock.lang.Stepwise
import static bcp.spring.RestServices.*
import static groovyx.net.http.ContentType.JSON

public class PolyFundControllerSpecification extends Specification {

	def setupSpec() {
	}


	def "Create Fund of type Hedge Fund" () {
		setup: 'create a Json message for the entity'
			def json = new JsonBuilder()
			json {
				type 'hf'
				hedgeFundAttribute 'value for heProp'
			}

		when:
			def response = CLIENT.post(path: '/sandbox/funds', body: json.toString(), requestContentType: JSON)

		then:
			response.status == 201
	}


	def "Create Fund of type Real Estate should return status 201" () {
		setup: 'create a Json message for the entity'
			def json = new JsonBuilder()
			json {
				type 're'
				reProp 'value for reProp'
			}

		when:
			def response = CLIENT.post(path: '/sandbox/funds', body: json.toString(), requestContentType: JSON)

		then:
			response.status == 201
	}
}
