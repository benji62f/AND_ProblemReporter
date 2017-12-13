package com.lille1.lefebvreb.problemreporter.db.entity;

import com.lille1.lefebvreb.problemreporter.db.ProblemReporterDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
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
    @PrimaryKey
    private int id;

    @NotNull
    @Column
    private String name;

    @NotNull
    @Column
    @ForeignKey
    private ProblemTypeEntity type;

    @NotNull
    @Column
    private String address;

    @Column
    private String description;

    @NotNull
    @Column
    private String latitude;

    @NotNull
    @Column
    private String longitude;

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

    public ProblemTypeEntity getType() {
        return type;
    }

    public void setType(ProblemTypeEntity type) {
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

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
