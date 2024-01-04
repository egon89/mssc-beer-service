package com.egon.msscbeerservice.beer.services;

import com.egon.msscbeerservice.beer.dtos.BeerPagedListDto;
import com.egon.msscbeerservice.beer.dtos.ListBeerFilterDto;
import com.egon.msscbeerservice.beer.mappers.BeerMapper;
import com.egon.msscbeerservice.beer.repositories.BeerRepository;
import com.egon.msscbeerservice.beer.repositories.specifications.BeerSpecificationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListBeerServiceImpl implements ListBeerService {

  private final BeerRepository repository;

  private final BeerMapper mapper;

  @Override
  public BeerPagedListDto execute(ListBeerFilterDto filter, PageRequest pageRequest) {
    var page = repository.findAll(BeerSpecificationUtil.byFilter(filter), pageRequest);

    return new BeerPagedListDto(
        page.getContent().stream().map(mapper::toDto).toList(),
        PageRequest.of(page.getPageable().getPageNumber(), page.getPageable().getPageSize()),
        page.getTotalElements());
  }
}
