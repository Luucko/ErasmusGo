package com.howest.erasmuswelcome

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DBHelper(context: Context) {

    // Firebase instances
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val database: DatabaseReference = FirebaseDatabase.getInstance().getReference("users")

    // Data class for User
    data class User(val id: String, val name: String, val password: String)

    /**
     * Add a new user to Firebase Authentication and Database
     * @param email: User email (used as Firebase authentication ID)
     * @param password: User password
     * @param name: User name
     * @param onComplete: Callback for success or failure
     */
    fun addUser(email: String, password: String, name: String, onComplete: (Boolean, String) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val userId = auth.currentUser?.uid ?: return@addOnCompleteListener
                val user = User(userId, name, password)
                database.child(userId).setValue(user).addOnCompleteListener { dbTask ->
                    if (dbTask.isSuccessful) {
                        onComplete(true, "User added successfully!")
                    } else {
                        onComplete(false, dbTask.exception?.message ?: "Failed to save user in database.")
                    }
                }
            } else {
                onComplete(false, task.exception?.message ?: "Failed to register user.")
            }
        }
    }

    /**
     * Authenticate a user with email and password
     * @param email: User email
     * @param password: User password
     * @param onComplete: Callback with result (Boolean for success, String for message)
     */
    fun authenticateUser(email: String, password: String, onComplete: (Boolean, String) -> Unit) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                onComplete(true, "Authentication successful!")
            } else {
                onComplete(false, task.exception?.message ?: "Authentication failed.")
            }
        }
    }
}
