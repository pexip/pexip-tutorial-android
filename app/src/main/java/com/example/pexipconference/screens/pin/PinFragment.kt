package com.example.pexipconference.screens.pin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.pexipconference.R
import com.example.pexipconference.databinding.FragmentPinBinding

class PinFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val binding = FragmentPinBinding.inflate(
            inflater,
            container,
            false
        )

        val args by navArgs<PinFragmentArgs>()
        binding.enterPinButton.setOnClickListener {
            val action = PinFragmentDirections.actionPinFragmentToConferenceFragment(
                args.node, args.vmr, args.displayName, binding.pinText.text.toString()
            )
            findNavController().navigate(action)
        }
        return binding.root

    }

}