package com.egon.msscbeerservice.beer.services;

import com.egon.msscbeerservice.beer.dtos.BeerDto;
import com.egon.msscbeerservice.beer.entities.BeerEntity;
import com.egon.msscbeerservice.beer.mappers.BeerMapper;
import com.egon.msscbeerservice.beer.repositories.BeerRepository;
import com.egon.msscbeerservice.shared.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;
import java.util.function.BiFunction;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateBeerServiceImpl implements UpdateBeerService {

  private final BeerRepository repository;

  private final BeerMapper mapper;

  @Override
  public BeerDto execute(UUID id, BeerDto beerDto) {
    val beerEntity = repository.findById(id).orElseThrow(NotFoundException::new);
    // TODO: refactor
    beerEntity.setName(beerDto.getName());
    beerEntity.setStyle(beerDto.getStyle().toString());
    beerEntity.setPrice(beerDto.getPrice());
    beerEntity.setUpc(beerDto.getUpc());

    val beerUpdated = repository.save(beerEntity);

    log.debug("updating beer");
    return mapper.toDto(beerUpdated);
  }

  private static class UpdateUtils {
    public static BiFunction<BeerDto, BeerEntity, BeerEntity> setName = (beerDto, beerEntity) -> {
      beerEntity.setName(beerDto.getName());

      return beerEntity;
    };

    public static BiFunction<BeerDto, BeerEntity, BeerEntity> setStyle = (beerDto, beerEntity) -> {
      beerEntity.setStyle(beerDto.getStyle().toString());

      return beerEntity;
    };

    public static BiFunction<BeerDto, BeerEntity, BeerEntity> setPrice = (beerDto, beerEntity) -> {
      beerEntity.setPrice(beerDto.getPrice());

      return beerEntity;
    };

    public static BiFunction<BeerDto, BeerEntity, BeerEntity> setUpc = (beerDto, beerEntity) -> {
      beerEntity.setUpc(beerDto.getUpc());

      return beerEntity;
    };

    public static void test(BeerDto dto, BeerEntity entity) {
      BiFunctionCustom<BeerDto, BeerEntity> setName = (beerDto, beerEntity) -> {
        beerEntity.setName(beerDto.getName());

        return beerEntity;
      };

      BiFunctionCustom<BeerDto, BeerEntity> setStyle = (beerDto, beerEntity) -> {
        beerEntity.setStyle(beerDto.getStyle().toString());

        return beerEntity;
      };

      BiFunctionCustom<BeerDto, BeerEntity> setPrice = (beerDto, beerEntity) -> {
        beerEntity.setPrice(beerDto.getPrice());

        return beerEntity;
      };
      BiFunctionCustom<BeerDto, BeerEntity> setUpc = (beerDto, beerEntity) -> {
        beerEntity.setUpc(beerDto.getUpc());

        return beerEntity;
      };

      setName.andThen(setStyle).andThen(setPrice).andThen(setUpc).apply(dto, entity);

    }
  }

  private static interface BiFunctionCustom<T, U> extends BiFunction<T,U,U> {

    default BiFunctionCustom<T, U> andThen(BiFunctionCustom<T, U> after) {
      Objects.requireNonNull(after);
      return (T t, U u) -> after.apply(t, apply(t, u));
    }
  }
}
