package com.egon.msscbeerservice.beer.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class DeleteBeerServiceImpl implements DeleteBeerService {
  @Override
  public void execute(UUID id) {
    log.debug("deleting beer");
    System.out.println("deleting");
  }
}
