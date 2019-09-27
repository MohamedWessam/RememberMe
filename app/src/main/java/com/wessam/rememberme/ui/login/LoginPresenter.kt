package com.wessam.rememberme.ui.login

import com.wessam.rememberme.utils.SharedPreferencesManager

class LoginPresenter(var loginView: LoginView, var sharedPreferencesManager: SharedPreferencesManager) : ILoginPresenter {

    override fun saveUserData(name: String, language: String) {
        sharedPreferencesManager.setUserName(name)
        sharedPreferencesManager.setAppLanguage(language)
        sharedPreferencesManager.setLoginStatus(true)
        sharedPreferencesManager.setIsFirstLogin(true)
    }

    override fun displayError() {

    }
}