package com.example.bnicasefirst.helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.bnicasefirst.model.TransactionModel
import org.jetbrains.anko.db.*

class DatabaseHelper (context: Context) : ManagedSQLiteOpenHelper(context, "database_transaction.db", null, 1) {

    companion object {
        private var instance: DatabaseHelper? = null

        fun getInstance(context: Context): DatabaseHelper {
            if (instance == null) {
                instance = DatabaseHelper(context)
            }
            return instance as DatabaseHelper
        }

    }

    override fun onCreate(db: SQLiteDatabase?) {
        //Buat tabel pada database
        db?.createTable(
            TransactionModel.TABLE_TRANSCATION,
            true,
            TransactionModel.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            TransactionModel.TRANSACTION_ID to TEXT,
            TransactionModel.MERCHANT_NAME to TEXT,
            TransactionModel.NOMINAL to TEXT,
            TransactionModel.BANK to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(TransactionModel.TABLE_TRANSCATION, true)
    }
}

val Context.database: DatabaseHelper
    get() = DatabaseHelper.getInstance(applicationContext)