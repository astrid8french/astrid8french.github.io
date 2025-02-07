/*
 * Name: Astrid French
 * Date: 15 August 2024
 * Prof. Jerome DiMarzio
 * CS360 - Project Three
 * */

package com.example.myinventory;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myinventory.database.Product;
import com.example.myinventory.database.ProductDataBase;

import java.util.List;

public class ListActivity extends AppCompatActivity {
    // Declare variables
    private ListView bodyListView;
    private ImageButton addItemButton;
    private ImageButton homeButton;
    private ImageButton deleteButton;
    private TextView textViewUserName;
    private ProductDataBase db;
    private List<Product> products;
    private CustomAdapter adapter;
    private Product selectedProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set layout from XML
        setContentView(R.layout.activity_list);

        // Initialize UI elements
        bodyListView = findViewById(R.id.bodyListView);
        addItemButton = findViewById(R.id.addItemButton);
        homeButton = findViewById(R.id.homeButton);
        deleteButton = findViewById(R.id.delete);
        textViewUserName = findViewById(R.id.textViewUserName);

        // Retrieve the username from Intent
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        if (username == null || username.isEmpty()) {
            username = "Guest"; // Fallback for missing username
        }
        textViewUserName.setText("Hi, " + username + "...");

        // Connect to database
        db = new ProductDataBase(this);
        products = db.getAllProducts();

        // Set up the list adapter
        adapter = new CustomAdapter(this, products);
        bodyListView.setAdapter(adapter);

        // Set up item click listener
        bodyListView.setOnItemClickListener((parent, view, position, id) -> {
            selectedProduct = products.get(position); // Store selected product
        });

        // Add button functionality
        addItemButton.setOnClickListener(v -> {
            Intent intentAdd = new Intent(ListActivity.this, AddListActivity.class);
            startActivity(intentAdd);
        });

        // Home button functionality
        homeButton.setOnClickListener(v -> {
            Intent intentHome = new Intent(ListActivity.this, HomeActivity.class);
            startActivity(intentHome);
        });

        // Delete button functionality
        deleteButton.setOnClickListener(v -> {
            if (selectedProduct != null) {
                new AlertDialog.Builder(ListActivity.this)
                        .setTitle("Delete Item")
                        .setMessage("Are you sure you want to delete this item?")
                        .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                            // Delete from database
                            db.deleteProduct(selectedProduct.getId());
                            // Remove from list
                            products.remove(selectedProduct);
                            adapter.notifyDataSetChanged();
                            selectedProduct = null;
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .show();
            }
        });
    }

    // Custom adapter for the product list
    private class CustomAdapter extends ArrayAdapter<Product> {
        public CustomAdapter(Context context, List<Product> products) {
            super(context, R.layout.activity_body_list_item, products);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = AddBodyList.createListItemView(
                        getContext(),
                        getItem(position),
                        product -> selectedProduct = product
                );
            }
            return convertView;
        }
    }
}
