package com.skt.apollo.models.common;


public class ErrorCode {


	public static class _400 {

		public static final ErrorCode _4001 = new ErrorCode( 400, "시스템 코드가 없습니다.");
		public static final ErrorCode _4002 = new ErrorCode( 400, "데이터 타입이 없습니다.");
		public static final ErrorCode _4003 = new ErrorCode( 400, "파일명이 없습니다.");
		public static final ErrorCode _4004 = new ErrorCode( 400, "Invalid Request Type");
		public static final ErrorCode _4005 = new ErrorCode( 400, "잘못된 접근입니다.");
		public static final ErrorCode _4006 = new ErrorCode( 400, "파일경로가 없습니다.");

		
		
		public static final ErrorCode _4063 = new ErrorCode( 406, "does not support Content-Type" );
		public static final ErrorCode _4101 = new ErrorCode( 400, "IOError writing file to output stream");
		public static final ErrorCode _IRIS0001 = new ErrorCode( 400, "IRIS DB DataServer Protocol Error");

	}

	public static class _500 {

	}


	public String message;

	public int statusCode;
	
	public ErrorCode(  int statusCode ) {
		this.statusCode = statusCode;
	}
	

	public ErrorCode(  int statusCode , String message) {
		this.message = message;
		this.statusCode = statusCode;
	}

	public ErrorCodeException asException() {
		return new ErrorCodeException(this);
	}
	
	public ErrorCodeException asException(Exception cause) {
		return new ErrorCodeException(this, cause);
	}

}
