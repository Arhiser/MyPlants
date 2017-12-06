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
    private String mId;
    private String mName;
    private String mImagePath;
    private int mWater;
    private int mFertilizer;
    private String mNotes;

    public Plant() {

    }

    public Plant(String name, String imagePath, int water, int fertilizer, String notes) {
        mId = UUID.randomUUID().toString();
        mName = name;
        mImagePath = imagePath;
        mWater = water;
        mFertilizer = fertilizer;
        mNotes = notes;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getImagePath() {
        return mImagePath;
    }

    public void setImagePath(String imagePath) {
        mImagePath = imagePath;
    }

    public int getWater() {
        return mWater;
    }

    public void setWater(int water) {
        mWater = water;
    }

    public int getFertilizer() {
        return mFertilizer;
    }

    public void setFertilizer(int fertilizer) {
        mFertilizer = fertilizer;
    }

    public String getNotes() {
        return mNotes;
    }

    public void setNotes(String notes) {
        mNotes = notes;
    }
}
