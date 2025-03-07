package com.mcb.creditfactory.service.car;

import com.mcb.creditfactory.dto.CarDto;
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
public class CarAdapter implements CollateralObject {
    @Autowired
    private CostEvaluationRepository costEvaluationRepository;
    @Autowired
    private CarService carService;

    private static final LocalDate MIN_ASSESS_DATE = LocalDate.of(2017, Month.OCTOBER, 1);
    private static final int MIN_CAR_YEAR = 2000;
    private static final BigDecimal MIN_CAR_VALUE = BigDecimal.valueOf(1000000);

    private CarDto carDto;

    public CarAdapter(CarDto carDto){
        this.carDto = carDto;
    }

    // Используется когда адаптер создается через new вне контекста Spring
    public void setCarService(CarService carService){
        this.carService = carService;
    }

    @Override
    public BigDecimal getValue() {
        CostEvaluation costEvaluation = costEvaluationRepository.findByEvaluationObjectId(carDto.getId()).get(0);
        if(costEvaluation != null && costEvaluation.getValue() != null){
            return costEvaluation.getValue();
        }
        return null;
    }

    @Override
    public Short getYear() {
        return carDto.getYear();
    }

    @Override
    public LocalDate getDate() {
        List<CostEvaluation> costEvaluationList = costEvaluationRepository.findByEvaluationObjectId(carDto.getId());
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
        return CollateralType.CAR;
    }

    @Override
    public int approve(CollateralObject object) {
        if (object.getYear() < MIN_CAR_YEAR) {
            return -10;
        }
        if (object.getDate().isBefore(MIN_ASSESS_DATE)) {
            return -11;
        }
        if (object.getValue().compareTo(MIN_CAR_VALUE) < 0) {
            return -12;
        }

        return 0;
    }

    @Autowired
    public Long saveCollateral(Collateral object) {
        if (!(object instanceof CarDto)) {
            throw new IllegalArgumentException();
        }

        CarDto car = (CarDto) object;
        boolean approved = carService.approve(car);
        if (!approved) {
            return null;
        }

        return Optional.of(car)
                .map(carService::fromDto)
                .map(carService::save)
                .map(carService::getId)
                .orElse(null);
    }

    @Autowired
    public Collateral getInfo(Collateral object) {
        if (!(object instanceof CarDto)) {
            throw new IllegalArgumentException();
        }

        return Optional.of((CarDto) object)
                .map(carService::fromDto)
                .map(carService::getId)
                .flatMap(carService::load)
                .map(carService::toDTO)
                .orElse(null);
    }
}
