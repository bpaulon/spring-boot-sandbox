package bcp.spring.enumconverter;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController("EnumConverterDemoController")
@Slf4j
public class SampleController {

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		dataBinder.registerCustomEditor(CategoryEnum.class, new GenericEnumConverter<CategoryEnum>(CategoryEnum.class));
	}

	@RequestMapping(value = "/enumconverter")
	@ResponseStatus(value = HttpStatus.OK)
	public Map<?, ?> convert(CategoryEnum category) {
		log.info("Passed in category is:{} " + category.name());

		Map<String, String> categoryParameter = new HashMap<>();
		categoryParameter.put("name", category.name());
		categoryParameter.put("class", category.getClass().getCanonicalName());
		categoryParameter.put("code", category.getCode());
		categoryParameter.put("toString", category.toString());

		return categoryParameter;
	}

}
