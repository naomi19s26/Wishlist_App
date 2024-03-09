package com.example.wishlist_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WishlistAdapter(private val wishlists: MutableList<Wishlist>) : RecyclerView.Adapter<WishlistAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // TODO: Create member variables for any view that will be set
        // as you render a row.
        val nameTextView : TextView
        val urlTextView : TextView
        val priceTextView : TextView

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each sub-view
        init {
            // TODO: Store each of the layout's views into
            // the public final member variables created above
            nameTextView = itemView.findViewById(R.id.item_name)
            urlTextView = itemView.findViewById(R.id.item_url)
            priceTextView = itemView.findViewById(R.id.item_price)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.wishlist_main, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get the data model based on position
        val wishlist = wishlists[position]
        // Set item views based on views and data model
        holder.nameTextView.text = wishlist.name
        holder.urlTextView.text = wishlist.url
        holder.priceTextView.text = wishlist.price.toString()

        holder.itemView.setOnLongClickListener {
            // Remove the item from the dataset
            wishlists.removeAt(position)
            // Notify adapter of the item removal
            notifyItemRemoved(position)
            true // Return true to indicate the event is consumed
        }


    }

    override fun getItemCount(): Int {
        return wishlists.size
    }

}