package com.wessam.rememberme.ui.splash

import com.wessam.rememberme.utils.SharedPreferencesManager

class SplashPresenter(var splashView: SplashView, var sharedPreferencesManager: SharedPreferencesManager) : ISplashPresenter {

    override fun isLogedIn() = sharedPreferencesManager.getLoginStatus()

    override fun decideNextActivity() = if (isLogedIn()) splashView.openMainActivity()
    else splashView.openLoginActivity()
}