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
public class ProblemTypeEntity extends BaseModel {
    @Column
    @PrimaryKey
    private int id;

    @NotNull
    @Column
    private String name;

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
}
