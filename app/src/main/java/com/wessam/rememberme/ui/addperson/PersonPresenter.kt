package com.wessam.rememberme.ui.addperson

import android.net.Uri

interface PersonPresenter {

    fun finishPersonActivity()

    fun createPerson(
        personName: String,
        personPhone: String,
        relationShipId: Int,
        callPeriod: Int
    )

    fun contactName(): String?

    fun contactNumber(): String?

    fun uriContact(uri: Uri)

    fun convertCallPeriodId(id: Int): Int

}