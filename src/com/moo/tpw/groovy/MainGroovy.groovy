package com.moo.tpw.groovy

import com.moo.tpw.model.enr.CompassProductType
import com.moo.tpw.model.enr.Coverage
import org.junit.Before
import org.junit.Test


class MainGroovy {
    @Before
    public void setup(){

    }

    @Test
    public void createTranslateArrayThenReplaceValuesInString() {
        Coverage cov1 = new Coverage(planId: '11111111', compassProductType: CompassProductType.LTD)
        Coverage cov2 = new Coverage(planId: '22222222', compassProductType: CompassProductType.STD, secondaryPlanId: '222223333', secondaryCompassProductType: CompassProductType.VADD)
        Coverage cov3 = new Coverage(planId: '11111111', compassProductType: CompassProductType.LTD)
        Coverage cov4 = new Coverage(planId: '22222222', compassProductType: CompassProductType.STD, secondaryPlanId: '222223333', secondaryCompassProductType: CompassProductType.VADD)
        def coverages = [cov1, cov2, cov3, cov4]


        def translateList =[]
        coverages.each {it ->
            if(!translateList.find{it2 -> it2.planId == it.planId}) {
                translateList.push([planId: it.planId, compassProductType: it.compassProductType])
            }
            if (it.secondaryPlanId && !translateList.find{it2 -> it2.planId == it.secondaryPlanId}) {
                translateList.push([planId: it.secondaryPlanId, compassProductType: it.secondaryCompassProductType])
            }
        }


        def json = "\n Plan id 11111111" +
                "\n Plan id 123445234" +
                "\n Plan id 22222222" +
                "\n Plan id 11111111" +
                "\n Plan id 873878347" +
                "\n Plan id 22222222" +
                "\n Plan id 222223333" +
                "\n Plan id 222223333" +
                "\n Plan id 222223333" +
                "\n Plan id 11111111"

        println(json)
        translateList.each {it ->
            json = json.replaceAll(it.planId, it.compassProductType.toString())
        }
        println(json)



        //println(coverages)
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
