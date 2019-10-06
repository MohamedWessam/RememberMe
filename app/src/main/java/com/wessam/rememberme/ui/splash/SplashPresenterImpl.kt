package com.wessam.rememberme.ui.splash

import com.wessam.rememberme.utils.SharedPreferencesManager

class SplashPresenterImpl(var view: SplashView, var sharedPreferences: SharedPreferencesManager) : SplashPresenter {

    override fun decideNextActivity() {
        if (sharedPreferences.getLoginStatus()) view.openMainActivity()
        else view.openLoginActivity()
    }

}