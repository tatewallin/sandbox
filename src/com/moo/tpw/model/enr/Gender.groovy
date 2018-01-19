package com.moo.tpw.model.enr

import com.fasterxml.jackson.annotation.JsonCreator

enum Gender {
    MALE("M"),FEMALE("F"),UNKNOWN("-")

    private String abbreviation

    private Gender(String abbreviation) {
        this.abbreviation = abbreviation
    }

    String getAbbreviation() {
        return this.abbreviation
    }

    @JsonCreator
    static Gender getFromString(String value) {
        for (Gender gender : values()) {
            if (gender.toString().equalsIgnoreCase(value)
                    || gender.abbreviation.equalsIgnoreCase(value)) {
                return gender
            }
        }
        return null
    }
}