package com.medvedev.wowproject.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.medvedev.wowproject.R
import com.medvedev.wowproject.ui.HomeActivity
import com.medvedev.wowproject.ui.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_guide_detail.*


class GuideDetailFragment : Fragment(R.layout.fragment_guide_detail) {

    lateinit var viewModel : HomeViewModel
    val args: GuideDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as HomeActivity).viewModelHome
        val guide = args.guide

        Glide.with(this).load(guide.img_link).into(DGIV)
        GDNameTV.text = guide.spec_name
        GDindro.text = guide.intro
        GDStatPriority.text = guide.pve_stat_priority
        GDStatWeights.text = guide.stat_weights
        GDTalentBuilds.text = guide.talent_builds
        textView15.text = guide.lvl_15
        textView25.text = guide.lvl_25
        textView30.text = guide.lvl_30
        textView35.text = guide.lvl_35
        textView40.text = guide.lvl_40
        textView45.text = guide.lvl_45
        textView50.text = guide.lvl_50
    }
}