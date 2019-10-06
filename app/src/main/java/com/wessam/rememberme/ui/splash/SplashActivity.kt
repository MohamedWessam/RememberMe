package com.wessam.rememberme.ui.splash

import android.app.Activity
import com.wessam.rememberme.R
import com.wessam.rememberme.base.ParentActivity
import com.wessam.rememberme.ui.login.LoginActivity
import com.wessam.rememberme.ui.main.MainActivity
import android.os.Handler
import com.wessam.rememberme.ui.settings.SettingsActivity
import java.util.*

class SplashActivity : ParentActivity(), SplashView {

    override fun initializeComponents() {

        val splashPresenter: SplashPresenter = SplashPresenterImpl(this, mSharedPreferences)

        loadLanguage()

        Handler().postDelayed({
            splashPresenter.decideNextActivity()
        },4000)

    }


    fun loadLanguage() {
        val language = mSharedPreferences.getAppLanguage()

        val locale = Locale(language)
        Locale.setDefault(locale)
        val config = baseContext.resources.configuration
        config.locale = locale
        baseContext.resources.updateConfiguration(
            config,
            baseContext.resources.displayMetrics
        )

        //set layout direction
        val configuration = resources.configuration
        configuration.setLayoutDirection(Locale(language))
        resources.updateConfiguration(configuration, resources.displayMetrics)
    }


    override fun onResume() {
        super.onResume()
        loadLanguage()
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
