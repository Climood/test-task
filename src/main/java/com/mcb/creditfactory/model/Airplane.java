package com.mcb.creditfactory.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// todo Как вариант для удоства добавления новых видов объектов обеспечения можно сделать базовый класс с повторяющимися полями (id.brand,model) и вносить новые от него

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "AIRPLANE")
public class Airplane extends BaseEntity{

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "year_of_issue")
    private Short year;

    @Column(name = "fuelCapacity")
    private Integer fuelCapacity;

    @Column(name = "seats")
    private Integer seats;

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Short getYear() {
        return year;
    }

    public void setYear(Short year) {
        this.year = year;
    }

    public Integer getFuelCapacity() {
        return fuelCapacity;
    }

    public void setFuelCapacity(Integer fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "Airplane{" +
                "manufacturer='" + manufacturer + '\'' +
                ", year=" + year +
                ", fuelCapacity=" + fuelCapacity +
                ", seats=" + seats +
                '}';
    }
}
