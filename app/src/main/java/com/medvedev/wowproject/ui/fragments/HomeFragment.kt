package com.medvedev.wowproject.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.medvedev.wowproject.R
import com.medvedev.wowproject.ui.HomeActivity
import com.medvedev.wowproject.ui.viewmodel.HomeViewModel


class HomeFragment : Fragment(R.layout.fragment_home) {

    lateinit var viewModel : HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as HomeActivity).viewModelHome

        val btnRank : Button = view.findViewById(R.id.btnR)
        val btnGuid : Button = view.findViewById(R.id.btnG)

        btnGuid.setOnClickListener { findNavController().navigate(R.id.action_homeFragment_to_guideHomeFragment) }
        btnRank.setOnClickListener { findNavController().navigate(R.id.action_homeFragment_to_rankingFragment) }
    }
}