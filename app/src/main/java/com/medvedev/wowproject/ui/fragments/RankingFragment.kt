package com.medvedev.wowproject.ui.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.medvedev.wowproject.R
import com.medvedev.wowproject.adapters.RankingAdapter
import com.medvedev.wowproject.ui.HomeActivity
import com.medvedev.wowproject.ui.viewmodel.HomeViewModel
import com.medvedev.wowproject.util.Constants
import com.medvedev.wowproject.util.Resource
import kotlinx.android.synthetic.main.fragment_ranking.*


class RankingFragment : Fragment(R.layout.fragment_ranking) {
    lateinit var viewModel : HomeViewModel
    lateinit var rankingAdapter: RankingAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupRadioButtons()
        viewModel = (activity as HomeActivity).viewModelHome
        rankingAdapter.setOnItemClickListener {
            val bundle = Bundle().apply{
                putSerializable("spec" , it)
            }
            findNavController().navigate(R.id.action_rankingFragment_to_specFragment, bundle)
        }

        viewModel.rankinglist.observe(viewLifecycleOwner) {
                response ->
            when(response){
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { rankingResponse ->
                        rankingAdapter.differ.submitList(rankingResponse)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let{message->
                        Log.e(TAG, "An error occured: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }

            }
        }
    }

    private fun setupRadioButtons(){
        RBModeMax.setOnClickListener{onRadioButtonClick()}
        RBModeReal.setOnClickListener{onRadioButtonClick()}
        RBTarget1.setOnClickListener{onRadioButtonClick()}
        RBTarget3.setOnClickListener{onRadioButtonClick()}
    }

    private fun onRadioButtonClick(){
        if(RBModeMax.isChecked && RBTarget3.isChecked)
            viewModel.getRankingByModeTarget(Constants.Target.MULTIPLE,Constants.Mode.MAX)
        else if(RBModeReal.isChecked && RBTarget3.isChecked)
            viewModel.getRankingByModeTarget(Constants.Target.MULTIPLE,Constants.Mode.REAL)
        else if(RBModeMax.isChecked && RBTarget1.isChecked)
            viewModel.getRankingByModeTarget(Constants.Target.SINGLE,Constants.Mode.MAX)
        else if(RBModeReal.isChecked && RBTarget1.isChecked)
            viewModel.getRankingByModeTarget(Constants.Target.SINGLE,Constants.Mode.REAL)

    }

    private fun hideProgressBar(){
        progressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar(){
        progressBar.visibility = View.VISIBLE
    }


    private fun setupRecyclerView(){
        rankingAdapter = RankingAdapter()
        rankingRV.apply{
            adapter = rankingAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }


}