package com.wessam.rememberme.ui.settings

interface SettingsView {

    fun showEditNameDialog()

    fun showEditLanguageDialog()

    fun restartApp()

    fun showMessage(msg: String)

    fun showLanguageError()

}