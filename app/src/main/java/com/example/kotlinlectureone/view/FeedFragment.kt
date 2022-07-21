package com.example.kotlinlectureone.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinlectureone.R
import com.example.kotlinlectureone.adapter.CountryAdapter
import com.example.kotlinlectureone.viewmodel.FeedViewModel
import kotlinx.android.synthetic.main.fragment_feed.*


class FeedFragment : Fragment() {

    private lateinit var viewmodel: FeedViewModel

    private val countryAdapter = CountryAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewmodel = ViewModelProvider(this).get(FeedViewModel::class.java)
        viewmodel.getDataFromApi()

        countryList.layoutManager = LinearLayoutManager(context)
        countryList.adapter = countryAdapter

        observeMutableLiveDatas()


    }

    private fun observeMutableLiveDatas() {
        viewmodel.countries.observe(viewLifecycleOwner) { countries ->
            countries?.let {
                errorText.visibility = View.GONE
                countryAdapter.updateCountryList(countries)
            }
        }

        viewmodel.countryError.observe(viewLifecycleOwner) { error ->
            error?.let { hataVarmi ->
                if (hataVarmi) {
                    errorText.visibility = View.VISIBLE
                } else {
                    errorText.visibility = View.GONE
                }

            }

        }

        viewmodel.countryLoading.observe(viewLifecycleOwner){ isloading ->


        }

    }

    companion object {

    }
}