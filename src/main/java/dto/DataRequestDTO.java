package dto;


public class DataRequestDTO {
    private String digest;

    private String algorithm;

	public DataRequestDTO(String digest, String algorithm) {
		super();
		this.digest = digest;
		this.algorithm = algorithm;
	}

	public DataRequestDTO() {
		super();
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public String getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

}
