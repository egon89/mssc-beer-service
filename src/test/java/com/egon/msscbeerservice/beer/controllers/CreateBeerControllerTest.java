package com.egon.msscbeerservice.beer.controllers;

import com.egon.msscbeerservice.beer.helpers.BeerDtoHelper;
import com.egon.msscbeerservice.beer.services.CreateBeerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/*
 * @WebMvcTest
 * Spring Boot instantiates only the web layer rather than the whole context.
 */
@WebMvcTest(CreateBeerController.class)
class CreateBeerControllerTest {

  @MockBean
  CreateBeerService service;

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
}