package com.wessam.rememberme.ui.main

import android.content.Context
import com.wessam.rememberme.model.Person

interface MainActivityInteractor {

    fun getPerson(): ArrayList<Person>

}