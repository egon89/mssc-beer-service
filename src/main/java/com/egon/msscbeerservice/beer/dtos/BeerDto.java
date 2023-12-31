package com.egon.msscbeerservice.beer.dtos;

import com.egon.msscbeerservice.beer.enums.BeerStyleEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
@Builder(toBuilder = true)
public class BeerDto {
  private UUID id;

  @NotBlank
  private String name;

  @NotNull
  private BeerStyleEnum style;

  @NotNull
  @Positive
  private BigDecimal price;

  @NotNull
  @Positive
  private Long upc;

  private Integer quantityOnHand;

  private OffsetDateTime createdAt;

  private OffsetDateTime updatedAt;

  private Long version;
}
