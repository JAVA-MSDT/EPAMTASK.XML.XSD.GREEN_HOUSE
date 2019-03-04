package com.epam.javast.greenhouse.logic.parser.jaxbparser;

import com.epam.javast.greenhouse.model.api.Plant;
import com.epam.javast.greenhouse.model.entity.Flower;
import com.epam.javast.greenhouse.model.entity.Vegetable;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "plants", namespace = "http://www.example.com/plants")
public class Plants {

    private List<Plant> plants;

    public Plants(){
        plants = new ArrayList<>();
    }

    public Plants(List<Plant> plants){
        this.plants = plants;
    }

    @XmlElementRefs(value = {@XmlElementRef(name = "flower", type = Flower.class, namespace = "http://www.example.com/plants"),
    @XmlElementRef(namespace = "vegetable", type = Vegetable.class, name = "http://www.example.com/plants")})
    public List<Plant> getPlants() {
        return plants;
    }

    public void setPlants(List<Plant> plants) {
        this.plants = plants;
    }

    public boolean addPlant(Plant plant){
        return plants.add(plant);
    }
    @Override
    public String toString() {
        return "Plants{" +
                "plants=" + plants +
                '}';
    }
}
