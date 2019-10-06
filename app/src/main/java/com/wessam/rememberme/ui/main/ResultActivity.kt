package com.wessam.rememberme.ui.main

import android.app.Activity
import android.content.DialogInterface
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import androidx.appcompat.app.AppCompatActivity
import com.wessam.rememberme.R
import java.util.*

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        if (intent.getBooleanExtra("notification", false)) { //Just for confirmation
//            txtTitleView.text = intent.getStringExtra("title")
//            txtMsgView.text = intent.getStringExtra("message")

        }
    }

/*
    //show language dialog
    private fun showLanguageDialog() {
        val listItems = arrayOf("English", "العربية")
        val builder = AlertDialog.Builder(this@Main2Activity)
        builder.setSingleChoiceItems(listItems, -1,
            DialogInterface.OnClickListener { dialogInterface, i ->
                if (i == 0) {
                    setLocal("en")
                } else if (i == 1) {
                    setLocal("ar")
                }
                recreate()
                dialogInterface.dismiss()
                startActivity(Intent(this@Main2Activity, SplashActivity::class.java))
            })

        val titleText = resources.getString(R.string.language)
        // Initialize a new foreground color span instance
        val foregroundColorSpan = ForegroundColorSpan(Color.GRAY)
        // Initialize a new spannable string builder instance
        val ssBuilder = SpannableStringBuilder(titleText)
        // Apply the text color span
        ssBuilder.setSpan(
            foregroundColorSpan, 0, titleText.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        builder.setTitle(ssBuilder)
        builder.setNegativeButton(resources.getString(R.string.cancel), null)

        val dialog = builder.create()
        dialog.show()
    }

    //save chosen language in SharedPreferences
    fun setLocal(lang: String) {
        val locale = Locale(lang)
        Locale.setDefault(locale)
        val configuration = Configuration()
        configuration.locale = locale
        baseContext.resources.updateConfiguration(
            configuration,
            baseContext.resources.displayMetrics
        )
        val editor = getSharedPreferences("settings", MODE_PRIVATE).edit()
        editor.putString("My_Language", lang)
        editor.apply()
    }
*/




}