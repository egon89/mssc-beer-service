package com.egon.msscbeerservice.beer.repositories.specifications;

import com.egon.msscbeerservice.beer.dtos.ListBeerFilterDto;
import com.egon.msscbeerservice.beer.entities.BeerEntity;
import com.egon.msscbeerservice.beer.enums.BeerStyleEnum;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

@UtilityClass
public class BeerSpecificationUtil {

  public static Specification<BeerEntity> byName(String name) {
    if (Objects.isNull(name)) return null;

    return (root, query, builder) -> {
      return builder.like(builder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    };
  }

  public static Specification<BeerEntity> byStyle(BeerStyleEnum style) {
    if (Objects.isNull(style)) return null;

    return (root, query, builder) -> {
      return builder.equal(root.get("style"), style.toString());
    };
  }

  public static Specification<BeerEntity> byFilter(ListBeerFilterDto filterDto) {
    return Specification.allOf(byName(filterDto.name()), byStyle(filterDto.style()));
  }
}
