package com.egon.msscbeerservice.beer.dtos;

import com.egon.msscbeerservice.beer.enums.BeerStyleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto {
  private UUID id;
  private String name;
  private BeerStyleEnum style;
  private BigDecimal price;
  private Long upc;
  private OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;
  private Integer version;
}
