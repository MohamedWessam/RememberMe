package com.wessam.rememberme.base

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.wessam.rememberme.R
import com.wessam.rememberme.ui.settings.SettingsActivity
import com.wessam.rememberme.utils.SharedPreferencesManager
import kotlinx.android.synthetic.main.app_bar.*
import java.util.*

abstract class ParentActivity : AppCompatActivity() {

    private lateinit var mContext: Context

    private lateinit var mActivity: AppCompatActivity

    lateinit var mSharedPreferences: SharedPreferencesManager

    protected var toolbarTitle: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        mContext = this
        mActivity = this

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        if (isFullScreen()) {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }

        setContentView(getLayoutResource())

        mSharedPreferences = SharedPreferencesManager(mContext)

        initializeComponents()

        if (isEnabledToolbar()) configureToolbar()

    }

    /**
     * configure toolbar
     */
    private fun configureToolbar() {
        setSupportActionBar(toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(isEnabledBack())

        activities_tv_title.text = resources.getString(toolbarTitle!!)

        if (isSettingsMenuEnabled()){
            toolbar.inflateMenu(R.menu.menu)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item!!.itemId){
            R.id.menu_settings -> startActivity(SettingsActivity::class.java)
        }
        return super.onOptionsItemSelected(item)
    }

    protected fun startActivity(cls: Class<*>){
        startActivity(Intent(mContext, cls))
    }

    protected fun longToast(msg: String){
        Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show()
    }

    /**
     * initialize components
     */
    protected abstract fun initializeComponents()


    /**
     * get layout resource
     */
    protected abstract fun getLayoutResource(): Int


    /**
     * return true if full screen is enabled
     */
    protected abstract fun isFullScreen(): Boolean

    /**
     * return true if toolbar is enabled
     */
    protected abstract fun isEnabledToolbar(): Boolean


    /**
     * return true if toolbar back button is enabled
     */
    protected abstract fun isEnabledBack(): Boolean

    /**
     * return true if setting button is enabled
     */
    protected abstract fun isSettingsMenuEnabled(): Boolean

}