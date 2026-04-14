package com.example.swiftbite

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var bottomNav: BottomNavigationView

    private val restaurantList = listOf(
        Restaurant("Pasta", "Italian", 4.2f, 150),
        Restaurant("Sandwich", "Fast Food", 4.0f, 80),
        Restaurant("Fried Rice", "Indian", 4.5f, 120),
        Restaurant("Noodles", "Chinese", 4.3f, 130),
        Restaurant("Paneer Tikka", "Indian", 4.6f, 200)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        bottomNav = findViewById(R.id.bottomNav)

        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = RestaurantListAdapter(restaurantList) { restaurant ->
            val intent = Intent(this, MenuActivity::class.java)
            intent.putExtra("name", restaurant.name)
            startActivity(intent)
        }

        // Bottom navigation click
        bottomNav.setOnItemSelectedListener {

            when (it.itemId) {

                R.id.nav_home -> {
                    true
                }

                R.id.nav_cart -> {
                    startActivity(Intent(this, CartActivity::class.java))
                    true
                }

                R.id.nav_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    true
                }

                else -> false
            }
        }
    }
}