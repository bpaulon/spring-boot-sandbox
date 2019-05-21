package bcp.spring


import static bcp.spring.RestServices.*
import static bcp.spring.RestServices.HttpCodes.*

import org.apache.http.client.HttpResponseException

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import spock.lang.Specification
import spock.lang.Stepwise


public class EnumConverterSpecification extends Specification {

	def "Should resolve enum using the converter" () {
		setup:

		when:
			def response = CLIENT.get(path: "/sandbox/enumconverter",  query:['category':'category1'])
		then:
			response.status == 200
		and:
			def json=new JsonBuilder(response.data).toPrettyString()
			println json
		and: 
			'category1' == response.data['code']
			'CATEGORY_1' == response.data['name']
		and:
			def jsonSlurper = new JsonSlurper()
			def map = jsonSlurper.parseText(json);
			println map.getClass()
	}
}