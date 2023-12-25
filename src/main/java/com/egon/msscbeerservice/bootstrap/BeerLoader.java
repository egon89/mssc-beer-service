package com.egon.msscbeerservice.bootstrap;

import com.egon.msscbeerservice.beer.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class BeerLoader implements CommandLineRunner {

    private final BeerRepository beerRepository;

    @Override
    public void run(String... args) {
        log.info("Starting app with %d beer(s)...".formatted(beerRepository.count()));
    }
}
