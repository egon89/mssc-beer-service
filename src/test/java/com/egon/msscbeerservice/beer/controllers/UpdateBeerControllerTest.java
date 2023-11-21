package com.egon.msscbeerservice.beer.controllers;

import com.egon.msscbeerservice.beer.helpers.BeerDtoHelper;
import com.egon.msscbeerservice.beer.services.UpdateBeerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UpdateBeerController.class)
class UpdateBeerControllerTest {

  @MockBean
  UpdateBeerService service;

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
}