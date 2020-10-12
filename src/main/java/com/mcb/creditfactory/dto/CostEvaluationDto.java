package com.mcb.creditfactory.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("costevaluation")
public class CostEvaluationDto {
    private Long id;
    private Date date;
    private BigDecimal value;
    private Integer evaluationObjectId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Integer getEvaluationObjectId() {
        return evaluationObjectId;
    }

    public void setEvaluationObjectId(Integer evaluationObjectId) {
        this.evaluationObjectId = evaluationObjectId;
    }
}
