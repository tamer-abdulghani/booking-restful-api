package com.miage.models.exception;

public class ExceptionInfo {
    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getUrl() {
        return url;
    }

    private final int code;
	private final String message;
	private final String url;

	public ExceptionInfo(int code, String message, String url) {
		this.code = code;
		this.message = message;
		this.url = url;
	}
}
