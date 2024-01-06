package com.egon.msscbeerservice.beer.dtos.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.OffsetDateTime;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BeerInventoryIntegrationResponseDto(
    UUID id,
    String upc,
    Integer quantityOnHand,
    OffsetDateTime createdAt,
    OffsetDateTime updatedAt
) {
}
