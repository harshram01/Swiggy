package com.example.swiftbite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

class RestaurantListAdapter(
    private val originalList: List<Restaurant>,
    private val onItemClick: (Restaurant) -> Unit
) : RecyclerView.Adapter<RestaurantListAdapter.ViewHolder>(), Filterable {

    private var filteredList: List<Restaurant> = originalList

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvRestaurantName)
        val tvCuisine: TextView = itemView.findViewById(R.id.tvCuisine)
        val tvRating: TextView = itemView.findViewById(R.id.tvRating)
        val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_restaurant, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val restaurant = filteredList[position]

        holder.tvName.text = restaurant.name
        holder.tvCuisine.text = restaurant.cuisine
        holder.tvRating.text = "Rating: ${restaurant.rating}"
        holder.tvPrice.text = "₹${restaurant.price}"

        holder.itemView.setOnClickListener {
            onItemClick(restaurant)
        }
    }

    override fun getItemCount(): Int = filteredList.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val searchText = constraint.toString().lowercase(Locale.getDefault()).trim()

                filteredList = if (searchText.isEmpty()) {
                    originalList
                } else {
                    originalList.filter {
                        it.name.lowercase(Locale.getDefault()).contains(searchText) ||
                                it.cuisine.lowercase(Locale.getDefault()).contains(searchText)
                    }
                }

                val filterResults = FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredList = results?.values as List<Restaurant>
                notifyDataSetChanged()
            }
        }
    }
}