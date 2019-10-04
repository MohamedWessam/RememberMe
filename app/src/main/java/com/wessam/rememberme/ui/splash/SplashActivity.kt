package com.wessam.rememberme.ui.splash

import com.wessam.rememberme.R
import com.wessam.rememberme.base.ParentActivity
import com.wessam.rememberme.ui.login.LoginActivity
import com.wessam.rememberme.ui.main.MainActivity
import android.os.Handler
import com.wessam.rememberme.ui.settings.SettingsActivity

class SplashActivity : ParentActivity(), SplashView {

    override fun initializeComponents() {

        val splashPresenter: SplashPresenter = SplashPresenterImpl(this, mSharedPreferences)

        Handler().postDelayed({
            splashPresenter.decideNextActivity()
        },4000)

    }

    override fun getLayoutResource() = R.layout.activity_splash

    override fun isFullScreen() = true

    override fun isEnabledToolbar() = false

    override fun isEnabledBack() = false

    override fun openMainActivity() {
        startActivity(MainActivity::class.java)
        finish()
    }

    override fun openLoginActivity() {
        startActivity(LoginActivity::class.java)
        finish()
    }
}
