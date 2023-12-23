package com.egon.msscbeerservice.beer.utils;

import com.egon.msscbeerservice.beer.dtos.BeerDto;
import com.egon.msscbeerservice.beer.enums.BeerStyleEnum;
import com.egon.msscbeerservice.beer.helpers.BeerEntityHelper;
import com.egon.msscbeerservice.beer.helpers.BeerHelper;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class BeerUtilsTest {

  @Test
  void ShouldSetUpdatableValuesFromDtoToEntity() {
    val entity = BeerEntityHelper.create();
    val dto = BeerDto.builder()
        .name("Beer 10")
        .style(BeerStyleEnum.PILSNER)
        .price(BigDecimal.valueOf(9.99))
        .upc(100L)
        .build();

    BeerUtils.setUpdatableValuesToEntity(dto, entity);

    assertThat(entity.getId()).isEqualTo(BeerHelper.ID);
    assertThat(entity.getName()).isEqualTo(dto.getName());
    assertThat(entity.getStyle()).isEqualTo(dto.getStyle().toString());
    assertThat(entity.getPrice()).isEqualTo(dto.getPrice());
    assertThat(entity.getUpc()).isEqualTo(dto.getUpc());
  }
}
