package com.example.pexipconference.screens.form

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.pexipconference.databinding.FragmentFormBinding

class FormFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentFormBinding.inflate(inflater, container, false)
        binding.joinButton.setOnClickListener {

            val node = binding.nodeText.editText?.text.toString()
            val vmr = binding.vmrText.editText?.text.toString()
            val displayName = binding.displayNameText.editText?.text.toString()

            val action = FormFragmentDirections.actionFormFragmentToConferenceFragment(
                node, vmr, displayName, null
            )
            findNavController().navigate(action)
        }
        return binding.root
    }

}