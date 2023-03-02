package com.tracker.runner.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.tracker.runner.R
import com.tracker.runner.databinding.FragmentInitialBinding
import com.tracker.runner.utils.validation.ValidationStatus
import com.tracker.runner.utils.navigateToAnotherFragment
import com.tracker.runner.viewmodels.InitialViewModel
import dagger.hilt.android.AndroidEntryPoint

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
        handleValidationStatus()
        observeNameAndWeightValidationState()
    }

    private fun handleValidationStatus() {
        binding.apply {
            nameEditText.addTextChangedListener { nameEditable ->
                nameEditable?.let { initialViewModel.checkNameValidation(it.toString()) }
            }
            weightEditText.addTextChangedListener { weightEditable ->
                weightEditable?.let { initialViewModel.checkWeightValidation(it.toString()) }
            }
        }
    }

    private fun saveUserToSharedPreferencesAndNavigateToMainFragment(context: Context, nameInput: String, weightInput: String) {
        binding.letsGoButton.setOnClickListener {
            initialViewModel.saveUserInfo(context, nameInput, weightInput)
            navigateToAnotherFragment(R.id.action_initialFragment_to_mainFragment)
        }
    }

    private fun setEnabledLetsGoButton() {
        binding.letsGoButton.apply {
            isEnabled = true
            background = ResourcesCompat.getDrawable(resources, R.drawable.lets_go_button_active, null)
        }
    }

    private fun setDisabledLetsGoButton() {
        binding.letsGoButton.apply {
            isEnabled = false
            background = ResourcesCompat.getDrawable(resources, R.drawable.lets_go_button_inactive, null)
        }
    }

    private fun showErrorMessageEditText(editText: EditText, message: String) {
        editText.apply { error = message }
    }

    private fun observeNameAndWeightValidationState() {
        initialViewModel.apply {
            nameValidation.observe(viewLifecycleOwner) { nameValidationState ->
                weightValidation.observe(viewLifecycleOwner) { weightValidationState ->
                    if (nameValidationState.name is ValidationStatus.Error) {
                        showErrorMessageEditText(binding.nameEditText, nameValidationState.name.errorMessage)
                        setDisabledLetsGoButton()
                    }
                    else if (weightValidationState.weight is ValidationStatus.Error) {
                        showErrorMessageEditText(binding.weightEditText, weightValidationState.weight.errorMessage)
                        setDisabledLetsGoButton()
                    }
                    else if (nameValidationState.name is ValidationStatus.Success && weightValidationState.weight is ValidationStatus.Success) {
                        setEnabledLetsGoButton()
                        saveUserToSharedPreferencesAndNavigateToMainFragment(requireContext(), nameValidationState.nameInput!!, weightValidationState.weightInput!!)
                    }
                }
            }
        }
    }

}