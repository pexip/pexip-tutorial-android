package com.example.pexipconference.screens.conference

import android.Manifest
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

import com.example.pexipconference.R
import com.example.pexipconference.databinding.FragmentConferenceBinding
import com.google.android.material.snackbar.Snackbar
import com.pexip.sdk.api.infinity.InvalidPinException
import com.pexip.sdk.api.infinity.NoSuchConferenceException
import com.pexip.sdk.api.infinity.RequiredPinException
import org.webrtc.RendererCommon

class ConferenceFragment : Fragment() {

    private lateinit var binding: FragmentConferenceBinding
    private lateinit var viewModel: ConferenceViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // This variable has the node, vmr and displayName
        val args by navArgs<ConferenceFragmentArgs>()

        // Change the Action Bar title and put the Virtual Meeting Room
        (activity as AppCompatActivity).supportActionBar?.title = args.vmr

        // Inflate the layout for this fragment
        binding = FragmentConferenceBinding.inflate(
            inflater,
            container,
            false
        )

        // Create an instance of the viewModel and attach it to the data binding
        val application = requireNotNull(this.activity).application
        val viewModelFactory = ConferenceViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory)[ConferenceViewModel::class.java]
        binding.viewModel = viewModel

        // Assign this fragment as lifecycle owner
        binding.lifecycleOwner = this

        // Initialize the containers for the videoTracks
        initializeVideoSurfaces()

        // Set all observers
        setVideoObservers()
        setConnectionObservers(args.node, args.vmr, args.displayName)

        if (viewModel.isConnected.value != true) {
            // Check the media permissions or show a pop-up to accept them
            checkMediaPermissions() {
                // Callback once the permission was correctly checked
                viewModel.startConference(args.node, args.vmr, args.displayName, args.pin)
            }

        }

        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.localVideoTrack.value?.removeRenderer(binding.localVideoSurface)
        binding.localVideoSurface.release()
        viewModel.remoteVideoTrack.value?.removeRenderer(binding.mainVideoSurface)
        binding.mainVideoSurface.release()
    }

    override fun onStop() {
        super.onStop()
        viewModel.localVideoTrack.value?.stopCapture()
    }

    override fun onStart() {
        super.onStart()
        if (viewModel.isLocalVideoMuted.value != true) {
            viewModel.localVideoTrack.value?.startCapture()
        }
    }

    private fun initializeVideoSurfaces() {
        // Mirror the local video
        binding.localVideoSurface.setMirror(true)

        // Show all the video inside the container
        binding.mainVideoSurface.setScalingType(RendererCommon.ScalingType.SCALE_ASPECT_FIT)

        // Initialize the video surfaces
        binding.localVideoSurface.init(viewModel.eglBase.eglBaseContext, null)
        binding.mainVideoSurface.init(viewModel.eglBase.eglBaseContext, null)
    }

    private fun setVideoObservers() {
        // Initialize observer to attach the VideoTrack to the surface renderers
        viewModel.localVideoTrack.observe(viewLifecycleOwner, Observer { videoTrack ->
            videoTrack.addRenderer(binding.localVideoSurface)
        })
        viewModel.remoteVideoTrack.observe(viewLifecycleOwner, Observer { videoTrack ->
            videoTrack.addRenderer(binding.mainVideoSurface)
        })
    }

    private fun setConnectionObservers(node: String, vmr: String, displayName: String) {
        // Initialize observer to display connectivity changes
        viewModel.isConnected.observe(viewLifecycleOwner, Observer { isConnected ->
            if (!isConnected) {
                // The conference finished
                findNavController().popBackStack()
            }
        })

        // Error detected. Display a Snackbar with it.
        viewModel.onError.observe(viewLifecycleOwner, Observer { exception ->
            val error: String = when (exception) {
                is RequiredPinException -> {
                    val action = ConferenceFragmentDirections.actionConferenceFragmentToPinFragment(
                        node, vmr, displayName
                    )
                    findNavController().navigate(action)
                    ""
                }
                is InvalidPinException -> {
                    resources.getString(R.string.wrong_pin)
                }
                is NoSuchConferenceException -> {
                    resources.getString(R.string.conference_not_found, vmr)
                }
                else -> {
                    resources.getString(R.string.cannot_connect, node)
                }
            }
            if (error.isNotEmpty()) {
                val parentView = requireActivity().findViewById<View>(android.R.id.content)
                Snackbar.make(parentView, error, Snackbar.LENGTH_LONG).show()
                findNavController().popBackStack()
            }
        })
    }

    private fun checkMediaPermissions(callback: () -> Unit) {
        val requestMultiplePermissions =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
                if (!permissions.entries.all { it.value }) {
                    val parentView = requireActivity().findViewById<View>(android.R.id.content)
                    Snackbar.make(parentView, R.string.grant_media_permissions, Snackbar.LENGTH_LONG).show()
                    findNavController().popBackStack()
                } else {
                    callback()
                }
            }
        requestMultiplePermissions.launch(
            arrayOf(
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.CAMERA
            )
        )
    }

}