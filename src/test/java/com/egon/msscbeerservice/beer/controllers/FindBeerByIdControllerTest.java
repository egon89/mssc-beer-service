package com.egon.msscbeerservice.beer.controllers;

import com.egon.msscbeerservice.beer.helpers.BeerDtoHelper;
import com.egon.msscbeerservice.beer.services.FindBeerByIdService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FindBeerByIdControllerTest {

  @MockBean
  FindBeerByIdService service;

  @Autowired
  MockMvc mockMvc;

  @Test
  void shouldFindById() throws Exception {
    var beerDto = BeerDtoHelper.create();
    given(service.execute(any(), Boolean.FALSE)).willReturn(beerDto);

    mockMvc.perform(get("/api/v1/beers/".concat(BeerDtoHelper.ID.toString())))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(beerDto.getId().toString()))
        .andExpect(jsonPath("$.name").value(beerDto.getName()))
        .andExpect(jsonPath("$.style").value(beerDto.getStyle().toString()));
    verify(service).execute(BeerDtoHelper.ID, Boolean.FALSE);
  }
}