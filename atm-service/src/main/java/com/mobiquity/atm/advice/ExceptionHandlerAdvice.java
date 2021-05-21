package com.mobiquity.atm.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mobiquity.atm.exception.AtmNotFoundException;
import com.mobiquity.atm.exception.RootException;
import com.mobiquity.atm.response.BaseResponse;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

	@ExceptionHandler(AtmNotFoundException.class)
	public final ResponseEntity<BaseResponse> handleAtmNotFoundException(AtmNotFoundException ex) {
		return getResponseEntity(ex, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(RootException.class)
	public final ResponseEntity<BaseResponse> handleRootException(RootException ex) {
		return getResponseEntity(ex, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private ResponseEntity<BaseResponse> getResponseEntity(RootException ex, HttpStatus status) {
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setDescription(ex.getDescription());
		baseResponse.setErrorCode(ex.getErrorCode());
		return new ResponseEntity<BaseResponse>(baseResponse, status);
	}

}
