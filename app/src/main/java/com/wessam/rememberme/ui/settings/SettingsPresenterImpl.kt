package com.wessam.rememberme.ui.settings

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.wessam.rememberme.R
import com.wessam.rememberme.utils.SharedPreferencesManager

class SettingsPresenterImpl(private var settingsView: SettingsView, private val sharedPreferencesManager: SharedPreferencesManager) : SettingsPresenter {

    override fun onEditNameClicked() {
        settingsView.showEditNameDialog()
    }

    override fun setNewName(alertDialog: AlertDialog, editText: EditText, context: Context) {
        if (editText.text.toString().trim().isEmpty())
            editText.error = context.resources.getString(R.string.name_required)
             else {
            sharedPreferencesManager.setUserName(editText.text.toString())
            alertDialog.dismiss()
        }
    }

    override fun onEditLanguageClicked() {
        settingsView.showEditLanguageDialog()
    }


    override fun editLanguage(language: String?, msg: String) {
        language?.let {
            sharedPreferencesManager.setAppLanguage(it)
            settingsView.restartApp()
        }

    }

    override fun editName(name: String?, msg: String) {
        name?.let { sharedPreferencesManager.setUserName(it) }
        settingsView.showMessage(msg)

    }

    override fun onCopyButtonClicked(context: Context, msg: String) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip: ClipData = ClipData.newPlainText("mail", context.resources.getString(R.string.developer_mail))
        clipboard.primaryClip = clip

        settingsView.showMessage(msg)
    }

}