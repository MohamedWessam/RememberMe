package com.wessam.rememberme.ui.main

import com.wessam.rememberme.model.Person
import com.wessam.rememberme.utils.SharedPreferencesManager

class MainActivityPresenterImpl(
    var view: MainActivityView,
    var sharedPreferences: SharedPreferencesManager,
    private val interactor: MainActivityInteractor
) : MainActivityPresenter {

    override fun checkFirstLogin() {
        if (sharedPreferences.getIsFirstLogin()) {
            view.showWelcomeDialog()
            sharedPreferences.setIsFirstLogin(false)
        }
    }

    override fun getUserName(): String {
        return sharedPreferences.getUserName()!!
    }

    override fun getPerson(): ArrayList<Person> {
        return interactor.getPerson()
    }

}