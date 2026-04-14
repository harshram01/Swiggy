package com.example.swiftbite

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PaymentActivity : AppCompatActivity() {

    private lateinit var tvPayTotal: TextView
    private lateinit var rbCash: RadioButton
    private lateinit var rbUpi: RadioButton
    private lateinit var rbCard: RadioButton
    private lateinit var btnPayNow: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        title = "Payment"

        tvPayTotal = findViewById(R.id.tvPayTotal)
        rbCash = findViewById(R.id.rbCash)
        rbUpi = findViewById(R.id.rbUpi)
        rbCard = findViewById(R.id.rbCard)
        btnPayNow = findViewById(R.id.btnPayNow)

        tvPayTotal.text = "Amount to Pay: ₹${CartManager.getTotalPrice()}"

        btnPayNow.setOnClickListener {
            val paymentMethod = when {
                rbCash.isChecked -> "Cash on Delivery"
                rbUpi.isChecked -> "UPI"
                rbCard.isChecked -> "Card"
                else -> ""
            }

            if (paymentMethod.isEmpty()) {
                Toast.makeText(this, "Please select a payment method", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Payment successful via $paymentMethod", Toast.LENGTH_LONG).show()
                CartManager.cartItems.clear()

                val intent = Intent(this, OrderSuccessActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}