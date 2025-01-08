package com.howest.erasmuswelcome;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    // Firebase instances
    private val auth: FirebaseAuth = FirebaseAuth.getInstance();
    private val databaseUsers: DatabaseReference = FirebaseDatabase.getInstance().getReference("users");

    private val databaseTeachers: DatabaseReference = FirebaseDatabase.getInstance().getReference("teachers");

    // Data class for User
    data class User(val id: Int, val name: String, val password: String, val country: String, val email: String)

    companion object {
        private const val DATABASE_NAME = "my_database.db";
        private const val DATABASE_VERSION = 2;
    }

    override fun onCreate(db: SQLiteDatabase?) {

        try {
            db?.execSQL("CREATE TABLE users (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, password TEXT, country TEXT, email TEXT)");
        } catch (e: SQLException) {
            e.printStackTrace();
        }



    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        try {
            db?.execSQL("DROP TABLE IF EXISTS users");
            onCreate(db);
        } catch (e: SQLException) {
            e.printStackTrace();
        }
    }

    fun addUser(name: String, password: String, country: String, email: String) {
        val db = writableDatabase;
        try {
            val values = ContentValues().apply {
                put("name", name);
                put("password", password);
                put("country", country);
                put("email", email);
            };
            db.insert("users", null, values);
        } catch (e: SQLException) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    @SuppressLint("Recycle")
    fun checkPasswordMatch(name: String, pwd: String): Boolean {
        val db = readableDatabase;
        var cursor: Cursor? = null;
        return try {
            val projection = arrayOf("password");
            val selection = "name = ?";
            val selectionArgs = arrayOf(name);
            cursor = db.query("users", projection, selection, selectionArgs, null, null, null);

            if (cursor.moveToFirst()) {
                val passwordFromDb = cursor.getString(cursor.getColumnIndexOrThrow("password"));
                pwd == passwordFromDb;
            } else {
                false;
            }
        } catch (e: Exception) {
            e.printStackTrace();
            false;
        } finally {
            cursor?.close();
            db.close();
        }
    }

    fun getUserByUsername(username: String): User? {
        val db = readableDatabase;
        var cursor: Cursor? = null;
        return try {
            val projection = arrayOf("id", "name", "password", "country", "email");
            val selection = "name = ?";
            val selectionArgs = arrayOf(username);
            cursor = db.query("users", projection, selection, selectionArgs, null, null, null);

            if (cursor.moveToFirst()) {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                val name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                val password = cursor.getString(cursor.getColumnIndexOrThrow("password"));
                val country = cursor.getString(cursor.getColumnIndexOrThrow("country"));
                val email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
                User(id, name, password, country, email);
            } else {
                null;
            }
        } catch (e: Exception) {
            e.printStackTrace();
            null;
        } finally {
            cursor?.close();
            db.close();
        }
    }

    fun addCurrentUserToFirebase(name: String) {
        val db = readableDatabase;
        var cursor: Cursor? = null;
        try {
            val projection = arrayOf("id", "name", "password", "country", "email");
            val selection = "name = ?";
            val selectionArgs = arrayOf(name);
            cursor = db.query("users", projection, selection, selectionArgs, null, null, null);

            if (cursor.moveToFirst()) {
                val username = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                val dbPassword = cursor.getString(cursor.getColumnIndexOrThrow("password"));
                val country = cursor.getString(cursor.getColumnIndexOrThrow("country"));
                val email = cursor.getString(cursor.getColumnIndexOrThrow("email"));

                val database = FirebaseDatabase.getInstance();
                val ref: DatabaseReference = database.getReference("users/$username");

                val currentUser = hashMapOf(
                    "username" to username,
                    "password" to dbPassword,
                    "country" to country
                );
                ref.setValue(currentUser).addOnFailureListener { exception ->
                    exception.printStackTrace();
                };
            }
        } catch (e: Exception) {
            e.printStackTrace();
        } finally {
            cursor?.close();
            db.close();
        }
    }

    fun authenticateUser(email: String, password: String, onComplete: (Boolean, String) -> Unit) {
        try {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onComplete(true, "Authentication successful!");
                } else {
                    onComplete(false, task.exception?.message ?: "Authentication failed.");
                }
            };
        } catch (e: Exception) {
            e.printStackTrace();
            onComplete(false, "An error occurred during authentication.");
        }
    }
}
