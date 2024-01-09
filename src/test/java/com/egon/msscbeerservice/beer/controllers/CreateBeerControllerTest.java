package com.egon.msscbeerservice.beer.controllers;

import com.egon.msscbeerservice.beer.dtos.BeerDto;
import com.egon.msscbeerservice.beer.helpers.BeerDtoHelper;
import com.egon.msscbeerservice.beer.services.CreateBeerService;
import com.egon.msscbeerservice.beer.services.GetOnHandBeerInventoryService;
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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CreateBeerControllerTest {

  @MockBean
  CreateBeerService service;

  @MockBean
  private GetOnHandBeerInventoryService getOnHandBeerInventoryService;

  @Autowired
  MockMvc mockMvc;

  @Autowired
  ObjectMapper objectMapper;

  @Test
  void shouldCreateBeer() throws Exception {
    var beerDto = BeerDtoHelper.createWithoutId();
    var beerDtoJson = objectMapper.writeValueAsString(beerDto);
    var beerDtoSaved = BeerDtoHelper.create();
    given(service.execute(any())).willReturn(beerDtoSaved);

    mockMvc.perform(post("/api/v1/beers")
        .contentType(MediaType.APPLICATION_JSON)
        .content(beerDtoJson))
        .andExpect(status().isCreated());
    verify(service, times(1)).execute(beerDto);
  }

  @Test
  void shouldReturnBadRequestErrorWhenFieldsAreInvalid() throws Exception {
    var beerDto = BeerDto.builder().build();
    var beerDtoJson = objectMapper.writeValueAsString(beerDto);

    mockMvc.perform(post("/api/v1/beers")
        .contentType(MediaType.APPLICATION_JSON)
        .content(beerDtoJson))
        .andExpect(status().isBadRequest());

    verify(service, never()).execute(any());
  }
}