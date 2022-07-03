package com.wenger.mytestapplication.ui.fragments

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.wenger.mytestapplication.R
import com.wenger.mytestapplication.data.Prefs
import com.wenger.mytestapplication.databinding.StartFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartFragment : Fragment(R.layout.start_fragment) {

    private val viewModel: StartViewModel by viewModels()
    private var binding: StartFragmentBinding? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = StartFragmentBinding.bind(view)
        setupView()
    }

    private fun setupView() {
        binding?.apply {
            yesBtn.setOnClickListener {
                viewModel.agreeTermsAndConditions()
                findNavController().apply {
                    navigate(R.id.go_to_web_view_fragment)
                    backQueue.clear()
                }
            }
            exitBtn.setOnClickListener {
                activity?.finish()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}