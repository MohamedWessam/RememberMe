package com.wessam.rememberme.ui.splash

import android.content.Intent
import com.wessam.rememberme.R
import com.wessam.rememberme.base.ParentActivity
import com.wessam.rememberme.ui.login.FirstLoginActivity
import com.wessam.rememberme.ui.main.MainActivity
import com.wessam.rememberme.utils.SharedPreferencesManager
import android.os.Handler

class SplashActivity : ParentActivity(), SplashView {

    override fun initializeComponents() {

        val splashPresenter: ISplashPresenter = SplashPresenter(this, mSharedPreferences)

        Handler().postDelayed({
            splashPresenter.decideNextActivity()
        },4000)

    }

    override fun getLayoutResource() = R.layout.activity_splash

    override fun isFullScreen() = true

    override fun isEnabledToolbar() = false

    override fun isEnabledBack() = false

    override fun isOrientationEnabled() = false

    override fun openMainActivity() {
        startActivity(MainActivity::class.java)
        finish()
    }

    override fun openLoginActivity() {
        startActivity(FirstLoginActivity::class.java)
        finish()
    }
}
