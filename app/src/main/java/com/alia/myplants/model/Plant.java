package com.alia.myplants.model;

/**
 * Created by Alyona on 04.12.2017.
 */

public class Plant {
    private int mId;
    private String mName;
    private String mWater;

    public Plant(int id, String name, String water) {
        mId = id;
        mName = name;
        mWater = water;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getWater() {
        return mWater;
    }

    public void setWater(String water) {
        mWater = water;
    }
}
