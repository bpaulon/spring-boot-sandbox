package bcp.spring.enumconverter;

import java.beans.PropertyEditorSupport;
import java.util.*;

public class GenericEnumConverter<T extends Enum<T> & EnumParameter> extends PropertyEditorSupport {

	private List<T> enumValues = new ArrayList<T>();

	public GenericEnumConverter(Class<T> typeParameterClass) {
		for (T option : typeParameterClass.getEnumConstants()) {
			enumValues.add(option);
		}
	}

	public Optional<T> getByCode(String code) {
		return enumValues.stream().filter(f -> f.getCode().equals(code)).findFirst();
	}

	@Override
	public void setAsText(String text) {
		Optional<T> valueOptional = getByCode(text);
		T value = valueOptional.orElseThrow(() -> new IllegalArgumentException("No enum constant identified by: "
				+ text));
		setValue(value);
	}

}
