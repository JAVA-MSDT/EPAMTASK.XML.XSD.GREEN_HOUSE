package com.epam.javast.greenhouse.logic.parser.api;

import com.epam.javast.greenhouse.model.api.Plant;

import java.util.List;

public interface XmlParser {
    List<Plant> plantList(String xmlFile);
}
