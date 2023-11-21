package com.egon.msscbeerservice.beer.controllers;

import com.egon.msscbeerservice.beer.helpers.BeerDtoHelper;
import com.egon.msscbeerservice.beer.services.DeleteBeerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DeleteBeerController.class)
class DeleteBeerControllerTest {

  @MockBean
  DeleteBeerService service;

  @Autowired
  MockMvc mockMvc;

  @Autowired
  ObjectMapper objectMapper;

  @Test
  void shouldDeleteBeer() throws Exception {
    mockMvc.perform(delete("/api/v1/beers/".concat(BeerDtoHelper.ID.toString())))
        .andExpect(status().isNoContent());
    verify(service).execute(BeerDtoHelper.ID);
  }
}