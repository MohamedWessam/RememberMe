package com.wessam.rememberme.ui.login

import com.thekhaeng.pushdownanim.PushDown
import com.thekhaeng.pushdownanim.PushDownAnim
import com.wessam.rememberme.R
import com.wessam.rememberme.base.ParentActivity
import com.wessam.rememberme.ui.main.MainActivity
import com.wessam.rememberme.utils.SharedPreferencesManager
import kotlinx.android.synthetic.main.activity_first_login.*

class FirstLoginActivity : ParentActivity(), LoginView {

    override fun initializeComponents() {

        PushDownAnim.setPushDownAnimTo(btn_ok)

        val iLoginPresenter: ILoginPresenter = LoginPresenter(this, mSharedPreferences)

        val name = et_user_name.text.toString()
        val languageId = rg_language.checkedRadioButtonId

        val language = if (languageId == 0) "ar"
        else "en"

        iLoginPresenter.saveUserData(name, language)

        btn_ok.setOnClickListener { openHomeActivity() }
    }

    override fun getLayoutResource() = R.layout.activity_first_login

    override fun isFullScreen() = false

    override fun isEnabledToolbar() = false

    override fun isEnabledBack() = false

    override fun isOrientationEnabled() = false

    override fun openHomeActivity() {
        startActivity(MainActivity::class.java)
        finish()
    }

}
