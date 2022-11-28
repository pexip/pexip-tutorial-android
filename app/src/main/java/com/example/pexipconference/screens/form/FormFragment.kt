package com.example.pexipconference.screens.form

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pexipconference.R

class FormFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // TODO (09) Change the way the layout is inflated and use data binding
        return inflater.inflate(R.layout.fragment_form, container, false)
        // TODO (10) Define button listener to navigate to the Conference with all the parameters
    }

}