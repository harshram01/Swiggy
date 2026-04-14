package com.example.swiftbite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CartAdapter(
    private val cartList: MutableList<CartItem>,
    private val onCartUpdated: () -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foodImage: ImageView = itemView.findViewById(R.id.cartFoodImage)
        val foodName: TextView = itemView.findViewById(R.id.cartFoodName)
        val foodPrice: TextView = itemView.findViewById(R.id.cartFoodPrice)
        val foodQuantity: TextView = itemView.findViewById(R.id.cartFoodQuantity)
        val btnPlus: TextView = itemView.findViewById(R.id.btnPlus)
        val btnMinus: TextView = itemView.findViewById(R.id.btnMinus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = cartList[position]

        holder.foodImage.setImageResource(item.image)
        holder.foodName.text = item.name
        holder.foodPrice.text = "₹${item.price}"
        holder.foodQuantity.text = "Qty: ${item.quantity}"

        holder.btnPlus.setOnClickListener {
            CartManager.increaseQuantity(item)
            notifyItemChanged(position)
            onCartUpdated()
        }

        holder.btnMinus.setOnClickListener {
            val oldSize = cartList.size
            CartManager.decreaseQuantity(item)
            val newSize = cartList.size

            if (newSize < oldSize) {
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, cartList.size)
            } else {
                notifyItemChanged(position)
            }
            onCartUpdated()
        }
    }

    override fun getItemCount(): Int = cartList.size
}