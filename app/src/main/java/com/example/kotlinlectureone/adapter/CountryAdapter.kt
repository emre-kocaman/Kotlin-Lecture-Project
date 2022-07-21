package com.example.kotlinlectureone.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinlectureone.R
import com.example.kotlinlectureone.databinding.CountryRowBinding
import com.example.kotlinlectureone.model.Country

class CountryAdapter(val list: ArrayList<Country>) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {


    class CountryViewHolder(val view: CountryRowBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val view = DataBindingUtil.inflate<CountryRowBinding>(
            inflater, R.layout.country_row, parent, false
        )


        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val item = list[position]
        holder.view.country = item
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateCountryList(newCountryList: List<Country>) {
        list.clear()
        list.addAll(newCountryList)
        notifyDataSetChanged()
    }
}