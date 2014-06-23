package com.skt.apollo.models.common;

public class ErrorCodeException extends RuntimeException {

	private static final long serialVersionUID = 3683324441095798742L;

	public ErrorCode errorCode;

	public ErrorCodeException(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}
	
	public ErrorCodeException(ErrorCode errorCode, Exception cause) {
		this.errorCode = errorCode;
		this.initCause(cause);
	}
	
}
