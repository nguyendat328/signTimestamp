package dto;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class DataResponseDTO {
	private String timestampToken;
	private ZonedDateTime timestamp;
	private String algorithm;

	public DataResponseDTO(String timestampToken, ZonedDateTime timestamp, String algorithm) {
		super();
		this.timestampToken = timestampToken;
		this.timestamp = timestamp;
		this.algorithm = algorithm;
	}

	public DataResponseDTO() {
		super();
	}

	public String getTimestampToken() {
		return timestampToken;
	}

	public void setTimestampToken(String timestampToken) {
		this.timestampToken = timestampToken;
	}

	public ZonedDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(ZonedDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

}
