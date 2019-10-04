package com.wessam.rememberme.ui.main

import android.content.Context

interface MainActivityPresenter{

    fun getUserName(): String

    fun onFirstLogin()

}