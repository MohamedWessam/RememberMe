package com.wessam.rememberme.ui.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wessam.rememberme.R
import com.wessam.rememberme.base.ParentActivity
import com.wessam.rememberme.ui.login.ILoginPresenter
import com.wessam.rememberme.ui.login.LoginPresenter
import com.wessam.rememberme.utils.SharedPreferencesManager
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.dialog_language.*
import kotlinx.android.synthetic.main.dialog_user_name.*

class SettingsActivity : ParentActivity(), SettingsView {

    override fun initializeComponents() {
        toolbarTitle = R.string.settings

        val iSettingsPresenter = SettingsPresenter(this, mSharedPreferences)


        val languageId = rg_language_dialog.checkedRadioButtonId

       // val language = if (languageId == 0) "ar" else "en"

        btn_edit_name.setOnClickListener { iSettingsPresenter.showEditNameDialog(this) }
        btn_dialog_ok.setOnClickListener { iSettingsPresenter.editName(et_dialog_user.text.toString()) }

        btn_edit_language.setOnClickListener { iSettingsPresenter.showEditLanguageDialog(this) }
    //    btn_language_dialog_ok.setOnClickListener { iSettingsPresenter.editLanguage() }


    }

    override fun getLayoutResource() = R.layout.activity_settings

    override fun isFullScreen() = false

    override fun isEnabledToolbar() = true

    override fun isEnabledBack() = true

    override fun isOrientationEnabled() = false

}
