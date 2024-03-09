package com.example.wishlist_app

import android.graphics.Typeface
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.res.ResourcesCompat


class MainActivity : AppCompatActivity() {

    private lateinit var items: MutableList<Wishlist>
    private lateinit var nameEditText: EditText
    private lateinit var urlEditText: EditText
    private lateinit var priceEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val customFont: Typeface? = ResourcesCompat.getFont(this, R.font.droid_serif_italic)
        supportActionBar?.title = getString(R.string.app_name)

        val titleTextView = toolbar.getChildAt(0) as? TextView
        titleTextView?.typeface = customFont


        // Initialize EditText fields
        nameEditText = findViewById(R.id.input_name)
        urlEditText = findViewById(R.id.input_url)
        priceEditText = findViewById(R.id.input_price)

        // Initialize RecyclerView
        val wishlistRv: RecyclerView = findViewById(R.id.wishlistRv)
        wishlistRv.layoutManager = LinearLayoutManager(this)

        // Initialize button and set click listener
        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            val name = nameEditText.text.toString()
            val url = urlEditText.text.toString()
            val price = priceEditText.text.toString()

            if (name.isNotEmpty() && url.isNotEmpty() && price.isNotEmpty()) {
                val item = Wishlist(name, price, url)
                items.add(item)
                // Notify RecyclerView adapter about data changes
                wishlistRv.adapter?.notifyDataSetChanged()
                clearInputs()
            }
        }

        // Initialize items list
        items = mutableListOf()
        // Set adapter for RecyclerView
        wishlistRv.adapter = WishlistAdapter(items)
    }

    private fun clearInputs() {
        nameEditText.text.clear()
        urlEditText.text.clear()
        priceEditText.text.clear()
    }
}
