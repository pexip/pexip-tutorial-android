package com.example.pexipconference.screens.conference

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.pexipconference.R

class ConferenceFragment : Fragment() {

    // TODO (27) Define the private variable to store the binding and viewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // TODO (28) Remove the way the layout is inflated. We will use data binding
        return inflater.inflate(R.layout.fragment_conference, container, false)
        // TODO (29) Retrieve the safeArgs
        // TODO (30) Set the action bar title with the VMR name
        // TODO (31) Inflate the layout
        // TODO (32) Instanciate the viewModel
        // TODO (33) Initialialize the video surfaces
        // TODO (34) Set observers
        // TODO (35) Check the media permissions
        // TODO (36) Start the conference
    }

    // TODO (37) Override onDestroyView() to release the video surfaces

    // TODO (38) Override onStop() to stop capturing the local video

    // TODO (39) Override onStart() to start capturing the local video

    // TODO (40) Define the private method initializeVideoSurfaces

    // TODO (41) Define the private method setVideoObservers

    // TODO (42) Define the private method setConnectionObservers

    // TODO (43) Define the private method checkMediaPermissions

}