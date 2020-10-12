package com.mcb.creditfactory.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "COSTEVALUATION")
public class CostEvaluation implements Comparable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private Date date;

    @Column(name = "value")
    private BigDecimal value;

    @Column(name = "evaluation_object_id")
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

    @Override
    public String toString() {
        return "CostEvaluation{" +
                "id=" + id +
                ", date=" + date +
                ", value=" + value +
                ", evaluationObjectId=" + evaluationObjectId +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        CostEvaluation another = (CostEvaluation) o;
        if(another.getDate() != null && this.getDate() != null){
            return this.getDate().compareTo(another.getDate());
        }
        return 0;
    }
}
