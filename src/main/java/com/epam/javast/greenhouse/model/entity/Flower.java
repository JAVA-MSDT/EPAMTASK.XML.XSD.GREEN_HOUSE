package com.epam.javast.greenhouse.model.entity;

import com.epam.javast.greenhouse.model.api.Plant;
import com.epam.javast.greenhouse.model.enumeration.Origin;
import com.epam.javast.greenhouse.model.enumeration.Reproduction;
import com.epam.javast.greenhouse.model.enumeration.Size;
import com.epam.javast.greenhouse.model.enumeration.Soil;


public class Flower extends Plant {

    private int petalsQuantity;
    private Size size;
    private boolean isPoison;

    public Flower() {
    }

    public Flower(int id, String name, Soil soil, Origin origin, VisualParameter visualParameter, GrowingTips growingTips, Reproduction reproduction, int PetalsQuantity, Size size, boolean isPoison) {
        super(id, name, soil, origin, visualParameter, growingTips, reproduction);
        setPetalsQuantity(PetalsQuantity);
        setSize(size);
        setPoison(isPoison);
    }

    public int getPetalsQuantity() {
        return petalsQuantity;
    }

    public void setPetalsQuantity(int petalsQuantity) {
        if(petalsQuantity <= 0){
            throw new IllegalArgumentException("Quantity of petals can not be null");
        }
        this.petalsQuantity = petalsQuantity;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        if(size == null){
            throw new IllegalArgumentException("Size can not be null");
        }
        this.size = size;
    }

    public boolean isPoison() {
        return isPoison;
    }

    public void setPoison(boolean poison) {
        isPoison = poison;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (this == o) return true;
        if (!super.equals(o)) return false;
        Flower f = (Flower) o;
        return petalsQuantity == f.petalsQuantity &&
                isPoison == f.isPoison &&
                size == f.size;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + petalsQuantity;
        result = prime * result + ((size == null) ? 0 : size.hashCode());
        result = prime * result + (isPoison ? 1231 : 1237);
        return result;
    }

    @Override
    public String toString() {
        return super.toString() + " " + getClass().getSimpleName() + " {" +
                "petalsQuantity=" + petalsQuantity +
                ", size=" + size +
                ", isPoison=" + isPoison +
                '}';
    }
}
