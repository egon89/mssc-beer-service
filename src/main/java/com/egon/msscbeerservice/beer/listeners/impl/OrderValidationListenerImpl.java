package com.egon.msscbeerservice.beer.listeners.impl;

import com.egon.brewery.dtos.BeerOrderLineDto;
import com.egon.brewery.dtos.events.ValidateBeerOrderRequest;
import com.egon.brewery.dtos.events.ValidateBeerOrderResultDto;
import com.egon.msscbeerservice.beer.listeners.OrderValidationListener;
import com.egon.msscbeerservice.config.JmsConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderValidationListenerImpl implements OrderValidationListener {

  private final JmsTemplate jmsTemplate;

  @JmsListener(destination = JmsConfig.VALIDATE_ORDER_QUEUE)
  @Override
  public void listen(ValidateBeerOrderRequest beerOrderRequest) {
    final var beerOrder = Optional.ofNullable(beerOrderRequest)
        .map(ValidateBeerOrderRequest::getBeerOrderDto)
        .orElseThrow(() -> new IllegalArgumentException("Order beer not found"));
    log.debug("Get order validation {} from {} queue", beerOrder.getId(), JmsConfig.VALIDATE_ORDER_QUEUE);

    if (beerOrder.getBeerOrderLines().isEmpty()) {
      throw new RuntimeException("Beer order %s without order lines".formatted(beerOrder.getId()));
    }
    final var invalidUpcCounter = beerOrder.getBeerOrderLines().stream()
        .map(BeerOrderLineDto::getUpc)
        .filter(Objects::isNull)
        .count();
    final var orderResult = ValidateBeerOrderResultDto.builder()
        .id(beerOrder.getId())
        .isValid(invalidUpcCounter == 0)
        .build();

    try {
      jmsTemplate.convertAndSend(JmsConfig.VALIDATE_ORDER_RESULT_QUEUE, orderResult);
      log.debug("Order request {} sent to {} queue ", beerOrder.getId(), JmsConfig.VALIDATE_ORDER_RESULT_QUEUE);
    } catch (Exception ex) {
      final var errorMessage =
          "Error to send order %s to %s queue".formatted(beerOrder.getId(), JmsConfig.VALIDATE_ORDER_RESULT_QUEUE);
      log.error(errorMessage);
      throw new RuntimeException(errorMessage);
    }
  }
}
