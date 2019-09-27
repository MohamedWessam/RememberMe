package com.wessam.rememberme.ui.main

import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wessam.rememberme.R
import com.wessam.rememberme.base.ParentActivity
import com.wessam.rememberme.model.PersonAdapter
import com.wessam.rememberme.model.SqliteHelper
import com.wessam.rememberme.ui.addperson.AddPersonActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : ParentActivity(), MainActivityView {

    companion object {
        lateinit var dbHandler: SqliteHelper
    }

    override fun initializeComponents() {

        toolbarTitle = R.string.app_name

        dbHandler = SqliteHelper(this)

        viewPerson()

        val iMainActivityPresenter = MainActivityPresenter(this, mSharedPreferences)
        iMainActivityPresenter.showWelcomeDialog(this)

        fsb_add_person.setOnClickListener { openAddPersonActivity() }
    }

    private fun viewPerson() {
        val personList = dbHandler.getPerson(this)
        val adapter = PersonAdapter(this, personList)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_data)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.adapter = adapter
    }

    override fun getLayoutResource() = R.layout.activity_main

    override fun isFullScreen() = false

    override fun isEnabledToolbar() = true

    override fun isEnabledBack() = false

    override fun isOrientationEnabled() = false

    override fun openAddPersonActivity() {
        startActivity(AddPersonActivity::class.java)
    }


    override fun onResume() {
        viewPerson()
        super.onResume()
    }
}
