package com.hbox.assignment1.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hbox.assignment1.R
import com.hbox.assignment1.databinding.ItemCountryBinding
import com.hbox.assignment1.model.Country

class CountryAdapter(private var mContext: Context, private var countryList: List<Country>) :
    RecyclerView.Adapter<CountryAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemCountryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(countryList[position]) {
                binding.tvCountryName.text = this.name
                binding.tvCountryCapital.text =
                    mContext.getString(R.string.label_capital) + " : " + this.capital
                binding.tvCountryCurrency.text =
                    mContext.getString(R.string.label_currency) + " : " + this.currency

                binding.tvCountryCapital.visibility = if (this.expand) View.VISIBLE else View.GONE
                binding.tvCountryCurrency.visibility = if (this.expand) View.VISIBLE else View.GONE

                binding.linear.setOnClickListener{
                    this.expand = !this.expand
                    notifyDataSetChanged()
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return countryList.size
    }


}