package com.mcb.creditfactory.external;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

@Service
@Slf4j
public class ExternalApproveService {

    public int approve(CollateralObject object) {
        if (object.getDate() == null ||object.getYear() == null || object.getValue() == null || object.getType() == null) {
            return -1;
        }

        return object.approve(object);
    }




}
