package com.moo.tpw.model.enr

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class Address {
    Long addressId
    String city
    State state
    String country
    String zip
    String street1
    String street2
}
