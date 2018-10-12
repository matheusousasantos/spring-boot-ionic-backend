package com.matheus.cusomc.resources.exception;

import java.util.ArrayList;
import java.util.List;

/* Essa classe herda tudo da StandardError e adiciona uma lista */
public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;
	
	List<FieldMessage> list = new ArrayList<>();
	
	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
	}

	public List<FieldMessage> getErros() {
		return list;
	}

	public void addError(String fieldName, String message) {
		list.add(new FieldMessage(fieldName, message)); // Aicionando em uma lista.
	}
}
