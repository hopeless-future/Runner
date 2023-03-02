package com.tracker.runner.ui.fragments

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.tracker.runner.R
import com.tracker.runner.databinding.FragmentMainBinding
import com.tracker.runner.utils.Constants.LOCATION_PERMISSIONS_REQUEST_CODE
import com.tracker.runner.utils.NetworkState
import com.tracker.runner.utils.isHaveLocationPermissions
import com.tracker.runner.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions

@RequiresApi(Build.VERSION_CODES.M)
@AndroidEntryPoint
class MainFragment : Fragment(), EasyPermissions.PermissionCallbacks {
    private lateinit var binding: FragmentMainBinding
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkInternetConnection()
        requestPermissions()
        observeNetworkState()
    }

    private fun checkInternetConnection() { mainViewModel.checkDeviceConnection(requireContext()) }

    private fun requestPermissions() {
        if (isHaveLocationPermissions(requireContext())) return
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) EasyPermissions.requestPermissions(
            this, resources.getString(R.string.rationalRequestPermissionsText), LOCATION_PERMISSIONS_REQUEST_CODE,
            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
        )
        else {
            EasyPermissions.requestPermissions(
                this, resources.getString(R.string.rationalRequestPermissionsText), LOCATION_PERMISSIONS_REQUEST_CODE,
                Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_BACKGROUND_LOCATION
            )
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this@MainFragment)
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {}

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this@MainFragment, perms)) AppSettingsDialog.Builder(this).build().show()
        else requestPermissions()
    }

    private fun observeNetworkState() {
        viewLifecycleOwner.lifecycleScope.launch {
            mainViewModel.networkState.flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED).collect { networkState ->
                when (networkState) {
                    NetworkState.HasInternetConnection -> Unit
                    NetworkState.NoInternetConnection -> Toast.makeText(requireContext(), R.string.noInternetConnectionText, Toast.LENGTH_SHORT).show()
                    else -> Unit
                }
            }
        }
    }

}