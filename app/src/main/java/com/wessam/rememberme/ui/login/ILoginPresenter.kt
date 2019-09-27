package com.wessam.rememberme.ui.login

interface ILoginPresenter {

    fun displayError()

    fun saveUserData(name: String, language: String)

}