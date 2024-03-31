package com.egon.brewery.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BeerOrderDto extends BaseDto{

  @Builder(toBuilder = true)
  public BeerOrderDto(
      UUID id, Integer version, OffsetDateTime createdAt, OffsetDateTime updatedAt, UUID customerId, String customerRef,
      List<BeerOrderLineDto> beerOrderLines, String orderStatus, String orderStatusCallbackUrl) {
    super(id, version, createdAt, updatedAt);
    this.customerId = customerId;
    this.customerRef = customerRef;
    this.beerOrderLines = beerOrderLines;
    this.orderStatus = orderStatus;
    this.orderStatusCallbackUrl = orderStatusCallbackUrl;
  }

  private UUID customerId;
  private String customerRef;
  private List<BeerOrderLineDto> beerOrderLines;
  private String orderStatus;
  private String orderStatusCallbackUrl;
}
