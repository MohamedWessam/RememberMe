package com.wessam.rememberme.ui.login

import android.widget.RadioButton
import com.thekhaeng.pushdownanim.PushDownAnim
import com.wessam.rememberme.R
import com.wessam.rememberme.base.ParentActivity
import com.wessam.rememberme.ui.main.MainActivity
import com.wessam.rememberme.ui.settings.SettingsActivity
import kotlinx.android.synthetic.main.activity_first_login.*

class LoginActivity : ParentActivity(), LoginView {

    var defaultLanguage: String = "en"
    lateinit var loginPresenter: LoginPresenter

    override fun initializeComponents() {

        PushDownAnim.setPushDownAnimTo(btn_ok)

        loginPresenter = LoginPresenterImpl(this, mSharedPreferences)

        rg_language.setOnCheckedChangeListener { _, checkedId ->
            val radio: RadioButton = findViewById(checkedId)
            defaultLanguage = when (radio) {
                rb_lan_ar -> "ar"
                else -> "en"
            }
        }

        btn_ok.setOnClickListener {
            val name = et_user_name.text.toString()
            loginPresenter.onOkButtonClicked(et_user_name, name, defaultLanguage)
        }
    }

    override fun getLayoutResource() = R.layout.activity_first_login

    override fun isFullScreen() = false

    override fun isEnabledToolbar() = false

    override fun isEnabledBack() = false

    override fun openMainActivity() {
        startActivity(MainActivity::class.java)
        finish()
    }

    override fun showRequiredFieldError() {
        et_user_name.error = resources.getString(R.string.enter_name_first)
    }

}