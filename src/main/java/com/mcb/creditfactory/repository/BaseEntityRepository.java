package com.mcb.creditfactory.repository;

import com.mcb.creditfactory.model.BaseEntity;
import org.springframework.data.repository.CrudRepository;

public interface BaseEntityRepository extends CrudRepository<BaseEntity, Long> {
}
