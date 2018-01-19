package com.moo.tpw.groovy

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.moo.tpw.model.enr.Address
import com.moo.tpw.model.enr.Demographic
import com.moo.tpw.model.enr.State
import groovy.json.JsonBuilder
import org.joda.time.LocalDate

class JSONObjectPrintTest {

    static void main(String[] args) {
        //test1()
        test2()
    }


    static void test1(){
        println("/n/n test1...")
        Address address = new Address(addressId: 1, city: 'omaha', state: State.CALIFORNIA)
        println(new JsonBuilder(address).toPrettyString())

        Demographic demographic = new Demographic(
                ssn: '123456789',
                payrollNumber: '11111111',
                firstName: 'Tate',
                address: address,
                //birthDate: new LocalDate(),
        )
        println(new JsonBuilder(demographic).toPrettyString())
    }

    static void test2(){
        println("/n/n test2...")

        Address address = new Address(addressId: 1, city: 'omaha', state: State.CALIFORNIA)
        Demographic demographic = new Demographic(
                ssn: '123456789',
                payrollNumber: '11111111',
                firstName: 'Tate',
                address: address,
                birthDate: new LocalDate().now(),
        )
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
                .setDateFormat("yyyy-MM-dd")
                .setPrettyPrinting()
                .create()


        println(gson.toJson(demographic))
    }

}
//Long personId
//Integer coreAdminPersonId
//String ssn
//String payrollNumber
//String firstName
//String middleName
//String lastName
//LocalDate birthDate
//Gender gender
//PersonType relationship
//Boolean smoker
//Boolean everSmoked
//LocalDate smokerEffectiveDate
//Boolean dependentStop
//Boolean changeStop
//
//Boolean duplicateSsn = Boolean.FALSE
//
//Address address = new Address()