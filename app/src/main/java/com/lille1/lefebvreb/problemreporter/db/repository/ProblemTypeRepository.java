package com.lille1.lefebvreb.problemreporter.db.repository;

import com.lille1.lefebvreb.problemreporter.db.entity.ProblemTypeEntity;
import com.lille1.lefebvreb.problemreporter.db.entity.ProblemTypeEntity_Table;
import com.lille1.lefebvreb.problemreporter.db.entity.ProblemTypeEnum;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.List;

/**
 * Created by Benjamin on 14/12/2017.
 */

public class ProblemTypeRepository {

    public static List<ProblemTypeEntity> getAll(){
        return SQLite.select().from(ProblemTypeEntity.class).queryList();
    }

    public static void clearTable(){
        List<ProblemTypeEntity> pbs = getAll();
        for(ProblemTypeEntity pb: pbs){
            pb.delete();
        }
    }

    public static ProblemTypeEntity getProblemByAuthor(String name){
        return new Select()
                .from(ProblemTypeEntity.class)
                .where(ProblemTypeEntity_Table.name.eq(name))
                .querySingle();
    }

    public static void initProblemTypes(){
        clearTable();
        ProblemTypeEntity pb1 = new ProblemTypeEntity();
        pb1.setName(ProblemTypeEnum.ARBRE_A_TAILLER.getLabel());
        pb1.save();

        ProblemTypeEntity pb2 = new ProblemTypeEntity();
        pb2.setName(ProblemTypeEnum.ARBRE_A_ABATTRE.getLabel());
        pb2.save();

        ProblemTypeEntity pb3 = new ProblemTypeEntity();
        pb3.setName(ProblemTypeEnum.DETRITUS.getLabel());
        pb3.save();

        ProblemTypeEntity pb4 = new ProblemTypeEntity();
        pb4.setName(ProblemTypeEnum.HAIE_A_TAILLER.getLabel());
        pb4.save();

        ProblemTypeEntity pb5 = new ProblemTypeEntity();
        pb5.setName(ProblemTypeEnum.MAUVAISE_HERBE.getLabel());
        pb5.save();

        ProblemTypeEntity pb6 = new ProblemTypeEntity();
        pb6.setName(ProblemTypeEnum.AUTRE.getLabel());
        pb6.save();
    }
}
