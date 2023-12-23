package com.egon.msscbeerservice.shared.interfaces;

import java.util.Objects;
import java.util.function.BiFunction;

public interface BiFunctionCustom<T, U> extends BiFunction<T,U,U> {

  default BiFunctionCustom<T, U> andThen(BiFunctionCustom<T, U> after) {
    Objects.requireNonNull(after);
    return (T t, U u) -> after.apply(t, apply(t, u));
  }
}
