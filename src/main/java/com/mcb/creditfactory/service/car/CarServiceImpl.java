package com.mcb.creditfactory.service.car;

import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.external.ExternalApproveService;
import com.mcb.creditfactory.model.Car;
import com.mcb.creditfactory.repository.CarRepository;
import com.mcb.creditfactory.repository.CostEvaluationRepository;
import com.mcb.creditfactory.service.baseentity.BaseEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl extends BaseEntityService<Car> implements CarService{
    @Autowired
    private ExternalApproveService approveService;

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CostEvaluationRepository costEvaluationRepository;

    @Override
    public boolean approve(CarDto dto) {
        return approveService.approve(new CarAdapter(dto)) == 0;
    }

    @Override
    public Car fromDto(CarDto dto) {
        Car tmpCar = new Car();
        tmpCar.setId(dto.getId());
        tmpCar.setBrand(dto.getBrand() == null || dto.getBrand().equals("") ? null : dto.getBrand());
        tmpCar.setModel(dto.getModel() == null || dto.getModel().equals("") ? null : dto.getModel());
        tmpCar.setPower(dto.getPower());
        tmpCar.setYear(dto.getYear());
        return tmpCar;
    }

    @Override
    public CarDto toDTO(Car car) {
        CarDto tmpCarDto = new CarDto();
        tmpCarDto.setId(car.getId());
        tmpCarDto.setBrand(car.getBrand() == null || car.getBrand().equals("") ? null : car.getBrand());
        tmpCarDto.setModel(car.getModel() == null || car.getModel().equals("") ? null : car.getModel());
        tmpCarDto.setPower(car.getPower());
        tmpCarDto.setYear(car.getYear());
        return tmpCarDto;
    }

    @Override
    public Long getId(Car car) {
        return car.getId();
    }

    @Override
    public CrudRepository<Car, Long> getRepository() {
        return carRepository;
    }

}
