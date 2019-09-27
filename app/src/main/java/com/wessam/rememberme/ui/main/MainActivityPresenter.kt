package com.wessam.rememberme.ui.main

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.wessam.rememberme.R
import com.wessam.rememberme.utils.SharedPreferencesManager
import kotlinx.android.synthetic.main.dialog_welcome_new_user.*

class MainActivityPresenter(var mainActivityView: MainActivityView, var sharedPreferencesManager: SharedPreferencesManager) : IMainActivityPresenter {

    override fun showWelcomeDialog(context: Context) {
        if (!sharedPreferencesManager.getIsFirstLogin()){
            val welcomeDialog = LayoutInflater.from(context).inflate(R.layout.dialog_welcome_new_user, null)
            val alertBuilder = AlertDialog.Builder(context).setView(welcomeDialog).show()
            alertBuilder.window?.setBackgroundDrawableResource(android.R.color.transparent)
            alertBuilder.btn_dialog_start.setOnClickListener { alertBuilder.dismiss() }
        }
    }
}