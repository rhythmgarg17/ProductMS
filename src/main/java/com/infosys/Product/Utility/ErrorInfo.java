package com.infosys.Product.Utility;

import java.time.LocalDateTime;

public class ErrorInfo {

	private String errorMessage;

	private Integer errorCode;

	private LocalDateTime timestamp;

	private String pathUri;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getPathUri() {
		return pathUri;
	}

	public void setPathUri(String pathUri) {
		this.pathUri = pathUri;
	}

}
