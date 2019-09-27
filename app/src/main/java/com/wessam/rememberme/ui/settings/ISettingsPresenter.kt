package com.wessam.rememberme.ui.settings

import android.content.Context
import org.intellij.lang.annotations.Language

interface ISettingsPresenter{

    fun showEditNameDialog(context: Context)

    fun showEditLanguageDialog(context: Context)

    fun editName(name: String)

    fun editLanguage(language: String)

}