package com.egon.msscbeerservice.beer.mappers;

import com.egon.msscbeerservice.beer.dtos.BeerDto;
import com.egon.msscbeerservice.beer.entities.BeerEntity;
import com.egon.msscbeerservice.shared.mappers.DateMapper;
import org.mapstruct.Mapper;

@Mapper(uses = DateMapper.class)
public interface BeerMapper {
  BeerDto toDto(BeerEntity entity);
  BeerEntity toEntity(BeerDto dto);
}
