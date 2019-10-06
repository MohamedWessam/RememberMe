package com.wessam.rememberme.ui.addperson

import android.net.Uri
import com.wessam.rememberme.utils.SharedPreferencesManager

class PersonPresenterImpl(
    private val view: AddPersonView,
    private val sharedPreferences: SharedPreferencesManager,
    private val interactor: PersonInteractor
) : PersonPresenter {

    override fun createPerson(personName: String, personPhone: String, relationShipId: Int, callPeriod: Int) {
        when{
            personName.isBlank() -> view.showNameError()
            personPhone.isBlank() -> view.showMobileNumberError()
            else ->{
                interactor.createPerson(personName, personPhone, relationShipId, callPeriod)
                view.finishActivity()
            }
        }
    }

    override fun finishPersonActivity() {
        view.finishActivity()
    }

    override fun contactName(): String? {
        return interactor.getContactName()
    }

    override fun contactNumber(): String? {
        return interactor.getContactNumber()
    }

    override fun uriContact(uri: Uri) {
        interactor.uriContact(uri)
    }

    override fun convertCallPeriodId(id: Int): Int {
        return when (id){
            1 -> 1
            2 -> 7
            3 -> 30
            else -> 0
        }
    }

}