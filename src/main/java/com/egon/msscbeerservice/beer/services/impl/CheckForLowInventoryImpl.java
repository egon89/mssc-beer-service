package com.egon.msscbeerservice.beer.services.impl;

import com.egon.msscbeerservice.beer.dtos.events.BeerEventDto;
import com.egon.msscbeerservice.beer.mappers.BeerMapper;
import com.egon.msscbeerservice.beer.repositories.BeerRepository;
import com.egon.msscbeerservice.beer.services.CheckForLowInventory;
import com.egon.msscbeerservice.config.JmsConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
@Service
public class CheckForLowInventoryImpl implements CheckForLowInventory {

  @Value("${beer.initial.jobs}")
  private Boolean initialJobs;
  private final BeerRepository beerRepository;
  private final BeerMapper beerMapper;
  private final JmsTemplate jmsTemplate;
  private final ObjectMapper objectMapper;

  @Scheduled(fixedRate = 10, timeUnit = TimeUnit.MINUTES)
  @Override
  public void execute() {
    if (Objects.isNull(initialJobs) || Boolean.FALSE.equals(initialJobs)) return;

    log.info("Starting check for low inventory job");
    beerRepository.findAll().stream()
        .parallel()
        .map(entity -> beerMapper.toDto(entity, true))
        .peek(beerDto -> log.info("Check inventory for beer {}. Local inventory: {}. Minimum required: {}.",
            beerDto.getId(), beerDto.getQuantityOnHand(), beerDto.getMinOnHand()))
        .filter(beerDto -> beerDto.getQuantityOnHand() < beerDto.getMinOnHand())
        .forEach(beerDto -> {
          log.info("[!] Low local inventory for beer {}. Local inventory: {}. Minimum required: {}.",
              beerDto.getId(), beerDto.getQuantityOnHand(), beerDto.getMinOnHand());
          try {
            jmsTemplate.convertAndSend(JmsConfig.BREWING_REQUEST_QUEUE,
                objectMapper.writeValueAsString(BeerEventDto.create(beerDto)));
          } catch (JsonProcessingException e) {
            log.error("Error to send beer {} to queue {}", beerDto.getId(), JmsConfig.BREWING_REQUEST_QUEUE);
          }
        });
    log.info("Check for low inventory job finished");
  }
}
