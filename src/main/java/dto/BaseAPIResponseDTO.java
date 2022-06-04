package dto;

public class BaseAPIResponseDTO<T, U> {
    private T content;
    private U info;
	public BaseAPIResponseDTO(T content, U info) {
		super();
		this.content = content;
		this.info = info;
	}
	public BaseAPIResponseDTO() {
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
    
    
}
