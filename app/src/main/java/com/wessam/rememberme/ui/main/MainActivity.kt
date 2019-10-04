package com.wessam.rememberme.ui.main

import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wessam.rememberme.R
import com.wessam.rememberme.base.ParentActivity
import com.wessam.rememberme.model.SqliteHelper
import com.wessam.rememberme.ui.addperson.AddPersonActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_welcome_new_user.*

class MainActivity : ParentActivity(), MainActivityView {

    companion object {
        lateinit var dbHandler: SqliteHelper
    }

    private lateinit var mainPresenter: MainActivityPresenter

    override fun initializeComponents() {

        toolbarTitle = R.string.app_name

        dbHandler = SqliteHelper(this)

        mainPresenter = MainActivityPresenterImpl(this, mSharedPreferences)

        mainPresenter.onFirstLogin()

        viewPerson()

        fab_add_person.setOnClickListener { openAddPersonActivity() }
    }

    private fun viewPerson() {
        val personList = dbHandler.getPerson(this)
        val adapter = PersonAdapter(this, personList)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_data)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.adapter = adapter
    }

    override fun showWelcomeDialog() {
            val welcomeDialog = LayoutInflater.from(this).inflate(R.layout.dialog_welcome_new_user, null)
            val alertBuilder = AlertDialog.Builder(this).setView(welcomeDialog).show()
            alertBuilder.window?.setBackgroundDrawableResource(android.R.color.transparent)
            alertBuilder.tv_user_name.append("${resources.getString(R.string.welcome)} ${mSharedPreferences.getUserName()} !")
            alertBuilder.btn_dialog_start.setOnClickListener {
                alertBuilder.dismiss()
        }
    }

    override fun getLayoutResource() = R.layout.activity_main

    override fun isFullScreen() = false

    override fun isEnabledToolbar() = true

    override fun isEnabledBack() = false

    override fun openAddPersonActivity() {
        startActivity(AddPersonActivity::class.java)
    }

    override fun onResume() {
        viewPerson()
        super.onResume()
    }
}
