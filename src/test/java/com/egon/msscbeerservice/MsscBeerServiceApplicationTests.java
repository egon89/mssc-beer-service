package com.egon.msscbeerservice;

import com.egon.msscbeerservice.beer.services.GetOnHandBeerInventoryService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootTest
class MsscBeerServiceApplicationTests {

	@MockBean
	private GetOnHandBeerInventoryService getOnHandBeerInventoryService;

	@Test
	void contextLoads() {
	}

}
