package com.wessam.rememberme.ui.addperson

import android.net.Uri

interface PersonInteractor {

    fun createPerson(personName: String, personPhone: String, relationShipId: Int, callPeriodId: Int)

    fun getContactName(): String?

    fun getContactNumber(): String?

    fun uriContact(uri: Uri)

}