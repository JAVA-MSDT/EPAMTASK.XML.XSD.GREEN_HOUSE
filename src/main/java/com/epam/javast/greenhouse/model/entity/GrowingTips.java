package com.epam.javast.greenhouse.model.entity;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "growing-tips", namespace = "http://www.example.com/plants")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "growing-tips", propOrder = {
        "temperature",
        "isPhotophilous",
        "waterAmount"
})
public class GrowingTips {

    @XmlElement(name = "temperature", namespace = "http://www.example.com/plants")
    private double temperature;
    @XmlElement(name = "is-photophilous", namespace = "http://www.example.com/plants")
    private boolean isPhotophilous;
    @XmlElement(name = "water-amount", namespace = "http://www.example.com/plants")
    private int waterAmount;

    public GrowingTips() {
    }

    public GrowingTips(double temperature, boolean isPhotophilous, int waterAmount) {
        setTemperature(temperature);
        setPhotophilous(isPhotophilous);
        setWaterAmount(waterAmount);
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public boolean isPhotophilous() {
        return isPhotophilous;
    }

    public void setPhotophilous(boolean photophilous) {
        isPhotophilous = photophilous;
    }

    public int getWaterAmount() {
        return waterAmount;
    }

    public void setWaterAmount(int waterAmount) {
        if (waterAmount < 0) {
            throw new IllegalArgumentException("Watering in Week can not be negative number");
        }
        this.waterAmount = waterAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (this == o) return true;
        GrowingTips gT = (GrowingTips) o;

        return Double.doubleToLongBits(temperature) == Double.doubleToLongBits(gT.temperature) &&
                isPhotophilous == gT.isPhotophilous &&
                waterAmount == gT.waterAmount;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp = Double.doubleToLongBits(temperature);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + (isPhotophilous ? 1231 : 1237);
        result = prime * result + waterAmount;
        return result;
    }

    @Override
    public String toString() {
        return "GrowingTips{" +
                "temperature=" + temperature +
                ", isPhotophilous=" + isPhotophilous +
                ", waterAmount=" + waterAmount +
                '}';
    }
}
