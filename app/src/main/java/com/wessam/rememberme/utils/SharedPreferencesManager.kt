package com.wessam.rememberme.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import java.util.*

class SharedPreferencesManager(context: Context) {

    private var sharedPreferences = context.getSharedPreferences(SharedPrefKey.SHARED_PREF_NAME, MODE_PRIVATE)
    private var editor = sharedPreferences.edit()

    fun setLoginStatus(status: Boolean) {
        editor.putBoolean(SharedPrefKey.LOGIN_STATUS, status).apply()
    }

    fun getLoginStatus() = sharedPreferences.getBoolean(SharedPrefKey.LOGIN_STATUS, false)

    fun setIsFirstLogin(isFirstLogin: Boolean){
        editor.putBoolean(SharedPrefKey.FIRST_LOGIN, isFirstLogin).apply()
    }

    fun getIsFirstLogin() = sharedPreferences.getBoolean(SharedPrefKey.FIRST_LOGIN, true)

    fun setAppLanguage(language: String) {
        editor.putString(SharedPrefKey.App_LANGUAGE, language).apply()
    }

    fun getAppLanguage() = sharedPreferences.getString(SharedPrefKey.App_LANGUAGE, Locale.getDefault().language) ?: "ar"

    fun setUserName(name: String){
        editor.putString(SharedPrefKey.USER_NAME, name).apply()
    }

    fun getUserName() = sharedPreferences.getString(SharedPrefKey.USER_NAME, null)

    fun clear() {
        editor.clear().apply()
    }

}