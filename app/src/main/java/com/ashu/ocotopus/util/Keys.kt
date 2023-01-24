package com.ashu.ocotopus.util

object Keys {

    init {
        System.loadLibrary("native-lib")
    }

    external fun webClientKey(): String

}