package com.wessam.rememberme.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.wessam.rememberme.R
import com.wessam.rememberme.model.Person
import kotlinx.android.synthetic.main.dialog_update_person.*
import kotlinx.android.synthetic.main.dialog_update_person.view.*
import kotlinx.android.synthetic.main.recycler_main_activity.view.*

class PersonAdapter(var context: Context, private val persons: ArrayList<Person>) :
    RecyclerView.Adapter<PersonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_main_activity, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return persons.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val person: Person = persons[position]

        holder.tvName.text = person.personName

        holder.ivGroupType.setImageResource(imageResource(person.relationShipId!!))

        holder.btnDelete.setOnClickListener {

            val alertDialog = AlertDialog.Builder(context)

            alertDialog.setMessage("${context.resources.getString(R.string.delete_message)} ${person.personName} ?")
                .setCancelable(true)
                .setTitle(context.resources.getString(R.string.delete_title))
                .setIcon(R.drawable.ic_warning)
                .setPositiveButton(context.resources.getString(R.string.ok)) { _, _ ->
                    MainActivityInteractorImpl.dbHandler.deletePerson(person)
                    persons.removeAt(position)
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position, persons.size)
                }
                .setNegativeButton(context.resources.getString(R.string.cancel)) { dialog, _ ->
                    dialog.cancel()
                }.create()
                .show()
        }

        holder.btnEdit.setOnClickListener {
            val updateDialog = LayoutInflater.from(context).inflate(R.layout.dialog_update_person, null)

            val etName: TextView = updateDialog.findViewById(R.id.et_update_name)
            val etMobile: TextView = updateDialog.findViewById(R.id.et_update_mobile)
            val sRelationship: Spinner = updateDialog.findViewById(R.id.spinner_update_relationship)
            val sTime: Spinner = updateDialog.findViewById(R.id.spinner_update_time)

            etName.text = person.personName
            etMobile.text = person.personPhone
            sRelationship.setSelection(person.relationShipId!!)
            sTime.setSelection(person.callPeriod!!)

            val alertBuilder = AlertDialog.Builder(context).setView(updateDialog).show()
            alertBuilder.btn_cancel_update.setOnClickListener { alertBuilder.dismiss() }
            alertBuilder.btn_update.setOnClickListener {
                MainActivityInteractorImpl.dbHandler.updatePerson(person)
                persons[position].personName = updateDialog.et_update_name.text.toString()
                persons[position].personPhone = updateDialog.et_update_mobile.text.toString()
                persons[position].relationShipId = updateDialog.spinner_update_relationship.selectedItemId.toInt()
                persons[position].callPeriod = updateDialog.spinner_update_time.selectedItemId.toInt()
                alertBuilder.dismiss()
                notifyDataSetChanged()
            }

        }
    }

    private fun imageResource(relationshipId: Int): Int {
        return when (relationshipId) {
            1 -> R.drawable.ic_family
            2 -> R.drawable.ic_friends
            3 -> R.drawable.ic_crash
            else -> R.drawable.ic_other
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName = itemView.tv_name
        val ivGroupType = itemView.iv_group_type
        val btnEdit = itemView.btn_edit
        val btnDelete = itemView.btn_delete
    }
}