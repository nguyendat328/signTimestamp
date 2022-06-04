package dto;

public class BaseAPIRequestDTO<T, U> {
	private T content;
	private U info;
	private String signature;

	public BaseAPIRequestDTO(T content, U info, String signature) {
		super();
		this.content = content;
		this.info = info;
		this.signature = signature;
	}

	public BaseAPIRequestDTO() {
		super();
	}

	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}

	public U getInfo() {
		return info;
	}

	public void setInfo(U info) {
		this.info = info;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

}
