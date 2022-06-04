package dto;

public class InfoRequestDTO {

    private String version;
    private String senderId;
    private String receiverId;
    private Integer messageType;
    private long sendDate;
    private String messageId;
	public InfoRequestDTO(String version, String senderId, String receiverId, Integer messageType, long sendDate,
			String messageId) {
		super();
		this.version = version;
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.messageType = messageType;
		this.sendDate = sendDate;
		this.messageId = messageId;
	}
	public InfoRequestDTO() {
		super();
	}
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

}
