package com.alia.myplants.model;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by Alyona on 04.12.2017.
 */

public class Plant extends RealmObject {
    @PrimaryKey
    @Required
    private String id;
    private String name;
    private String imageName;
    private Integer water;
    private Integer fertilizer;
    private String description;

    public Plant() {
        this(null, null, 0, 0, null);

    }

    public Plant(String name, String imageName, int water, int fertilizer, String description) {
        id = UUID.randomUUID().toString();
        this.name = name;
        this.imageName = imageName;
        this.water = water;
        this.fertilizer = fertilizer;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public int getFertilizer() {
        return fertilizer;
    }

    public void setFertilizer(int fertilizer) {
        this.fertilizer = fertilizer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
