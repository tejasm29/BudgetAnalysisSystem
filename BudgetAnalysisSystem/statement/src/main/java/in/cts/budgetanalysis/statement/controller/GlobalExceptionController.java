package in.cts.budgetanalysis.statement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import in.cts.budgetanalysis.statement.exception.BadStatementException;

@RestControllerAdvice
public class GlobalExceptionController extends ResponseEntityExceptionHandler {

	Logger logger;

	public GlobalExceptionController() {
		logger = LoggerFactory.getLogger(this.getClass());
	}

	@ExceptionHandler(BadStatementException.class)
	public final ResponseEntity<String> handleUserNotFoundException(BadStatementException  exp, WebRequest request) {
		logger.info("sending bad request as response", exp);

		return new ResponseEntity<>(exp.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleUnhandledExceptions(Exception exp){
		logger.error("sending internal server error as resposne",exp);
		return new ResponseEntity<String>("Regeret Inconvinience! Please try again later!", 
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
