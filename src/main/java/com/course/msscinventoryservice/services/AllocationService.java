package com.course.msscinventoryservice.services;

import com.course.msscinventoryservice.brewery.model.BeerOrderDto;

public interface AllocationService {

       Boolean allocateOrder(BeerOrderDto beerOrderDto);
}