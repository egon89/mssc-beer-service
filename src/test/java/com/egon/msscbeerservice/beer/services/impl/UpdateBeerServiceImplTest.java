package com.egon.msscbeerservice.beer.services.impl;

import com.egon.msscbeerservice.beer.dtos.BeerDto;
import com.egon.msscbeerservice.beer.enums.BeerStyleEnum;
import com.egon.msscbeerservice.beer.helpers.BeerDtoHelper;
import com.egon.msscbeerservice.beer.helpers.BeerEntityHelper;
import com.egon.msscbeerservice.beer.helpers.BeerHelper;
import com.egon.msscbeerservice.beer.repositories.BeerRepository;
import com.egon.msscbeerservice.beer.services.GetOnHandBeerInventoryService;
import com.egon.msscbeerservice.beer.services.impl.UpdateBeerServiceImpl;
import com.egon.msscbeerservice.shared.exceptions.NotFoundException;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class UpdateBeerServiceImplTest {

  @Autowired
  private UpdateBeerServiceImpl service;

  @MockBean
  private BeerRepository repository;

  @MockBean
  private GetOnHandBeerInventoryService getOnHandBeerInventoryService;

  @Test
  @SuppressWarnings("ConstantConditions")
  void shouldUpdateABeer() {
    val beerEntity = BeerEntityHelper.create();
    val updateBeerDto = BeerDto.builder()
        .id(beerEntity.getId())
        .name("Beer 2")
        .style(BeerStyleEnum.IPA)
        .price(BigDecimal.valueOf(11.5))
        .upc(3L)
        .build();
    when(repository.findById(any())).thenReturn(Optional.of(beerEntity));
    when(repository.save(any())).thenReturn(beerEntity);

    val result = service.execute(BeerHelper.ID, updateBeerDto);

    assertThat(result).isNotNull();
    verify(repository).save(assertArg(p -> {
      assertThat(p.getId()).isEqualTo(updateBeerDto.getId());
      assertThat(p.getName()).isEqualTo(updateBeerDto.getName());
      assertThat(p.getStyle()).isEqualTo(updateBeerDto.getStyle().toString());
      assertThat(p.getPrice()).isEqualTo(updateBeerDto.getPrice());
      assertThat(p.getUpc()).isEqualTo(updateBeerDto.getUpc());
    }));
  }

  @Test
  void ShouldThrowNotFoundExceptionWhenBeerDoesNotExist() {
    when(repository.findById(any())).thenThrow(NotFoundException.class);

    assertThrows(NotFoundException.class,
        () -> service.execute(BeerHelper.ID, BeerDtoHelper.create()));
  }
}
