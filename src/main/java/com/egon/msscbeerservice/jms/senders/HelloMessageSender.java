package com.egon.msscbeerservice.jms.senders;

import com.egon.msscbeerservice.jms.dtos.HelloMessageDto;
import com.egon.msscbeerservice.config.JmsConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class HelloMessageSender {

  @Value("${beer.jms.initial.sender}")
  private Boolean shouldSend;

  private final JmsTemplate jmsTemplate;

  @Scheduled(fixedRate = 5000)
  public void send() {
    if (Objects.isNull(shouldSend) || Boolean.FALSE.equals(shouldSend)) return;

    var message = HelloMessageDto.builder()
        .id(UUID.randomUUID())
        .message("Hello from JMS")
        .build();
    log.info("sending jms message with id {}...", message.getId().toString());
    jmsTemplate.convertAndSend(JmsConfig.QUEUE_A, message);
  }
}

