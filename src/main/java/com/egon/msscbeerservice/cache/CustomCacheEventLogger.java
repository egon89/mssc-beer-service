package com.egon.msscbeerservice.cache;

import lombok.extern.slf4j.Slf4j;
import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomCacheEventLogger implements CacheEventListener<Object, Object> {

  @Override
  public void onEvent(CacheEvent<?, ?> cacheEvent) {
    log.info("Cache event: {}, key: {}, old value: {}, new value: {}",
        cacheEvent.getType(), cacheEvent.getKey(), cacheEvent.getOldValue(), cacheEvent.getNewValue());
  }
}
