package dto;

public class InfoResponseDTO {
    private String version;
    private String senderId;
    private String receiverId;
    private Integer messageType;
    private long sendDate;
    private String messageId;
    private String referenceMessageId;
    private Integer responseCode;
    private String responseMessage;
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	public String getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}
	public Integer getMessageType() {
		return messageType;
	}
	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}
	public long getSendDate() {
		return sendDate;
	}
	public void setSendDate(long sendDate) {
		this.sendDate = sendDate;
	}
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public String getReferenceMessageId() {
		return referenceMessageId;
	}
	public void setReferenceMessageId(String referenceMessageId) {
		this.referenceMessageId = referenceMessageId;
	}
	public Integer getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public InfoResponseDTO(String version, String senderId, String receiverId, Integer messageType, long sendDate,
			String messageId, String referenceMessageId, Integer responseCode, String responseMessage) {
		super();
		this.version = version;
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.messageType = messageType;
		this.sendDate = sendDate;
		this.messageId = messageId;
		this.referenceMessageId = referenceMessageId;
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
	}
	public InfoResponseDTO() {
		super();
	}

}
