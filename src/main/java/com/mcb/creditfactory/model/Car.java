package com.mcb.creditfactory.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CAR")
public class Car extends BaseEntity{

    @Column(name = "power")
    private Double power;

    @Column(name = "year_of_issue")
    private Short year;

    public Double getPower() {
        return power;
    }

    public void setPower(Double power) {
        this.power = power;
    }

    public Short getYear() {
        return year;
    }

    public void setYear(Short year) {
        this.year = year;
    }


    @Override
    public String toString() {
        return "Car{" +
                "power=" + power +
                ", year=" + year +
                '}';
    }
}
