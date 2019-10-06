package com.wessam.rememberme.model

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.widget.Toast
import android.database.sqlite.SQLiteOpenHelper as SQLiteOpenHelper1

class SqliteHelper(context: Context) :
    SQLiteOpenHelper1(context,
        DATABASE_NAME, null,
        DATABASE_VERSION
    ) {

    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "remember_me.DB"

        private val TABLE_NAME = "person"
        private val COLOMN_ID = "person_id"
        private val COLOMN_NAME = "person_name"
        private val COLOMN_PHONE = "person_phone"
        private val COLOMN_PERIOD_ID = "call_period"
        private val COLOMN_RELATIONSHIP_ID = "person_relationship_id"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_PERSON_TABLE = (
                "CREATE TABLE $TABLE_NAME (" +
                        "$COLOMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "$COLOMN_NAME TEXT," +
                        "$COLOMN_PHONE TEXT," +
                        "$COLOMN_RELATIONSHIP_ID INTEGER," +
                        "$COLOMN_PERIOD_ID INTEGER)"
                )
        db?.execSQL(CREATE_PERSON_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
    }


    fun getPerson(context: Context): ArrayList<Person> {

        val qry = "Select * From $TABLE_NAME"
        val db = this.readableDatabase
        val cursor = db.rawQuery(qry, null)
        val personList = ArrayList<Person>()

        if (cursor.count == 0)
            // show text says add persons
         else {
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                val person = Person()
                person.personId = cursor.getInt(cursor.getColumnIndex(COLOMN_ID))
                person.personName = cursor.getString(cursor.getColumnIndex(COLOMN_NAME))
                person.personPhone = cursor.getString(cursor.getColumnIndex(COLOMN_PHONE))
                person.relationShipId = cursor.getInt(cursor.getColumnIndex(COLOMN_RELATIONSHIP_ID))
                person.callPeriod = cursor.getInt(cursor.getColumnIndex(COLOMN_PERIOD_ID))
                personList.add(person)
                cursor.moveToNext()
            }
        }
        cursor.close()
        db.close()
        return personList
    }


    fun addPerson(context: Context, person: Person) {
        val values = ContentValues()
        values.put(COLOMN_NAME, person.personName)
        values.put(COLOMN_PHONE, person.personPhone)
        values.put(COLOMN_RELATIONSHIP_ID, person.relationShipId)
        values.put(COLOMN_PERIOD_ID, person.callPeriod)

        val db = this.writableDatabase
        try{
            db.insert(TABLE_NAME, null, values)
        }catch (ex: Exception){
            Toast.makeText(context, ex.message, Toast.LENGTH_LONG).show()
        }
        db.close()
    }

    fun updatePerson(person: Person): Int {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLOMN_ID, person.personId)
        values.put(COLOMN_NAME, person.personName)
        values.put(COLOMN_PHONE, person.personPhone)
        values.put(COLOMN_RELATIONSHIP_ID, person.relationShipId)
        values.put(COLOMN_PERIOD_ID, person.callPeriod)

        return db.update(TABLE_NAME, values, "$COLOMN_ID=?", arrayOf(person.personId.toString()))
    }

    fun deletePerson(person: Person) {
        val db = this.writableDatabase

        db.delete(TABLE_NAME, "$COLOMN_ID=?", arrayOf(person.personId.toString()))
    }

}