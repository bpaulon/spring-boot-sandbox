package bcp.spring.enumconverter;

public enum CategoryEnum implements EnumParameter {

	CATEGORY_1("category1"), CATEGORY_2("category2");

	private final String code;

	private CategoryEnum(String code) {
		this.code = code;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String toString() {
		return code;
	}

}
