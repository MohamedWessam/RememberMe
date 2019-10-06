package com.wessam.rememberme.ui.login

import com.wessam.rememberme.utils.SharedPreferencesManager

class LoginPresenterImpl(private var view: LoginView, private val sharedPreferences: SharedPreferencesManager) : LoginPresenter {

    override fun createUser(name: String, language: String) {
        if (name.isBlank()) {
            view.showRequiredFieldError()
        }
        else {
            saveUserData(name, language)
            view.openMainActivity()
        }
    }

    private fun saveUserData(name: String, language: String) {
        with(sharedPreferences) {
            setUserName(name)
            setAppLanguage(language)
            setLoginStatus(true)
            setIsFirstLogin(true)
        }
    }

}