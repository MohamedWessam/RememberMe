package com.wessam.rememberme.ui.settings

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.wessam.rememberme.R
import com.wessam.rememberme.utils.SharedPreferencesManager
import kotlinx.android.synthetic.main.dialog_user_name.*


class SettingsPresenter(settingsView: SettingsView, var sharedPreferencesManager: SharedPreferencesManager) : ISettingsPresenter {


    override fun editLanguage(language: String) {
        sharedPreferencesManager.setAppLanguage(language)
    }

    override fun editName(name: String) {
        sharedPreferencesManager.setUserName(name)
    }


    override fun showEditNameDialog(context: Context) {
        val nameDialog = LayoutInflater.from(context).inflate(R.layout.dialog_user_name, null)
        val alertBuilder = AlertDialog.Builder(context).setView(nameDialog).show()

        alertBuilder.btn_dialog_cancel.setOnClickListener { alertBuilder.dismiss() }
        alertBuilder.btn_dialog_ok.setOnClickListener { alertBuilder.dismiss() }
    }


    override fun showEditLanguageDialog(context: Context) {
        val nameDialog = LayoutInflater.from(context).inflate(R.layout.dialog_language, null)
        val alertBuilder = AlertDialog.Builder(context).setView(nameDialog).show()

        alertBuilder.btn_dialog_cancel.setOnClickListener { alertBuilder.dismiss() }
        alertBuilder.btn_dialog_ok.setOnClickListener { alertBuilder.dismiss() }
    }


}