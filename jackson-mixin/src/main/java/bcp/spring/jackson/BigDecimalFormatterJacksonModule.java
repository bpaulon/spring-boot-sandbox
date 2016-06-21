package bcp.spring.jackson;

import java.io.IOException;
import java.math.BigDecimal;

import javax.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;


/**
 * 
 * This component registers a global Jackson serializer for the BigDecimal fields.
 * 
 */
@Component
@Slf4j
public class BigDecimalFormatterJacksonModule extends WebMvcAutoConfiguration  {
	
	final static int SERIALIZER_SCALE = 10;
	
	@Autowired
	ObjectMapper jsonMapper;
	
	
	@PostConstruct
	public Module registerModule() {
		SimpleModule module = new SimpleModule();
		module.addSerializer(BigDecimal.class, new JsonSerializer<BigDecimal>() {

			
			/**
			 * Limit the max number of decimals to 10
			 */
			@Override
			public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializers)
					throws IOException, JsonProcessingException {
				int scale = value.scale();
				if (scale > SERIALIZER_SCALE) {
					scale = SERIALIZER_SCALE;
				}
				gen.writeNumber(value.setScale(scale, BigDecimal.ROUND_HALF_UP));
			}
		   
		});
		
		jsonMapper.registerModule(module);
		
		log.info("Big decimal formatting module (scale={}) created and registered", SERIALIZER_SCALE);
		return module;
	}
	
}
