package com.hxb.baselib.utils

import java.math.BigDecimal


/**
 * 把[value]四舍五入保留[decimalPlaces]位小数
 */
fun roundToNDecimalPlaces(value: Double, decimalPlaces: Int = 2): Double {
    return "%.${decimalPlaces}f".format(value).toDouble()
}

/**
 * double值相乘，高精度，计算结果四舍五入保留[decimalPlaces]位小数
 */
fun multiplyDoubles(vararg values: Double, decimalPlaces: Int = 2): Double {
    if (values.isEmpty()) {
        throw IllegalArgumentException("At least two values are required for multiplication.")
    }
    var result = BigDecimal.valueOf(1.0)
    for (value in values) {
        val bigDecimalValue = BigDecimal.valueOf(value)
        result = result.multiply(bigDecimalValue)
    }
    return roundToNDecimalPlaces(result.toDouble(), decimalPlaces)
}


/**
 * double除法，高精度，计算结果四舍五入保留[decimalPlaces]位小数
 */
fun divideTwoDoubles(dividend: Double, divisor: Double, decimalPlaces: Int): Double {
    if (divisor == 0.0) {
        throw IllegalArgumentException("Division by zero is not allowed.")
    }
    val bigDecimalDividend = BigDecimal.valueOf(dividend)
    val bigDecimalDivisor = BigDecimal.valueOf(divisor)
    val result = bigDecimalDividend.divide(bigDecimalDivisor, decimalPlaces, BigDecimal.ROUND_HALF_UP)
    return result.toDouble()
}