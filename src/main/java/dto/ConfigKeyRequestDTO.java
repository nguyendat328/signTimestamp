package dto;

public class ConfigKeyRequestDTO {
	private String type;
	private String value;

	public ConfigKeyRequestDTO(String type, String value) {
		super();
		this.type = type;
		this.value = value;
	}

	public ConfigKeyRequestDTO() {
		super();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
