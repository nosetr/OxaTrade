package com.oxaata.trade.exception;

import lombok.Getter;

public class ApiException extends RuntimeException{

    private static final long serialVersionUID = 5195063146082838366L;
		@Getter
    protected String errorCode;

    public ApiException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
