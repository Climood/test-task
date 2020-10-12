package com.mcb.creditfactory.service.airplane;

import com.mcb.creditfactory.dto.AirplaneDto;
import com.mcb.creditfactory.external.ExternalApproveService;
import com.mcb.creditfactory.model.Airplane;
import com.mcb.creditfactory.repository.AirplaneRepository;
import com.mcb.creditfactory.repository.CostEvaluationRepository;
import com.mcb.creditfactory.service.baseentity.BaseEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class AirplaneServiceImpl extends BaseEntityService<Airplane> implements AirplaneService {
    @Autowired
    private ExternalApproveService approveService;

    @Autowired
    private AirplaneRepository airplaneRepository;
    @Autowired
    private CostEvaluationRepository costEvaluationRepository;

    @Override
    public boolean approve(AirplaneDto dto) {
        return approveService.approve(new AirplaneAdapter(dto)) == 0;
    }

    @Override
    public Airplane fromDto(AirplaneDto dto) {
        Airplane tmpAirplane = new Airplane();
        tmpAirplane.setId(dto.getId());
        tmpAirplane.setBrand(dto.getBrand() == null || dto.getBrand().equals("") ? null : dto.getBrand());
        tmpAirplane.setModel(dto.getModel() == null || dto.getModel().equals("") ? null : dto.getModel());
        tmpAirplane.setManufacturer(dto.getManufacturer() == null || dto.getManufacturer().equals("") ? null : dto.getManufacturer());
        tmpAirplane.setYear(dto.getYear());
        tmpAirplane.setFuelCapacity(dto.getFuelCapacity());
        tmpAirplane.setSeats(dto.getSeats());
        tmpAirplane.setYear(dto.getYear());
        return tmpAirplane;
    }

    @Override
    public AirplaneDto toDTO(Airplane airplane) {
        AirplaneDto tmpAirplaneDto = new AirplaneDto();
        tmpAirplaneDto.setId(tmpAirplaneDto.getId());
        tmpAirplaneDto.setBrand(airplane.getBrand() == null || airplane.getBrand().equals("") ? null : airplane.getBrand());
        tmpAirplaneDto.setModel(airplane.getModel() == null || airplane.getModel().equals("") ? null : airplane.getModel());
        tmpAirplaneDto.setManufacturer(airplane.getManufacturer() == null || airplane.getManufacturer().equals("") ? null : airplane.getManufacturer());
        tmpAirplaneDto.setYear(airplane.getYear());
        tmpAirplaneDto.setFuelCapacity(airplane.getFuelCapacity());
        tmpAirplaneDto.setSeats(airplane.getSeats());
        tmpAirplaneDto.setYear(airplane.getYear());
        return tmpAirplaneDto;
    }

    @Override
    public Long getId(Airplane airplane) {
        return airplane.getId();
    }

    @Override
    public CrudRepository<Airplane, Long> getRepository() {
        return airplaneRepository;
    }
}
