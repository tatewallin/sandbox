package com.moo.tpw.model.enr

import com.fasterxml.jackson.annotation.JsonInclude
import org.joda.time.LocalDate

@JsonInclude(JsonInclude.Include.NON_EMPTY)
class Demographic {

    Long personId
    Integer coreAdminPersonId
    String ssn
    String payrollNumber
    String firstName
    String middleName
    String lastName
    LocalDate birthDate
    Gender gender
    PersonType relationship
    Boolean smoker
    Boolean everSmoked
    LocalDate smokerEffectiveDate
    Boolean dependentStop
    Boolean changeStop

    Boolean duplicateSsn = Boolean.FALSE

    Address address = new Address()

    String firstLast() {
        return "${firstName} ${lastName}"
    }
}
