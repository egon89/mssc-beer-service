package com.egon.msscbeerservice.beer.dtos;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class BeerPagedListDto extends PageImpl<BeerDto> implements Serializable {

    @Serial
    private static final long serialVersionUID = -7861464575443606416L;

    public BeerPagedListDto(List<BeerDto> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public BeerPagedListDto(List<BeerDto> content) {
        super(content);
    }
}
