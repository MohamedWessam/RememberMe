package com.wessam.rememberme.ui.main

import com.wessam.rememberme.utils.SharedPreferencesManager

class MainActivityPresenterImpl(var mainActivityView: MainActivityView, var sharedPreferencesManager: SharedPreferencesManager) : MainActivityPresenter {

    override fun onFirstLogin() {
        if (sharedPreferencesManager.getIsFirstLogin()){
            mainActivityView.showWelcomeDialog()
            sharedPreferencesManager.setIsFirstLogin(false)
        }
    }

    override fun getUserName(): String {
        return sharedPreferencesManager.getUserName()!!
    }

}