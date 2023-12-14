package com.egon.msscbeerservice.shared.dtos;

import java.util.List;

public record ValidationErrorDto(String message, List<String> errors) {}
