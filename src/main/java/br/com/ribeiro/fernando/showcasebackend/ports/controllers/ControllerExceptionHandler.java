package br.com.ribeiro.fernando.showcasebackend.ports.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.ribeiro.fernando.showcasebackend.domain.entities.responses.error.ErrorResponse;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleException(MethodArgumentNotValidException exception) {
		
		String message = exception.getMessage();
		
		String error = message
			.subSequence(message.indexOf("arguments [];"), message.length())
			.toString();
		
		return new ErrorResponse(error, HttpStatus.BAD_REQUEST);
		
    }
	
	@ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleException(IllegalArgumentException exception) {
		return new ErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ErrorResponse handleException(AccessDeniedException exception) {
		return new ErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
