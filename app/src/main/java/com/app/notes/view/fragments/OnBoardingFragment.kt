package com.app.notes.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.notes.R
import com.app.notes.databinding.FragmentOnBordingBinding
import com.app.notes.utils.OnBoardingMetaData
import com.app.notes.view.adapter.OnBoardingAdapter


class OnBoardingFragment : Fragment() {

   private lateinit var binding:FragmentOnBordingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOnBordingBinding.inflate(layoutInflater)
        val onBoardingMetadata = OnBoardingMetaData()
        val list  = onBoardingMetadata.getOnBoardingData()
        val adapter = OnBoardingAdapter(list,requireContext())
        binding.viewPager2.adapter = adapter
        binding.getStartedBtn.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, LoginFragment())
                .commit()
        }


        return binding.root
    }


}