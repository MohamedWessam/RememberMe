package com.wessam.rememberme.ui.main

import android.content.Context
import com.wessam.rememberme.model.Person
import com.wessam.rememberme.model.SqliteHelper

class MainActivityInteractorImpl(private val context: Context) : MainActivityInteractor{

    companion object {
        lateinit var dbHandler: SqliteHelper
    }

    override fun getPerson(): ArrayList<Person> {
        dbHandler = SqliteHelper(context)
        return dbHandler.getPerson(context)
    }



}