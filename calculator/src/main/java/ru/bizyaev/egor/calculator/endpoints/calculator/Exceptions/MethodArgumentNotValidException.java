package ru.bizyaev.egor.calculator.endpoints.calculator.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Expression is invalid")
public class MethodArgumentNotValidException extends RuntimeException {
}
