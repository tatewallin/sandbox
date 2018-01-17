package com.moo.tpw.groovy

import org.junit.Before
import org.junit.Test


class MainGroovy {
    @Before
    public void setup(){

    }

    @Test
    public void random(){
        def queryParams = ['groupNumber':'g0001234', 'assigneeType': 'assingeetype']
       // queryParams.add('groupNumber':'g0001234')
        //queryParams[assigneeType] =  'assingeetype'
        queryParams['test'] = '123'
        println(queryParams)
    }

    @Test
    public void buildJira() {
        def builder = new groovy.json.JsonBuilder()

        String groupNumber = 'G000AN7L'
        String descriptionText = 'description text...'

        //def customFieldArray = getGroupInfoServiceData(groupNumber)
        def root = builder.fields {
            project (key: "GPS")
            summary groupNumber?.toUpperCase()
            description descriptionText
            issuetype (id: "18")
            customfield_12000 ([groupNumber])
            customfield_10808 "N/A"
            customfield_16101 (["Yes"])
            customfield_11000 (id: "11400")
            customfield_15400 (id: "13608")
          //  customFieldArray.each {customFieldName, value ->
          //      "$customFieldName" value
          //  }
        }

        println(builder.toPrettyString())
    }
}
