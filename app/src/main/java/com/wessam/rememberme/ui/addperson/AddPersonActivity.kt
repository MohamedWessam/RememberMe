package com.wessam.rememberme.ui.addperson

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.net.Uri
import android.provider.ContactsContract
import android.view.View
import com.thekhaeng.pushdownanim.PushDownAnim
import com.wessam.rememberme.R
import com.wessam.rememberme.base.ParentActivity
import com.wessam.rememberme.receiver.NotificationReceiver
import kotlinx.android.synthetic.main.activity_add_person.*
import java.util.*

class AddPersonActivity : ParentActivity(), AddPersonView, View.OnClickListener {

    lateinit var presenter: PersonPresenter
    lateinit var uriContact: Uri
    private val REQUEST_CODE_PICK_CONTACTS = 1

    override fun initializeComponents() {
        toolbarTitle = R.string.add_person

        PushDownAnim.setPushDownAnimTo(btn_add, btn_cancel)

        presenter = PersonPresenterImpl(this, mSharedPreferences, PersonInteractorImpl(this))

        btn_cancel.setOnClickListener(this)
        btn_add.setOnClickListener(this)
        btn_open_contacts.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_cancel -> presenter.finishPersonActivity()
            R.id.btn_add -> createPerson()
            R.id.btn_open_contacts -> openContacts()
        }
    }

    override fun openContacts() {
        startActivityForResult(
            Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI),
            REQUEST_CODE_PICK_CONTACTS
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_PICK_CONTACTS && resultCode == Activity.RESULT_OK) {
            uriContact = data!!.data!!
            presenter.uriContact(uriContact)

            et_name.setText(presenter.contactName())
            et_mobile.setText(presenter.contactNumber())
        }
    }

    override fun showNameError() {
        et_name.error = resources.getString(R.string.name_required)
        et_name.requestFocus()
    }

    override fun showMobileNumberError() {
        et_mobile.error = resources.getString(R.string.phone_required)
        et_mobile.requestFocus()
    }

    override fun finishActivity() {
        finish()
    }

    private fun createPerson() {
        presenter.createPerson(
            personName = et_name.text.toString(),
            personPhone = et_mobile.text.trim().toString(),
            relationShipId = spinner_relationship.selectedItemId.toInt(),
            callPeriod = spinner_time.selectedItemId.toInt()
        )

        prepareAlarm()
    }

    override fun getLayoutResource() = R.layout.activity_add_person

    override fun isFullScreen() = false

    override fun isEnabledToolbar() = true

    override fun isEnabledBack() = true

    override fun isSettingsMenuEnabled() = true


    fun callPeroid(): Long {
        return when (spinner_time.selectedItemId.toInt()) {
            1 -> 1 *(24*60*60*1000)
            2 -> 7 *(24*60*60*1000)
            3 -> 30 *(24*60*60*1000)
            else -> 0
        }
    }

    private fun prepareAlarm() {
        var calendar = Calendar.getInstance()

       // calendar.add(Calendar.DATE, 30)

       // calendar.set(Calendar.HOUR_OF_DAY, 20)
       calendar.set(Calendar.MINUTE, 1)

        val intent = Intent(this, NotificationReceiver::class.java)
        intent.action = "com.wessam.rememberme.ALARM"
        intent.putExtra("NAME", et_name.text.toString())
        intent.putExtra("PHONE", et_mobile.text.toString())

        val pendingIntent = PendingIntent.getBroadcast(
            applicationContext,
            100,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            callPeroid(),
            pendingIntent
        )

        sendBroadcast(intent)
    }


}
