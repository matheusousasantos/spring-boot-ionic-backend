package com.matheus.cusomc.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.matheus.cusomc.services.exceptions.ConstraintViolationException;
import com.matheus.cusomc.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	/* Toda vez que uma exceção é chamada essa classe é responsável por definir o que vai ser mostado quando o erro
	específico ocorrer */
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<StandardError> violationException(ConstraintViolationException e, HttpServletRequest request){
		
		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	/* Alem em mostrar status,msg e o timeStamp vou mostrar a lista de erro - mostrando o nome do campo e o erro referênte 
	 * Vou precisar de uma classe auxiliar para carregar esses campos - FieldMessage */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request){
		
		ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de Validação!", System.currentTimeMillis());
		
		/* Vou percorrer a lista que já tem padrão do framework(e)*/
		
		for(FieldError x : e.getBindingResult().getFieldErrors()) { /*Pra cada erro que tiver na lista de erro dessa exceção(e)
		eu vou gerar o meu objeto FieldMessager */
			err.addError(x.getField(), x.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

}
