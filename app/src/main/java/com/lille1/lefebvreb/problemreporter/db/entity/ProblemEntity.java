package com.lille1.lefebvreb.problemreporter.db.entity;

import com.lille1.lefebvreb.problemreporter.db.ProblemReporterDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.NotNull;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by lefebvreb on 13/12/17.
 */

@Table(database = ProblemReporterDatabase.class)
public class ProblemEntity extends BaseModel {
    @Column
    @PrimaryKey(autoincrement = true)
    private int id;

    @NotNull
    @Column
    private String name;

    @NotNull
    @Column
    private String type;

    @NotNull
    @Column
    private String address;

    @Column
    private String description;

    @NotNull
    @Column
    private double latitude;

    @NotNull
    @Column
    private double longitude;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
