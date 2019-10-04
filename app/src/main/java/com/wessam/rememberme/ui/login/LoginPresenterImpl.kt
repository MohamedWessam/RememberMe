package com.wessam.rememberme.ui.login

import android.widget.TextView
import com.wessam.rememberme.utils.SharedPreferencesManager

class LoginPresenterImpl(
    private var loginView: LoginView,
    private val sharedPreferencesManager: SharedPreferencesManager) : LoginPresenter {

    override fun onOkButtonClicked(textView: TextView, name: String, language: String) {
        if (isTextViewEmpty(textView)) {
            loginView.showRequiredFieldError()
            sharedPreferencesManager.setIsFirstLogin(true)
        }
        else {
            saveUserData(name, language)
            loginView.openMainActivity()
        }
    }

    private fun saveUserData(name: String, language: String) {
        with(sharedPreferencesManager) {
            setUserName(name)
            setAppLanguage(language)
            setLoginStatus(true)
            setIsFirstLogin(true)
        }
    }

    private fun isTextViewEmpty(textView: TextView): Boolean {
        return (textView.text.trim().toString().isEmpty())
    }
}