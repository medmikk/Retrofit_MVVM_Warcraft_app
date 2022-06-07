package com.medvedev.wowproject.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.medvedev.wowproject.R
import com.medvedev.wowproject.adapters.SpecAdapter
import com.medvedev.wowproject.ui.HomeActivity
import com.medvedev.wowproject.ui.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_ranking.*
import kotlinx.android.synthetic.main.fragment_spec.*


class SpecFragment : Fragment(R.layout.fragment_spec) {
    lateinit var viewModel : HomeViewModel
    lateinit var specAdapter: SpecAdapter

    val args: SpecFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        viewModel = (activity as HomeActivity).viewModelHome
        val spec = args.spec


        specAdapter.setOnItemClickListener {
            if(it.href != null) {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(it.href))
                startActivity(browserIntent)
            }
            }
        nameSpecTV.text = spec.name
        percentSpecTV.text = spec.proc
        DPSSpecTV.text = spec.dps
        specAdapter.differ.submitList(spec.spelllist)


        FromSpecToRankingTV.setOnClickListener {
            findNavController().navigate(R.id.action_specFragment_to_rankingFragment) }
    }

    private fun setupRecyclerView(){
        specAdapter = SpecAdapter()
        specRV.apply{
            adapter = specAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}