package com.example.swiftbite

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class CartActivity : AppCompatActivity() {

    private lateinit var recyclerViewCart: RecyclerView
    private lateinit var tvTotalPrice: TextView
    private lateinit var btnCheckout: Button
    private lateinit var cartAdapter: CartAdapter
    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        title = "My Cart"

        recyclerViewCart = findViewById(R.id.recyclerViewCart)
        tvTotalPrice = findViewById(R.id.tvTotalPrice)
        btnCheckout = findViewById(R.id.btnCheckout)
        bottomNav = findViewById(R.id.bottomNav)

        recyclerViewCart.layoutManager = LinearLayoutManager(this)

        cartAdapter = CartAdapter(CartManager.cartItems) {
            updateTotalPrice()
        }

        recyclerViewCart.adapter = cartAdapter
        updateTotalPrice()

        btnCheckout.setOnClickListener {
            startActivity(Intent(this, PaymentActivity::class.java))
        }

        // Bottom Navigation
        bottomNav.selectedItemId = R.id.nav_cart

        bottomNav.setOnItemSelectedListener {

            when (it.itemId) {

                R.id.nav_home -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    true
                }

                R.id.nav_cart -> true

                R.id.nav_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    true
                }

                else -> false
            }
        }
    }

    private fun updateTotalPrice() {
        tvTotalPrice.text = "Total Price: ₹${CartManager.getTotalPrice()}"
    }
}