package com.egon.msscbeerservice.shared.mappers;

import com.egon.msscbeerservice.beer.services.GetOnHandBeerInventoryService;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DateMapperTest {
  private static final LocalDateTime LOCAL_DATE_TIME = LocalDateTime.of(2023, 12, 14, 0, 0);
  @Autowired
  private DateMapper mapper;

  @MockBean
  private GetOnHandBeerInventoryService getOnHandBeerInventoryService;

  @Test
  void toTimestamp() {
    val offsetDateTime = OffsetDateTime.of(
        LocalDateTime.of(2023, 12, 13, 21, 0),
        ZoneOffset.ofHours(-3));

    val result = mapper.toTimestamp(offsetDateTime);

    assertThat(result).isEqualTo(Timestamp.valueOf(LOCAL_DATE_TIME));
  }

  @Test
  void toOffsetDateTime() {
    val timestamp = Timestamp.valueOf(LOCAL_DATE_TIME);

    val result = mapper.toOffsetDateTime(timestamp);

    assertThat(result).isEqualTo(OffsetDateTime.of(LOCAL_DATE_TIME, ZoneOffset.UTC));
  }
}