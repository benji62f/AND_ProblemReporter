package com.lille1.lefebvreb.problemreporter.db.entity;

/**
 * Created by Benjamin on 14/12/2017.
 */

public enum ProblemTypeEnum {

    ARBRE_A_TAILLER("Arbre à tailler"),
    ARBRE_A_ABATTRE("Arbre à abattre"),
    DETRITUS("Détritus"),
    HAIE_A_TAILLER("Haie à tailler"),
    MAUVAISE_HERBE("Mauvaise herbe"),
    AUTRE("Autre");

    private String label;

    ProblemTypeEnum(String label){
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
