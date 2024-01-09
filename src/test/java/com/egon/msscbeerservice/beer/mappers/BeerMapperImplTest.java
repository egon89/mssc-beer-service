package com.egon.msscbeerservice.beer.mappers;

import com.egon.msscbeerservice.beer.helpers.BeerDtoHelper;
import com.egon.msscbeerservice.beer.helpers.BeerEntityHelper;
import com.egon.msscbeerservice.beer.helpers.BeerHelper;
import com.egon.msscbeerservice.beer.services.GetOnHandBeerInventoryService;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BeerMapperImplTest {

  @Autowired
  private BeerMapper mapper;

  @MockBean
  private GetOnHandBeerInventoryService getOnHandBeerInventoryService;

  @Test
  void toDto() {
    val expected = BeerEntityHelper.create();

    val result = mapper.toDto(expected);

    assertThat(result.getId()).isEqualTo(expected.getId());
    assertThat(result.getName()).isEqualTo(expected.getName());
    assertThat(result.getStyle().toString()).isEqualTo(expected.getStyle());
    assertThat(result.getPrice()).isEqualTo(expected.getPrice());
    assertThat(result.getUpc()).isEqualTo(expected.getUpc());
    assertThat(result.getVersion()).isEqualTo(expected.getVersion());
    assertThat(result.getCreatedAt()).isEqualTo(OffsetDateTime.of(BeerHelper.CREATED_AT, ZoneOffset.UTC));
    assertThat(result.getUpdatedAt()).isEqualTo(OffsetDateTime.of(BeerHelper.UPDATED_AT, ZoneOffset.UTC));
  }

  @Test
  void toEntity() {
    val expected = BeerDtoHelper.create();

    val result = mapper.toEntity(expected);

    assertThat(result.getId()).isEqualTo(expected.getId());
    assertThat(result.getName()).isEqualTo(expected.getName());
    assertThat(result.getStyle()).isEqualTo(expected.getStyle().toString());
    assertThat(result.getPrice()).isEqualTo(expected.getPrice());
    assertThat(result.getUpc()).isEqualTo(expected.getUpc());
    assertThat(result.getVersion()).isEqualTo(expected.getVersion());
    assertThat(result.getCreatedAt()).isEqualTo(Timestamp.valueOf(BeerHelper.CREATED_AT));
    assertThat(result.getUpdatedAt()).isEqualTo(Timestamp.valueOf(BeerHelper.UPDATED_AT));
  }
}