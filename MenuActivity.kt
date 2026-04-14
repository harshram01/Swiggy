package com.example.swiftbite

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MenuActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var tvCartCount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val restaurantName = intent.getStringExtra("name") ?: "Menu"
        title = restaurantName

        recyclerView = findViewById(R.id.recyclerViewMenu)
        tvCartCount = findViewById(R.id.tvCartCount)

        val foodList = listOf(
            CartItem("Pasta", 150, 1, R.drawable.pasta),
            CartItem("Sandwich", 80, 1, R.drawable.sandwich),
            CartItem("Fried Rice", 120, 1, R.drawable.fried_rice),
            CartItem("Noodles", 130, 1, R.drawable.noodles),
            CartItem("Paneer Tikka", 200, 1, R.drawable.paneer)
        )

        tvCartCount.text = "Go to Cart: ${CartManager.cartItems.size}"

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RestaurantAdapter(foodList) { selectedItem ->
            CartManager.addToCart(selectedItem)
            tvCartCount.text = "Go to Cart: ${CartManager.cartItems.size}"
        }

        tvCartCount.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        tvCartCount.text = "Go to Cart: ${CartManager.cartItems.size}"
    }
}