package com.egon.msscbeerservice.beer.dtos.events;

import com.egon.msscbeerservice.beer.dtos.BeerDto;
import com.egon.msscbeerservice.shared.dtos.EventDto;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BeerEventDto extends EventDto<BeerEventDto.LowInventoryBeerDto> {
  @Builder
  public BeerEventDto(LowInventoryBeerDto data) {
    super(data);
  }

  public static BeerEventDto create(BeerDto beerDto) {
    return BeerEventDto.builder()
        .data(new LowInventoryBeerDto(beerDto.getId(), beerDto.getQuantityOnHand(), beerDto.getMinOnHand()))
        .build();
  }

  public static record LowInventoryBeerDto(UUID id, Integer quantityOnHand, Integer minOnHand) {}
}
