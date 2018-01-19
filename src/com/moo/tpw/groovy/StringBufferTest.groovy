package com.moo.tpw.groovy

/**
 * Created by req69775 on 1/17/18.
 */
class StringBufferTest {
    static void main(String[] args) {
        StringBuffer sb = new StringBuffer()
        String NL = "\n"

        Calendar cal = Calendar.getInstance()
        sb.append(NL).append("-------------------------").append(NL).append("Error Information").append(NL).append("-------------------------").append(NL)
        sb.append("Time of Error = ").append(cal.getTime()).append(NL)
        sb.append("SystemMessage.Prefix = Web").append(NL)
        sb.append("SystemMessage.Number = Error").append(NL)
        sb.append(NL)
        println(sb.toString())

        StringBuffer sb2 = new StringBuffer()
        sb2.append("${NL}-------------------------${NL}Error Information${NL}-------------------------")
        sb2.append("${NL}Time of Error = ${cal.getTime()}")
        sb2.append("${NL}SystemMessage.Prefix = Web")
        sb2.append("${NL}SystemMessage.Number = Error")
        sb2.append(NL)
        println(sb2.toString())
    }
}
