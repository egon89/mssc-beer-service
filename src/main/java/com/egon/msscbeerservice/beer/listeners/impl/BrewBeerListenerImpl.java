package com.egon.msscbeerservice.beer.listeners.impl;

import com.egon.common.events.dtos.LowInventoryBeerEventDto;
import com.egon.common.events.dtos.NewInventoryEventDto;
import com.egon.msscbeerservice.beer.listeners.BrewBeerListener;
import com.egon.msscbeerservice.beer.repositories.BeerRepository;
import com.egon.msscbeerservice.config.JmsConfig;
import com.egon.msscbeerservice.shared.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class BrewBeerListenerImpl implements BrewBeerListener {

  private final BeerRepository beerRepository;
  private final JmsTemplate jmsTemplate;

  @JmsListener(destination = JmsConfig.BREWING_REQUEST_QUEUE)
  @Override
  public void listen(LowInventoryBeerEventDto eventDto) {
    val beerDto = Optional.of(eventDto.getData()).orElseThrow(IllegalArgumentException::new);
    log.debug("Get beer {} listening queue {}", beerDto.id(), JmsConfig.BREWING_REQUEST_QUEUE);
    val beer = beerRepository.findById(beerDto.id()).orElseThrow(NotFoundException::new);
    val newInventoryBeerDto = new NewInventoryEventDto.NewInventoryBeerDto(beerDto.id(), beer.getQuantityToBrew());
    val newInventoryBeerEventDto = NewInventoryEventDto.builder().data(newInventoryBeerDto).build();

    jmsTemplate.convertAndSend(JmsConfig.NEW_INVENTORY_QUEUE, newInventoryBeerEventDto);
    log.debug("Beer {} sent to queue {}", beerDto.id(), JmsConfig.NEW_INVENTORY_QUEUE);
  }
}

