package com.udacity.eurekaserver.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface PriceRepository extends CrudRepository<Price, Long> {

}
