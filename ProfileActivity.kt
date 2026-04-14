package com.example.swiftbite

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfileActivity : AppCompatActivity() {

    private lateinit var tvName: TextView
    private lateinit var tvEmail: TextView
    private lateinit var btnLogout: Button
    private lateinit var bottomNav: BottomNavigationView
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        title = "Profile"

        tvName = findViewById(R.id.tvProfileName)
        tvEmail = findViewById(R.id.tvProfileEmail)
        btnLogout = findViewById(R.id.btnLogout)
        bottomNav = findViewById(R.id.bottomNav)

        sharedPreferences = getSharedPreferences("SwiftBitePrefs", MODE_PRIVATE)

        val name = sharedPreferences.getString("name", "User")
        val email = sharedPreferences.getString("email", "No Email")

        tvName.text = "Name: $name"
        tvEmail.text = "Email: $email"

        btnLogout.setOnClickListener {
            sharedPreferences.edit().putBoolean("isLoggedIn", false).apply()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        // Bottom Navigation
        bottomNav.selectedItemId = R.id.nav_profile

        bottomNav.setOnItemSelectedListener {

            when (it.itemId) {

                R.id.nav_home -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    true
                }

                R.id.nav_cart -> {
                    startActivity(Intent(this, CartActivity::class.java))
                    true
                }

                R.id.nav_profile -> true

                else -> false
            }
        }
    }
}