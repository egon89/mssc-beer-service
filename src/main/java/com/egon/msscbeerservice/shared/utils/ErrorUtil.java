package com.egon.msscbeerservice.shared.utils;

import com.egon.msscbeerservice.shared.dtos.ValidationErrorDto;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

public class ErrorUtil {
    private static final String INVALID_FIELDS = "There are invalid fields";
    private ErrorUtil() {
    }

    public static List<String> getFieldErrors(MethodArgumentNotValidException exception) {
        return exception.getBindingResult().getFieldErrors().stream()
                .map(error -> String.format("%s: %s", error.getField(), error.getDefaultMessage()))
                .toList();
    }

    public static ValidationErrorDto validationErrorFactory(List<String> errors) {
        return new ValidationErrorDto(INVALID_FIELDS, errors);
    }

    public static ValidationErrorDto validationErrorFactory(String message, List<String> errors) {
        return new ValidationErrorDto(message, errors);
    }
}
