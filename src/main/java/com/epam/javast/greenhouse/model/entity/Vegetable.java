package com.epam.javast.greenhouse.model.entity;

import com.epam.javast.greenhouse.model.api.Plant;
import com.epam.javast.greenhouse.model.enumeration.Origin;
import com.epam.javast.greenhouse.model.enumeration.Reproduction;
import com.epam.javast.greenhouse.model.enumeration.Season;
import com.epam.javast.greenhouse.model.enumeration.Soil;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"weight", "season", "isSweet"})

public class Vegetable extends Plant {

    private double weight;
    private Season season;
    private boolean isSweet;

    public Vegetable() {

    }

    public Vegetable(int id, String name, Soil soil, Origin origin, VisualParameter visualParameter,
                     GrowingTips growingTips, Reproduction reproduction, double weight, Season season, boolean isSweet) {
        super(id, name, soil, origin, visualParameter, growingTips, reproduction);
        setWeight(weight);
        setSeason(season);
        setSweet(isSweet);
    }

    public double getWeight() {
        return weight;
    }

    @XmlElement
    public void setWeight(double weight) {
        if(weight <= 0){
            throw new IllegalArgumentException("Vegetable weight can not be less than or equal 0");
        }
        this.weight = weight;
    }

    public Season getSeason() {
        return season;
    }

    @XmlElement
    public void setSeason(Season season) {
        if(season == null){
            throw new IllegalArgumentException("Vegetable season can not be null");
        }
        this.season = season;
    }

    public boolean isSweet() {
        return isSweet;
    }

    @XmlElement
    public void setSweet(boolean sweet) {
        isSweet = sweet;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (this == o) return true;
        if (!super.equals(o)) return false;
        Vegetable v = (Vegetable) o;
        return Double.doubleToLongBits(weight) == Double.doubleToLongBits(v.weight) &&
                isSweet == v.isSweet &&
                season == v.season;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        long temp = Double.doubleToLongBits(weight);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((season == null) ? 0 : season.hashCode());
        result = prime * result + (isSweet ? 1231 : 1237);
        return result;
    }

    @Override
    public String toString() {
        return super.toString() + " " + getClass().getSimpleName() + " {" +
                "weight=" + weight +
                ", season=" + season +
                ", isSweet=" + isSweet +
                '}';
    }
}
