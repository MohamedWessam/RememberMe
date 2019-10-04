package com.wessam.rememberme.ui.splash

import com.wessam.rememberme.utils.SharedPreferencesManager

class SplashPresenterImpl(var splashView: SplashView, var sharedPreferencesManager: SharedPreferencesManager) : SplashPresenter {

    override fun decideNextActivity() {
        if (sharedPreferencesManager.getLoginStatus()) splashView.openMainActivity()
        else splashView.openLoginActivity()
    }

}