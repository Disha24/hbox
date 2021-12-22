package com.hbox.assignment1.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.hbox.assignment1.ui.adapter.CountryAdapter
import com.hbox.assignment1.R
import com.hbox.assignment1.databinding.ActivityMainBinding
import com.hbox.assignment1.model.Country

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var countryList: List<Country>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        loadCountry()

        binding.rvCountry.layoutManager = LinearLayoutManager(this)
        binding.rvCountry.setHasFixedSize(true)

        val adapter = CountryAdapter(this, countryList)
        binding.rvCountry.adapter = adapter

    }

    // add items to the list manually in our case
    private fun loadCountry() {
        countryList = listOf(
            Country("Afghanistan" , "Kabul", "Afghani"),
            Country("Australia" , "Canberra", "Australian Dollar"),
            Country("Bahrain" , "Manama", "Bahraini Dinar"),
            Country("Bangladesh" , "Dhaka", "Taka"),
            Country("Belgium" , "Brussels", "Euro"),
            Country("China" , "Beijing", "Chinese Yuan"),
            Country("Greece" , "Athens", "Euro"),
            Country("India" , "New Delhi", "Indian Rupee"),
            Country("Japan" , "Tokyo", "Yen"),
            Country("United States of America" , "Washington D.C.", "United States Dollar"),
        )
    }
}