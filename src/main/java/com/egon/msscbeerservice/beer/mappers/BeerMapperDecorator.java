package com.egon.msscbeerservice.beer.mappers;

import com.egon.msscbeerservice.beer.dtos.BeerDto;
import com.egon.msscbeerservice.beer.entities.BeerEntity;
import com.egon.msscbeerservice.beer.services.GetOnHandBeerInventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Objects;


@Slf4j
public abstract class BeerMapperDecorator implements BeerMapper {
  @Autowired
  @Qualifier("delegate")
  private BeerMapper delegate;

  @Autowired
  private GetOnHandBeerInventoryService getOnHandBeerInventoryService;

  @Override
  public BeerDto toDto(BeerEntity entity) {
    log.debug("Beer mapper decorator");
    return delegate.toDto(entity).toBuilder()
        .quantityOnHand(getOnHandBeerInventoryService.execute(entity.getId()))
        .build();
  }

  @Override
  public BeerDto toDto(BeerEntity entity, Boolean showInventoryOnHand) {
    return Objects.isNull(showInventoryOnHand) || Boolean.FALSE.equals(showInventoryOnHand)
        ? delegate.toDto(entity)
        : toDto(entity);
  }
}
