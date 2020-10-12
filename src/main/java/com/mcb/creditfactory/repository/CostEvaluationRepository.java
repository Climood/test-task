package com.mcb.creditfactory.repository;

import com.mcb.creditfactory.model.CostEvaluation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CostEvaluationRepository extends CrudRepository<CostEvaluation, Long> {

    @Query("select ce from CostEvaluation ce where evaluation_object_id = ?1")
    List<CostEvaluation> findByEvaluationObjectId(Long id);

}
