package com.epam.javast.greenhouse.model.entity;

import com.epam.javast.greenhouse.model.enumeration.Color;

public class VisualParameter {

    private Color stalkColor;
    private Color leafColor;
    private double averageSize;

    public VisualParameter() {
    }

    public VisualParameter(Color stalkColor, Color leafColor, double averageSize) {
        setStalkColor(stalkColor);
        setLeafColor(leafColor);
        setAverageSize(averageSize);
    }

    public Color getStalkColor() {
        return stalkColor;
    }

    public void setStalkColor(Color stalkColor) {
        if(stalkColor == null){
            throw new IllegalArgumentException("Stalk Color can not be null");
        }
        this.stalkColor = stalkColor;
    }

    public Color getLeafColor() {
        return leafColor;
    }

    public void setLeafColor(Color leafColor) {
        if(stalkColor == null){
            throw new IllegalArgumentException("Leaf Color can not be null");
        }
        this.leafColor = leafColor;
    }

    public double getAverageSize() {
        return averageSize;
    }

    public void setAverageSize(double averageSize) {
        if(averageSize <= 0){
            throw new IllegalArgumentException("Average Size can not be 0 or less");
        }
        this.averageSize = averageSize;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (this == o) return true;
        VisualParameter vP = (VisualParameter) o;
        return Double.doubleToLongBits(averageSize) == Double.doubleToLongBits(vP.averageSize) &&
                stalkColor == vP.stalkColor &&
                leafColor == vP.leafColor;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((stalkColor == null) ? 0 : stalkColor.hashCode());
        result = prime * result + ((leafColor == null) ? 0 : leafColor.hashCode());
        long temp = Double.doubleToLongBits(averageSize);
        result = prime * result + (int) (temp ^ (temp >>> 32));

        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                "stalkColor=" + stalkColor +
                ", leafColor=" + leafColor +
                ", averageSize=" + averageSize +
                '}';
    }
}
