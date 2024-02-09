package com.egon.msscbeerservice.jms.listeners;

import com.egon.msscbeerservice.config.JmsConfig;
import com.egon.msscbeerservice.jms.dtos.HelloMessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HelloMessageListener {

  @JmsListener(destination = JmsConfig.QUEUE_A, containerFactory = "myFactory")
  public void listen(HelloMessageDto messageDto, MessageHeaders headers) {
    log.info("Message received: {}", messageDto);
  }
}
