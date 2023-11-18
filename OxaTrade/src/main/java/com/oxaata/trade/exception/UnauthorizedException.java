package com.oxaata.trade.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends ApiException{
    private static final long serialVersionUID = -6571994714217148411L;

		public UnauthorizedException(String message) {
        super(message,"UNAUTHORIZED");
    }
}
