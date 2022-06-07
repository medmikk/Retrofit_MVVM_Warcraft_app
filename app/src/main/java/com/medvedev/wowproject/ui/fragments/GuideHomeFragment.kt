package com.medvedev.wowproject.ui.fragments

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.medvedev.wowproject.R
import com.medvedev.wowproject.adapters.GuideAdapter
import com.medvedev.wowproject.adapters.RankingAdapter
import com.medvedev.wowproject.ui.HomeActivity
import com.medvedev.wowproject.ui.viewmodel.HomeViewModel
import com.medvedev.wowproject.util.Resource
import kotlinx.android.synthetic.main.fragment_guide_home.*
import kotlinx.android.synthetic.main.fragment_ranking.*


class GuideHomeFragment : Fragment(R.layout.fragment_guide_home) {
    lateinit var guideAdapter: GuideAdapter
    lateinit var viewModel : HomeViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        viewModel = (activity as HomeActivity).viewModelHome

        guideAdapter.setOnItemClickListener {
            val bundle = Bundle().apply{
                putSerializable("guide", it)
            }
            findNavController().navigate(R.id.action_guideHomeFragment_to_guideDetailFragment, bundle)
        }

        viewModel.guides.observe(viewLifecycleOwner){
            response ->
            when(response){
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { guideResponse ->
                        guideAdapter.differ.submitList(guideResponse)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let{message->
                        Log.e(ContentValues.TAG, "An error occured: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        }
    }

    private fun hideProgressBar(){
        progressBar3.visibility = View.INVISIBLE
    }

    private fun showProgressBar(){
        progressBar3.visibility = View.VISIBLE
    }


    private fun setupRecyclerView(){
        guideAdapter = GuideAdapter()
        GuideHomeRV.apply{
            adapter = guideAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}