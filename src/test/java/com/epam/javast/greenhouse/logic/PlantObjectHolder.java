package com.epam.javast.greenhouse.logic;

import com.epam.javast.greenhouse.model.entity.Flower;
import com.epam.javast.greenhouse.model.entity.GrowingTips;
import com.epam.javast.greenhouse.model.entity.Vegetable;
import com.epam.javast.greenhouse.model.entity.VisualParameter;
import com.epam.javast.greenhouse.model.enumeration.*;

/**
 * This class holds few methods to return a valid or invalid floswer, vegetable objects in order to use them in several
 * test cases.
 */
public class PlantObjectHolder {


    public Flower getValidFlower(){

        return new Flower(1, "Rose", Soil.PODZOL, Origin.AFRICA, getFlowerVisualParameter(), getFlowerGrowingTips(),
                Reproduction.SEED, 20, Size.SMALL, false);
    }

    public Vegetable getValidVegetable(){
        return new Vegetable(2, "Carrot", Soil.GROUND, Origin.EUROPE, getVegetableVisualParameter(), getVegetableGrowingTips(),
                Reproduction.CUTTING, 50, Season.SUMMER, true);
    }

    public Flower getInValidFlower(){

        return new Flower(1, "Rose", Soil.GROUND, Origin.AFRICA, getFlowerVisualParameter(), getFlowerGrowingTips(),
                Reproduction.SEED, 20, Size.SMALL, true);
    }

    public Vegetable getInValidVegetable(){
        return new Vegetable(2, "carrot", Soil.GROUND, Origin.SOUTH_AMERICA, getVegetableVisualParameter(), getVegetableGrowingTips(),
                Reproduction.CUTTING, 50, Season.SUMMER, true);
    }

    public VisualParameter getFlowerVisualParameter() {
        return new VisualParameter(Color.ROSE, Color.GREEN, 1.5);
    }

    public GrowingTips getFlowerGrowingTips(){
        return new GrowingTips(20, true, 4);
    }

    public VisualParameter getVegetableVisualParameter() {
        return new VisualParameter(Color.YELLOW, Color.GREEN, 5);
    }

    public GrowingTips getVegetableGrowingTips(){
        return new GrowingTips(30, false, 6);
    }
}
