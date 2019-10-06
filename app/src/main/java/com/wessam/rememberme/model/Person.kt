package com.wessam.rememberme.model

data class Person(
    var personId: Int? = 0,
    var personName: String? = null,
    var personPhone: String? = null,
    var callPeriod: Int? = 0,
    var relationShipId: Int? = 0
)