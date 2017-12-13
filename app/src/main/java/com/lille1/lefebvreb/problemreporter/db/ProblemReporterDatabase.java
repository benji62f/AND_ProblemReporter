package com.lille1.lefebvreb.problemreporter.db;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by lefebvreb on 13/12/17.
 */

@Database(name = ProblemReporterDatabase.NAME, version = ProblemReporterDatabase.VERSION)
public class ProblemReporterDatabase {
    public static final String NAME = "MyDataBase";

    public static final int VERSION = 1;
}
