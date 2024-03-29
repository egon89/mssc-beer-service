package com.egon.msscbeerservice;

import com.egon.msscbeerservice.beer.services.GetOnHandBeerInventoryService;
import jakarta.jms.ConnectionFactory;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootTest
class MsscBeerServiceApplicationTests {

	@MockBean
	private GetOnHandBeerInventoryService getOnHandBeerInventoryService;

	@Test
	void contextLoads() {
	}

	@EnableCaching
	@Configuration
	public static class CachingTestConfig {
		@Bean
		public CacheManager cacheManager() {
			return new ConcurrentMapCacheManager(
					"findBeerByIdCache", "listBeerCache", "findBeerByUpcCache");
		}
	}

	@Configuration
	public static class JMSTestConfig {
		@Bean
		public ConnectionFactory connectionFactory() {
			return Mockito.mock(ConnectionFactory.class);
		}
	}
}
