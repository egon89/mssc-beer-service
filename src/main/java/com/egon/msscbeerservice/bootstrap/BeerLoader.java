package com.egon.msscbeerservice.bootstrap;

import com.egon.msscbeerservice.beer.entities.Beer;
import com.egon.msscbeerservice.beer.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@RequiredArgsConstructor
@Component
public class BeerLoader implements CommandLineRunner {

    private final BeerRepository beerRepository;

    @Override
    public void run(String... args) {
        log.info("Starting beer loader...");
        if (beerRepository.count() != 0) return;

        beerRepository.save(
                Beer.builder()
                        .name("Mango Bobs")
                        .style("IPA")
                        .quantityToBrew(200)
                        .minOnHand(12)
                        .upc(337010000001L)
                        .price(new BigDecimal("12.95"))
                        .build());
        beerRepository.save(
                Beer.builder()
                        .name("Galaxy Cat")
                        .style("PALE_ALE")
                        .quantityToBrew(200)
                        .minOnHand(12)
                        .upc(337010000002L)
                        .price(new BigDecimal("11.95"))
                        .build());
        log.info("... beer loader finished");
    }
}
