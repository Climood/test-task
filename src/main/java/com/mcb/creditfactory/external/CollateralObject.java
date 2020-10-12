package com.mcb.creditfactory.external;

import com.mcb.creditfactory.dto.Collateral;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface CollateralObject {
    BigDecimal getValue();
    Short getYear();
    LocalDate getDate();
    CollateralType getType();
    int approve(CollateralObject object);
    public Long saveCollateral(Collateral object);
    public Collateral getInfo(Collateral object);

//    // Используется когда адаптер создается через new вне контекста Spring
//    public void setService();

}
