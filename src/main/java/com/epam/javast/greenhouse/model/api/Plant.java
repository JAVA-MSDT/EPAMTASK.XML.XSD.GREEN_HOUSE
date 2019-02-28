package com.epam.javast.greenhouse.model.api;

import com.epam.javast.greenhouse.model.entity.GrowingTips;
import com.epam.javast.greenhouse.model.entity.VisualParameter;
import com.epam.javast.greenhouse.model.enumeration.*;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement
@XmlType(propOrder = {"id", "name", "soil", "origin", "visualParameter", "growingTips", "reproduction"})
public  class Plant {

    private int id;
    private String name;
    private Soil soil;
    private Origin origin;
    private VisualParameter visualParameter;
    private GrowingTips growingTips;
    private Reproduction reproduction;


    public Plant() {
    }

    public Plant(int id, String name, Soil soil, Origin origin, VisualParameter visualParameter,
                 GrowingTips growingTips, Reproduction reproduction) {
        setId(id);
        setName(name);
        setSoil(soil);
        setOrigin(origin);
        setVisualParameter(visualParameter);
        setGrowingTips(growingTips);
        setReproduction(reproduction);
    }

    public int getId() {
        return id;
    }

    @XmlAttribute
    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Plant Id has to be greater than 0");
        }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @XmlAttribute
    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Plant name can not be null or without name");
        }
        this.name = name;
    }

    public Soil getSoil() {
        return soil;
    }

    @XmlElement
    public void setSoil(Soil soil) {
        if (soil == null) {
            throw new IllegalArgumentException("Plant Soil can not be null");
        }
        this.soil = soil;
    }


    public Origin getOrigin() {
        return origin;
    }

    @XmlElement
    public void setOrigin(Origin origin) {
        if (origin == null) {
            throw new IllegalArgumentException("Plant Origin can not be null");
        }
        this.origin = origin;
    }

    public VisualParameter getVisualParameter() {
        return visualParameter;
    }

    @XmlElement
    public void setVisualParameter(VisualParameter visualParameter) {
        if (visualParameter == null) {
            throw new IllegalArgumentException("Plant Visual Parameter can not be null");
        }
        this.visualParameter = visualParameter;
    }

    public GrowingTips getGrowingTips() {
        return growingTips;
    }

    @XmlElement
    public void setGrowingTips(GrowingTips growingTips) {
        if (growingTips == null) {
            throw new IllegalArgumentException("Plant Growing Tips can not be null");
        }
        this.growingTips = growingTips;
    }

    public Reproduction getReproduction() {
        return reproduction;
    }

    @XmlElement
    public void setReproduction(Reproduction reproduction) {
        if (reproduction == null) {
            throw new IllegalArgumentException("Plant Reproduction can not be null");
        }
        this.reproduction = reproduction;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (this == o) return true;
        Plant p = (Plant) o;

        return id == p.id &&
                equalHelper(name, p.name) &&
                soil == p.soil &&
                origin == p.origin &&
                equalHelper(visualParameter, p.visualParameter) &&
                equalHelper(growingTips, p.growingTips) &&
                reproduction == p.reproduction;
    }

    private boolean equalHelper(Object a, Object b) {
        return (a == b) || (a != null && a.equals(b));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((soil == null) ? 0 : soil.hashCode());
        result = prime * result + ((origin == null) ? 0 : origin.hashCode());
        result = prime * result + ((visualParameter == null) ? 0 : visualParameter.hashCode());
        result = prime * result + ((growingTips == null) ? 0 : growingTips.hashCode());
        result = prime * result + ((reproduction == null) ? 0 : reproduction.hashCode());

        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", soil=" + soil +
                ", origin=" + origin +
                ", visualParameter=" + visualParameter +
                ", growingTips=" + growingTips +
                ", reproduction=" + reproduction +
                '}';
    }
}
