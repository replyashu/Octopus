package com.ashu.ocotopus.util

fun Double.toSinglePrecision(): Double {
    return String.format("%.1f", this).toDouble()
}