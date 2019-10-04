package com.wessam.rememberme.ui.settings

import android.content.Context
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

interface SettingsPresenter{

    fun editName(name: String?, msg: String)

    fun editLanguage(language: String?, msg: String)

    fun onEditNameClicked()

    fun setNewName(alertDialog: AlertDialog, editText: EditText, context: Context)

    fun onEditLanguageClicked()

    fun onCopyButtonClicked(context: Context, msg: String)

}