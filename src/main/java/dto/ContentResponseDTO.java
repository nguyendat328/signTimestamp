package dto;

public class ContentResponseDTO {
	private String transactionId;
	private DataResponseDTO data;

	public ContentResponseDTO(String transactionId, DataResponseDTO data) {
		super();
		this.transactionId = transactionId;
		this.data = data;
	}

	public ContentResponseDTO() {
		super();
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public DataResponseDTO getData() {
		return data;
	}

	public void setData(DataResponseDTO data) {
		this.data = data;
	}

}
