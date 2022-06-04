package dto;


public class ContentRequestDTO {
    private String transactionId;
    private DataRequestDTO data;
	public ContentRequestDTO(String transactionId, DataRequestDTO data) {
		super();
		this.transactionId = transactionId;
		this.data = data;
	}
	public ContentRequestDTO() {
		super();
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public DataRequestDTO getData() {
		return data;
	}
	public void setData(DataRequestDTO data) {
		this.data = data;
	}
    
    
}


