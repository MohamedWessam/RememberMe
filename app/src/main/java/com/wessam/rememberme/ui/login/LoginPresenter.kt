package com.wessam.rememberme.ui.login

import android.widget.TextView

interface LoginPresenter {

    fun onOkButtonClicked(textView: TextView, name: String, language: String)

}