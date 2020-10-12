package com.mcb.creditfactory.service;

import com.mcb.creditfactory.dto.AirplaneDto;
import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.dto.Collateral;
import com.mcb.creditfactory.external.CollateralObject;
import com.mcb.creditfactory.repository.CostEvaluationRepository;
import com.mcb.creditfactory.service.airplane.AirplaneAdapter;
import com.mcb.creditfactory.service.airplane.AirplaneService;
import com.mcb.creditfactory.service.car.CarAdapter;
import com.mcb.creditfactory.service.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CollateralService {
    @Autowired
    private CostEvaluationRepository costEvaluationRepository;
    @Autowired
    private CarService carService;
    @Autowired
    private AirplaneService airplaneService;

    public Long saveCollateral(Collateral object) {
        if (object == null) {
            throw new IllegalArgumentException();
        }

        CollateralObject adapter = getAdapter(object);
        return adapter.saveCollateral(object);
    }

    public Collateral getInfo(Collateral object) {
        CollateralObject adapter = getAdapter(object);
        return adapter.getInfo(object);
    }

    public CollateralObject getAdapter(Collateral object) {
        CollateralObject collateralObject;

        Class<?> clazz = object.getClass();
        if (CarDto.class.equals(clazz)) {
            collateralObject = new CarAdapter((CarDto)object);
            // адаптер создается через new вне контекста Spring
            ((CarAdapter) collateralObject).setCarService(carService);
        } else if (AirplaneDto.class.equals(clazz)) {
            collateralObject = new AirplaneAdapter((AirplaneDto)object);
            // адаптер создается через new вне контекста Spring
            ((AirplaneAdapter) collateralObject).setAirplaneService(airplaneService);
        } else {
            throw new IllegalArgumentException();
        }
        return collateralObject;
    }
}
