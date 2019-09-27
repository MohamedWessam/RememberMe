package com.wessam.rememberme.ui.addperson

import com.thekhaeng.pushdownanim.PushDownAnim
import com.wessam.rememberme.R
import com.wessam.rememberme.base.ParentActivity
import kotlinx.android.synthetic.main.activity_add_person.*

import com.wessam.rememberme.model.*
import com.wessam.rememberme.ui.main.MainActivity

class AddPersonActivity : ParentActivity(), AddPersonView {

    override fun initializeComponents() {
        toolbarTitle = R.string.add_person

        PushDownAnim.setPushDownAnimTo(btn_add, btn_cancel)

        btn_cancel.setOnClickListener { backToMain() }

        btn_add.setOnClickListener {
            when {
                et_name.text.isEmpty() -> {
                    et_name.error = resources.getString(R.string.name_required)
                    et_name.requestFocus()
                }
                et_mobile.text.isEmpty() -> {
                    et_mobile.error = resources.getString(R.string.phone_required)
                    et_mobile.requestFocus()
                }
                else -> {
                    val name = et_name.text.toString()
                    val phone = et_mobile.text.trim().toString()
                    val relationShipId = spinner_relationship.selectedItemId.toInt()
                    val callPeriodId = spinner_time.selectedItemId.toInt()

                    val person = Person(personName = name, personPhone = phone, relationShipId = relationShipId, callPeriodId = callPeriodId)

                    MainActivity.dbHandler.addPerson(this, person)
                    this.finish()
                }
            }
        }

    }




    override fun getLayoutResource() = R.layout.activity_add_person

    override fun isFullScreen() = false

    override fun isEnabledToolbar() = true

    override fun isEnabledBack() = true

    override fun isOrientationEnabled() = false

    override fun backToMain() {
        this.finish()
    }

}
