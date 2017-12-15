package com.lille1.lefebvreb.problemreporter.db.repository;

import com.lille1.lefebvreb.problemreporter.db.entity.ProblemEntity;
import com.lille1.lefebvreb.problemreporter.db.entity.ProblemTypeEntity;
import com.lille1.lefebvreb.problemreporter.db.entity.ProblemTypeEnum;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

/**
 * Created by Benjamin on 14/12/2017.
 */

public class ProblemRepository {

    public static List<ProblemEntity> getAll(){
        return SQLite.select().from(ProblemEntity.class).queryList();
    }

    public static void insert(String name, ProblemTypeEntity type, String address, String description, double latitude, double longitude){
        ProblemEntity problemEntity = new ProblemEntity();
        problemEntity.setType(type);
        problemEntity.setName(name);
        problemEntity.setAddress(address);
        problemEntity.setDescription(description);
        problemEntity.setLatitude(latitude);
        problemEntity.setLongitude(longitude);

        problemEntity.save();
    }

    public static void clearTable(){
        List<ProblemEntity> pbs = getAll();
        for(ProblemEntity pb: pbs){
            pb.delete();
        }
    }
}
