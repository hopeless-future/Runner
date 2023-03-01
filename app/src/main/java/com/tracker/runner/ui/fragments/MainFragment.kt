package com.tracker.runner.ui.fragments

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.tracker.runner.R
import com.tracker.runner.databinding.FragmentMainBinding
import com.tracker.runner.utils.Constants.LOCATION_PERMISSIONS_REQUEST_CODE
import com.tracker.runner.utils.isHaveLocationPermissions
import com.tracker.runner.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions

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
        requestPermissions()
    }

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

}