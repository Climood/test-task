package com.mcb.creditfactory.service.costevaluation;

import com.mcb.creditfactory.dto.CostEvaluationDto;
import com.mcb.creditfactory.model.CostEvaluation;
import com.mcb.creditfactory.repository.CostEvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class CostEvaluationServiceImpl implements CostEvaluationService {
    @Autowired
    private CostEvaluationRepository costEvaluationRepository;

    public CrudRepository<CostEvaluation, Long> getRepository() {
        return costEvaluationRepository;
    }

    public CostEvaluation fromDto(CostEvaluationDto dto) {
        CostEvaluation tmpCostEvaluation = new CostEvaluation();
        tmpCostEvaluation.setId(dto.getId());
        tmpCostEvaluation.setDate(dto.getDate());
        tmpCostEvaluation.setValue(dto.getValue());
        tmpCostEvaluation.setEvaluationObjectId(dto.getEvaluationObjectId());
        return tmpCostEvaluation;
    }

    public CostEvaluationDto toDTO(CostEvaluation costEvaluation) {
        CostEvaluationDto tmpCostEvaluationDto = new CostEvaluationDto();
        tmpCostEvaluationDto.setId(costEvaluation.getId());
        tmpCostEvaluationDto.setDate(costEvaluation.getDate());
        tmpCostEvaluationDto.setValue(costEvaluation.getValue());
        tmpCostEvaluationDto.setEvaluationObjectId(costEvaluation.getEvaluationObjectId());
        return tmpCostEvaluationDto;
    }

}
