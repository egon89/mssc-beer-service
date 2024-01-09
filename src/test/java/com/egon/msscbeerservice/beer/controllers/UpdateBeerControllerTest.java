package com.egon.msscbeerservice.beer.controllers;

import com.egon.msscbeerservice.beer.dtos.BeerDto;
import com.egon.msscbeerservice.beer.helpers.BeerDtoHelper;
import com.egon.msscbeerservice.beer.services.GetOnHandBeerInventoryService;
import com.egon.msscbeerservice.beer.services.UpdateBeerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UpdateBeerControllerTest {

  @MockBean
  UpdateBeerService service;

  @MockBean
  private GetOnHandBeerInventoryService getOnHandBeerInventoryService;

  @Autowired
  MockMvc mockMvc;

  @Autowired
  ObjectMapper objectMapper;

  @Test
  void shouldUpdateBeer() throws Exception {
    var beerDto = BeerDtoHelper.create();
    var beerDtoJson = objectMapper.writeValueAsString(beerDto);
    given(service.execute(any(), any())).willReturn(beerDto);

    mockMvc.perform(put("/api/v1/beers/".concat(BeerDtoHelper.ID.toString()))
        .contentType(MediaType.APPLICATION_JSON)
        .content(beerDtoJson))
        .andExpect(status().isNoContent());
    verify(service).execute(BeerDtoHelper.ID, beerDto);
  }

  @Test
  void shouldReturnBadRequestErrorWhenFieldsAreInvalid() throws Exception {
    var beerDto = BeerDto.builder().build();
    var beerDtoJson = objectMapper.writeValueAsString(beerDto);

    mockMvc.perform(post("/api/v1/beers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(beerDtoJson))
        .andExpect(status().isBadRequest());

    verify(service, never()).execute(any(), any());
  }
}