package com.moo.tpw.groovy

import org.apache.commons.lang3.ObjectUtils
import org.apache.commons.lang3.StringUtils
import org.junit.Before
import org.junit.Test

/**
 * Created by req69775 on 2/5/18.
 */
class MapTest {

    private static final Map<List<String>, List<String>> roleToRequiredLdapGroupListMap = [
            "BILL_ADMIN_EA_USER"    : ["EAW_BILLING", "GPS_READONLY"]
    ].asImmutable()

    @Before
    public void setup(){

    }

    @Test
    public void searchTest() {

        List<String> ldapGroups = ["EAW_BILLING", "GPS_READONLY", "XXXX", "YYYYY"]

        List<String> searchLdap = ["EAW_BILLING", "GPS_READONLY", "ZZZZ"]

        println(ldapGroups)
        println(searchLdap)
        println(ldapGroups.intersect(searchLdap))
    }
    @Test
    public void searchMap() {
        def roles = []
        List<String> ldapGroups = ["gps_readonly", "eaw_billing", "XXXX", "YYYYY"]

        roleToRequiredLdapGroupListMap.each {roleEntry ->
            println(roleEntry.getKey())
            println(roleEntry.getValue())


            List <String> upperGroups = ldapGroups.collect{it -> it.toUpperCase()}
            ldapGroups.eachWithIndex{it, idx -> ldapGroups[idx] = it.toUpperCase()}
            println(ldapGroups)
            boolean allRequiredLdapGroupsFound = ldapGroups.intersect(roleEntry.getValue()).size() == roleEntry.getValue().size()




//            boolean allRequiredLdapGroupsFound = true
//            roleEntry.getValue().each {requiredLdapGroup ->
//                println(requiredLdapGroup)
//                allRequiredLdapGroupsFound = ldapGroups.find {ldapGroup ->
//                    StringUtils.upperCase(ldapGroup) == requiredLdapGroup
//                }
//                println(allRequiredLdapGroupsFound)
//            }

            println(allRequiredLdapGroupsFound)
            if(allRequiredLdapGroupsFound) {
                roles.add(ObjectUtils.defaultIfNull(roleEntry.getKey(), []))
            }
            println(roles)
        }

    }
}
