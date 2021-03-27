package br.com.totemti.livraria.configurations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.totemti.livraria.controllers.dto.ErroDTO;
import br.com.totemti.livraria.exceptions.RegistroNaoEncontradoException;

@RestControllerAdvice
public class ResourceExceptionHandler {
	
	private MessageSource messageSource;	
	
	@Autowired
	public ResourceExceptionHandler(MessageSource messageSource) {
		super();
		this.messageSource = messageSource;
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroDTO> handle(MethodArgumentNotValidException exception) {
		List<ErroDTO> erros = new ArrayList<>();
		List<FieldError> errosValidacao = exception.getBindingResult().getFieldErrors();
		
		errosValidacao.forEach(erro -> {
			String mensagem = messageSource.getMessage(erro,  LocaleContextHolder.getLocale());
			ErroDTO erroDTO= new ErroDTO(mensagem, erro.getField());
			
			erros.add(erroDTO);
		});
		
		return erros;
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException.class)
	public ErroDTO handle(IllegalArgumentException exception) {
		String message = messageSource.getMessage(exception.getMessage(), null, LocaleContextHolder.getLocale());
		return new ErroDTO(message);
	}
	

	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(RegistroNaoEncontradoException.class)
	public ErroDTO handle(RegistroNaoEncontradoException exception) {
		String message = messageSource.getMessage(exception.getMessage(), null, LocaleContextHolder.getLocale());
		return new ErroDTO(message);
	}
}
