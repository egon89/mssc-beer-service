package com.egon.msscbeerservice.beer.dtos.events;

import com.egon.msscbeerservice.beer.dtos.BeerDto;
import com.egon.msscbeerservice.shared.dtos.EventDto;
import lombok.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BeerEventDto extends EventDto<BeerDto> {
  @Builder
  public BeerEventDto(BeerDto data) {
    super(data);
  }

  public static BeerEventDto create(BeerDto beerDto) {
    return BeerEventDto.builder().data(beerDto).build();
  }
}
