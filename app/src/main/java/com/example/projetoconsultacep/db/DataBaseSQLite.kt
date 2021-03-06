package com.example.projetoconsultacep.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.projetoconsultacep.model.User
import java.sql.SQLException

class DataBaseSQLite(
    context: Context?
) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        private val DB_NAME = "user"
        private val DB_VERSION = 2

        private val TABLE_NAME_USER = "user"
        private val COLUMN_NAME = "name"
        private val COLUMN_PASSWORD = "pass"

    }

    private val SQL_CREATE_TABLE_USER = "CREATE TABLE $TABLE_NAME_USER(" +
            "$COLUMN_NAME TEXT NOT NULL," +
            "$COLUMN_PASSWORD TEXT NOT NULL)"

    private val SQL_DROP_TABLE_USER = "DROP TABLE $TABLE_NAME_USER"


    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_TABLE_USER)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion < newVersion) {
            db?.execSQL(SQL_DROP_TABLE_USER)
        }
        db?.execSQL(SQL_CREATE_TABLE_USER)
    }


    fun searchUser(user: String): List<User> {
        val db = readableDatabase
        val list = mutableListOf<User>()

        var where: String? = "$COLUMN_NAME = ?"
        var args: Array<String> = arrayOf("$user")

        try {
            val cursor = db.query(TABLE_NAME_USER, null, where, args, null, null, null, null)

            if (cursor == null) {
                db.close()
                return mutableListOf()
            }

            while (cursor.moveToNext()) {
                val user = User(
                    cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD))
                )
                list.add(user)
            }

        } catch (ex: SQLException) {
            ex.printStackTrace()
        } finally {
            db.close()
        }
        return list
    }

    fun insertUser(user: User) {
        val db = writableDatabase

        var contentValue = ContentValues()
        contentValue.put(COLUMN_NAME, user.name)
        contentValue.put(COLUMN_PASSWORD, user.password)

        try {
            db.insert(TABLE_NAME_USER, null, contentValue)
        } catch (ex: SQLException) {
            ex.printStackTrace()
        } finally {
            db.close()
        }
    }

}