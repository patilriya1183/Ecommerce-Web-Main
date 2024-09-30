package com.ecommerce.webmain;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ExceptionClass extends RuntimeException {
	
	public ExceptionClass(String msg) {
		super(msg);
	}

}
