package com.gid.identifyperson.utils

import java.lang.StringBuilder

/**
 * @Author: Khimheang DARA
 * @Date: 14/12/21
 */
object FormatUtils {

    /**
     * Return CardNumberFormat String
     * @param cardNumber String
     * @return String
     */
    fun cardNumberFormat(cardNumber: String): String {
        val result = StringBuilder()
        for (i in cardNumber.indices) {
            if (i % 4 == 0 && i != 0 && i < 16) {
                result.append("\t\t")
            }
            result.append(cardNumber[i])
        }

        return result.toString()
    }

    /**
     * Return AccountNumberFormat String
     * @param accountNumber String
     * @return String
     */
    fun accountNumberFormat(accountNumber: String): String {
        val result = StringBuilder()
        for (i in accountNumber.indices) {
            if (i % 3 == 0 && i != 0) {
                result.append("\t")
            }
            result.append(accountNumber[i])
        }

        return result.toString()
    }
}