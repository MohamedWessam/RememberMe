package com.wessam.rememberme.ui.main

import android.content.Context
import com.wessam.rememberme.model.Person

interface MainActivityPresenter{

    fun getUserName(): String

    fun checkFirstLogin()

    fun getPerson(): ArrayList<Person>

}