package com.moo.tpw.model.enr

import org.apache.commons.lang3.StringUtils

enum CompassAppliedToType {

    PARTICIPANT('participant'),
    SPOUSE('spouse'),
    PARTICIPANT_DEP('ppt-dep'),
    FAMILY('family'),
    DEPENDENT('dependent'),
    TIERED('tiered')

    private String stringValue

    CompassAppliedToType(String stringValue){
        this.stringValue = stringValue
    }

    String getStringValue(){
        return this.stringValue
    }

    static CompassAppliedToType getEnumFromString(String stringValue){
        for(CompassAppliedToType value : values()){
            if(StringUtils.equalsIgnoreCase(value.stringValue, stringValue)){
                return value
            }
        }
        return null
    }
}