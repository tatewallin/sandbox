package com.moo.tpw.groovy

import org.junit.Before
import org.junit.Test

/**
 * Created by req69775 on 2/8/18.
 */
class EnumTest {

    private static final enum ResponseStatusCode {
        INTERNAL_CRITICAL_ERROR(-1),
        SUCCESS(0),
        WARNING(1),
        ERROR(2),
        IN_PROGRESS(3)

        private int code
        ResponseStatusCode(int code) {
            this.code = code
        }
        int code(){
            return code
        }
    }

    @Before
    public void setup(){

    }

    @Test
    public void searchTest() {
        assert [ResponseStatusCode.SUCCESS.code, ResponseStatusCode.ERROR.code].contains(2)
        assert ![ResponseStatusCode.SUCCESS.code, ResponseStatusCode.ERROR.code].contains(3)}
}
