package com.egon.msscbeerservice.shared.dtos;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public abstract class EventDto<T> {
  private T data;
}
