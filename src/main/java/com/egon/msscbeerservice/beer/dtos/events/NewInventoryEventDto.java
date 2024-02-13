package com.egon.msscbeerservice.beer.dtos.events;

import com.egon.msscbeerservice.shared.dtos.EventDto;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class NewInventoryEventDto extends EventDto<NewInventoryEventDto.NewInventoryBeerDto> {

  @Builder
  public NewInventoryEventDto(NewInventoryBeerDto data) {
    super(data);
  }

  public static record NewInventoryBeerDto(UUID id, Integer quantityOnHand) {}
}
