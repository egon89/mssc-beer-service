package com.egon.common.events.dtos;

import com.egon.msscbeerservice.beer.dtos.BeerDto;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class LowInventoryBeerEventDto extends EventDto<LowInventoryBeerEventDto.LowInventoryBeerDto> {
  @Builder
  public LowInventoryBeerEventDto(LowInventoryBeerDto data) {
    super(data);
  }

  public static LowInventoryBeerEventDto create(BeerDto beerDto) {
    return LowInventoryBeerEventDto.builder()
        .data(new LowInventoryBeerDto(beerDto.getId(), beerDto.getQuantityOnHand(), beerDto.getMinOnHand()))
        .build();
  }

  public static record LowInventoryBeerDto(UUID id, Integer quantityOnHand, Integer minOnHand) {}
}
