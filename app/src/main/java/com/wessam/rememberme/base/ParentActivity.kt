package com.wessam.rememberme.base

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.wessam.rememberme.utils.SharedPreferencesManager
import kotlinx.android.synthetic.main.app_bar.*

abstract class ParentActivity : AppCompatActivity() {

    private lateinit var mContext: Context

    private lateinit var mActivity: AppCompatActivity

    lateinit var mSharedPreferences: SharedPreferencesManager

    protected var toolbarTitle: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mContext = this
        mActivity = this

        if (isFullScreen()) {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }

        setContentView(getLayoutResource())

        mSharedPreferences = SharedPreferencesManager(mContext)

        initializeComponents()

        requestedOrientation = if (isOrientationEnabled()) ActivityInfo.SCREEN_ORIENTATION_SENSOR
        else ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        if (isEnabledToolbar()) configureToolbar()

    }

    /**
     * configure toolbar
     */
    private fun configureToolbar() {
        setSupportActionBar(toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(isEnabledBack())

        activities_tv_title.text = resources.getString(toolbarTitle!!)
    }

    protected fun startActivity(cls: Class<*>){
        startActivity(Intent(mContext, cls))
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
     * return true if toolbar is enabled
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
     * return true if orientation is enabled
     * if false activity orientation will be portrait only
     */
    protected abstract fun isOrientationEnabled(): Boolean

}