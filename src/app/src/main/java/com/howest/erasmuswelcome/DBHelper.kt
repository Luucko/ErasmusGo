package com.howest.erasmuswelcome;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.howest.erasmuswelcome.Screens.EventsScreen.Event
import kotlinx.coroutines.tasks.await

class DBHelper(context: Context) {

    // Firebase instances
    private val auth: FirebaseAuth = FirebaseAuth.getInstance();
    private val userDatabaseRef = Firebase.database.getReference("Users")

    private val activeUser: User? = null

    // Data class for User
    data class User(
        val id: String="",
        val name: String="",
        val password: String="",
        val country: String="",
        val email: String=""
    )

    fun getActiveUser(): User? {
        return activeUser
    }

    fun addUser(name: String, password: String, country: String, email: String) {


        // Generiere einen eindeutigen Schlüssel
        val userId = userDatabaseRef.push().key

        if (userId != null) {
            // Erstelle ein neues Benutzerobjekt mit der generierten ID
            val newUser = User(
                id = userId,
                name = name,
                password = password,
                country = country,
                email = email
            );

            // Speichere den Benutzer unter dem generierten Schlüssel
            userDatabaseRef.child(userId).setValue(newUser)
                .addOnSuccessListener {
                    println("User added successfully with ID: $userId")
                }
                .addOnFailureListener { exception ->
                    println("Error adding user: ${exception.message}")
                }
        } else {
            println("Failed to generate a unique ID.")
        }

    }

    suspend fun fetchUsers(): List<User> {

        val users = mutableListOf<User>()

        try {
            val snapshot = userDatabaseRef.get().await()
            println(snapshot.value.toString())
            for (child in snapshot.children) {
                println(child.value.toString())
                val user = child.getValue(User::class.java)
                println(user.toString())
                if (user != null) {
                    users.add(user)
                    println(user.toString())
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return users;
    }



    fun checkPasswordMatch(name: String, pwd: String,users:List<User>): Boolean {

        users.forEach { user ->
            if (user.name == name && pwd == user.password) {
                return true;
            }
        }
        return false
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
