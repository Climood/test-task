package com.mcb.creditfactory.service.airplane;

import com.mcb.creditfactory.dto.AirplaneDto;
import com.mcb.creditfactory.dto.Collateral;
import com.mcb.creditfactory.external.CollateralObject;
import com.mcb.creditfactory.external.CollateralType;
import com.mcb.creditfactory.model.CostEvaluation;
import com.mcb.creditfactory.repository.CostEvaluationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class AirplaneAdapter implements CollateralObject {
    @Autowired
    private CostEvaluationRepository costEvaluationRepository;
    @Autowired
    private AirplaneService airplaneService;

    private static final LocalDate MIN_ASSESS_DATE = LocalDate.of(2017, Month.OCTOBER, 1);
    private static final int MIN_PLANE_YEAR = 1991;
    private static final BigDecimal MIN_PLANE_VALUE = BigDecimal.valueOf(230000000);

    private AirplaneDto airplaneDto;

    public AirplaneAdapter(AirplaneDto airplaneDto) {
        this.airplaneDto = airplaneDto;
    }

    // Используется когда адаптер создается через new вне контекста Spring
    public void setAirplaneService(AirplaneService airplaneService){
        this.airplaneService = airplaneService;
    }

    public String getManufacturer() {
        return airplaneDto.getManufacturer();
    }

    public Integer getFuelCapacity() {
        return airplaneDto.getFuelCapacity();
    }

    public Integer getSeats() {
        return airplaneDto.getSeats();
    }

    @Override
    public BigDecimal getValue() {
        CostEvaluation costEvaluation = costEvaluationRepository.findByEvaluationObjectId(airplaneDto.getId()).get(0);
        if(costEvaluation != null && costEvaluation.getValue() != null){
            return costEvaluation.getValue();
        }
        return null;
    }

    @Override
    public Short getYear() {
        return airplaneDto.getYear();
    }

    @Override
    public LocalDate getDate() {
        List<CostEvaluation> costEvaluationList = costEvaluationRepository.findByEvaluationObjectId(airplaneDto.getId());
        costEvaluationList.sort((c1,c2) -> c1.compareTo(c2));
        CostEvaluation costEvaluation = costEvaluationList.get(costEvaluationList.size() -1 ); // После сортировки самый новый будет в конце
        if(costEvaluation != null && costEvaluation.getDate() != null){
            return costEvaluation.getDate().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
        }
        return null;
    }

    @Override
    public CollateralType getType() {
        return CollateralType.AIRPLANE;
    }

    @Override
    public int approve(CollateralObject object) {
        if (object.getYear() < MIN_PLANE_YEAR) {
            return -20;
        }
        if (object.getDate().isBefore(MIN_ASSESS_DATE)) {
            return -21;
        }
        if (object.getValue().compareTo(MIN_PLANE_VALUE) < 0) {
            return -22;
        }

        return 0;
    }

    @Autowired
    public Long saveCollateral(Collateral object) {
        if (!(object instanceof AirplaneDto)) {
            throw new IllegalArgumentException();
        }

        AirplaneDto airplaneDto = (AirplaneDto) object;
        boolean approved = airplaneService.approve(airplaneDto);
        if (!approved) {
            return null;
        }

        return Optional.of(airplaneDto)
                .map(airplaneService::fromDto)
                .map(airplaneService::save)
                .map(airplaneService::getId)
                .orElse(null);
    }

    @Autowired
    public Collateral getInfo(Collateral object) {
        if (!(object instanceof AirplaneDto)) {
            throw new IllegalArgumentException();
        }

        return Optional.of((AirplaneDto) object)
                .map(airplaneService::fromDto)
                .map(airplaneService::getId)
                .flatMap(airplaneService::load)
                .map(airplaneService::toDTO)
                .orElse(null);
    }

}
