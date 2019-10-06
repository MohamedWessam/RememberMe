package com.wessam.rememberme.ui.addperson

import android.content.Context
import android.net.Uri
import android.provider.ContactsContract
import com.wessam.rememberme.model.Person
import com.wessam.rememberme.ui.main.MainActivityInteractorImpl

class PersonInteractorImpl(private val context: Context) : PersonInteractor {

    lateinit var contactID: String
    lateinit var uriContact: Uri

    override fun createPerson(personName: String, personPhone: String, relationShipId: Int, callPeriodId: Int) {
        val person = Person(personName = personName, personPhone = personPhone, relationShipId = relationShipId, callPeriod = callPeriodId)
        MainActivityInteractorImpl.dbHandler.addPerson(context, person)
    }

    override fun uriContact(uri: Uri) {
        uriContact = uri
    }

    override fun getContactName(): String? {
        var contactName: String? = null

        val cursor = context.contentResolver.query(uriContact, null, null, null, null)

        if (cursor!!.moveToFirst()) { contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)) }
        cursor.close()

        return contactName
    }

    override fun getContactNumber(): String? {
        var contactNumber: String? = null

        val cursorID = context.contentResolver.query(
            uriContact,
            arrayOf(ContactsContract.Contacts._ID), null, null, null
        )

        if (cursorID!!.moveToFirst()) {
            contactID = cursorID.getString(cursorID.getColumnIndex(ContactsContract.Contacts._ID))
        }
        cursorID.close()

        val cursorPhone = context.contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER),
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ? AND " +
                    ContactsContract.CommonDataKinds.Phone.TYPE + " = " +
                    ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE,
            arrayOf(contactID), null
        )

        if (cursorPhone!!.moveToFirst()) {
            contactNumber = cursorPhone.getString(cursorPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
        }
        cursorPhone.close()

        return contactNumber
    }

}