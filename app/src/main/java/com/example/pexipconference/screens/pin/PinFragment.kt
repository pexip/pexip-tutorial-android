package com.example.pexipconference.screens.pin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pexipconference.R

class PinFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // TODO (07) Change the way the layout is inflated and use data binding
        return inflater.inflate(R.layout.fragment_pin, container, false)
        // TODO (08) Read the safe args
        // TODO (09) Set the click listener to navigate to the conference with the new PIN
    }

}