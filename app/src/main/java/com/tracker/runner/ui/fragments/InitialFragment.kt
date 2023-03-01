package com.tracker.runner.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.tracker.runner.R
import com.tracker.runner.databinding.FragmentInitialBinding
import com.tracker.runner.utils.validation.ValidationStatus
import com.tracker.runner.utils.navigateToAnotherFragment
import com.tracker.runner.viewmodels.InitialViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InitialFragment : Fragment() {
    private lateinit var binding: FragmentInitialBinding
    private val initialViewModel by viewModels<InitialViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInitialBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        saveUser()
        observeUserNavigationFlow()
        observeUserValidationFlow()
    }

    private fun saveUser() {
        binding.letsGoButton.setOnClickListener {
            val name = binding.nameEditText.text.toString()
            val weight = binding.weightEditText.text.toString()
            initialViewModel.saveUserInfo(requireContext(), name, weight)
        }
    }

    private fun observeUserNavigationFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            initialViewModel.navigationFlow.flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED).collect { isNavigateToAnotherFragment ->
                when (isNavigateToAnotherFragment) {
                    true -> navigateToAnotherFragment(R.id.action_initialFragment_to_mainFragment)
                    else -> return@collect
                }
            }
        }
    }

    private fun observeUserValidationFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            initialViewModel.userValidationFlow.flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED).collect { userValidationState ->
                if (userValidationState.name is ValidationStatus.Error) {
                    binding.nameEditText.apply {
                        requestFocus()
                        error = userValidationState.name.errorMessage
                    }
                }
                if (userValidationState.weight is ValidationStatus.Error) {
                    binding.weightEditText.apply {
                        requestFocus()
                        error = userValidationState.weight.errorMessage
                    }
                }
            }
        }
    }

}