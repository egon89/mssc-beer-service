package com.egon.msscbeerservice.beer.dtos;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class BeerPagedListDto extends PageImpl<BeerDto> {
    public BeerPagedListDto(List<BeerDto> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public BeerPagedListDto(List<BeerDto> content) {
        super(content);
    }
}
