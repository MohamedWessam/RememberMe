package com.wessam.rememberme.ui.settings

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.RadioButton
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.wessam.rememberme.R
import com.wessam.rememberme.base.ParentActivity
import com.wessam.rememberme.ui.main.MainActivityInteractorImpl
import com.wessam.rememberme.ui.splash.SplashActivity
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.dialog_language.*
import kotlinx.android.synthetic.main.dialog_user_name.*

class SettingsActivity : ParentActivity(), SettingsView {

    private lateinit var settingsPresenter: SettingsPresenter

    override fun initializeComponents() {

        toolbarTitle = R.string.settings

        settingsPresenter = SettingsPresenterImpl(this, mSharedPreferences)

        tv_user_name.text = mSharedPreferences.getUserName()

        tv_language.text = when (mSharedPreferences.getAppLanguage()) {
            "ar" -> resources.getString(R.string.arabic)
            else -> resources.getString(R.string.english)
        }

        btn_copy.setOnClickListener {
            settingsPresenter.onCopyButtonClicked(this, resources.getString(R.string.mail_copied))
        }

        btn_edit_name.setOnClickListener {
            settingsPresenter.onEditNameClicked()
        }

        btn_edit_language.setOnClickListener { settingsPresenter.onEditLanguageClicked() }

    }

    override fun getLayoutResource() = R.layout.activity_settings

    override fun isFullScreen() = false

    override fun isEnabledToolbar() = true

    override fun isEnabledBack() = true

    override fun showEditNameDialog() {
        val nameDialog = LayoutInflater.from(this).inflate(R.layout.dialog_user_name, null)
        val alertBuilder = AlertDialog.Builder(this).setView(nameDialog).show()
        val etName = alertBuilder.findViewById<EditText>(R.id.et_dialog_user)
        etName!!.setText(mSharedPreferences.getUserName())
        alertBuilder.btn_dialog_cancel.setOnClickListener { alertBuilder.dismiss() }
        alertBuilder.btn_dialog_ok.setOnClickListener {
            settingsPresenter.setNewName(alertBuilder, etName, this)
            tv_user_name.text = mSharedPreferences.getUserName()
        }
    }



    //show language dialog
    override fun showEditLanguageDialog() {
        val listItems = arrayOf("English", "العربية")
        val builder = AlertDialog.Builder(this@SettingsActivity)
        builder.setSingleChoiceItems(listItems, -1
        ) { dialogInterface, i ->
            val language = if (i == 0) "en" else "ar"

            settingsPresenter.editLanguage(language, "l")
            recreate()
            dialogInterface.dismiss()
            startActivity(Intent(this@SettingsActivity, SplashActivity::class.java))
        }

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







    /*
    override fun showEditLanguageDialog() {
        val languageDialog = LayoutInflater.from(this).inflate(R.layout.dialog_language, null)
        val alertBuilder = AlertDialog.Builder(this).setView(languageDialog).show()

        alertBuilder.btn_language_dialog_cancel.setOnClickListener { alertBuilder.dismiss() }

        alertBuilder.btn_language_dialog_ok.setOnClickListener {

            var language = mSharedPreferences.getAppLanguage()

            alertBuilder.rg_language_dialog.setOnCheckedChangeListener { _, checkedId ->
                val radio: RadioButton = findViewById(checkedId)
                language = when (radio) {
                    rb_dialog_ar -> "ar"
                    else -> "en"
                }
            }
            settingsPresenter.editLanguage(language, "l")
        }
    }
*/

/*
    override fun showEditLanguageDialog() {
        val listItems = arrayOf("English", "العربية")
        val builder = AlertDialog.Builder(this@SettingsActivity)
        builder.setMessage(resources.getString(R.string.change_language))
            .setIcon(R.drawable.ic_warning)
            .setCancelable(true)

        builder.setSingleChoiceItems(
            listItems, -1
        ) { dialogInterface, i ->
            val language = if (i == 0) "en" else "ar"

            settingsPresenter.editLanguage(language, "l")
            recreate()
            dialogInterface.dismiss()
            startActivity(Intent(this@SettingsActivity, SplashActivity::class.java))
        }
        builder.setNegativeButton(resources.getString(R.string.cancel))
        { dialog, _ ->
            dialog.cancel()
        }.create()
            .show()
*/

/*
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

*/


    override fun restartApp() {
        startActivity(SplashActivity::class.java)
        finish()
    }

    override fun showMessage(msg: String) {
        Snackbar.make(parent_settings_layout, msg, Snackbar.LENGTH_SHORT).show()
    }

    override fun showLanguageError() {
        longToast(resources.getString(R.string.choose_language_first))
    }

}
