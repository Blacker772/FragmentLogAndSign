package com.example.fragmentlogandsign.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragmentlogandsign.R
import com.example.fragmentlogandsign.databinding.FragmentInfoDisplayBinding

class InfoDisplayFragment : Fragment() {

    private var binding: FragmentInfoDisplayBinding? = null
    private var name:String? = null
    private var surname:String? = null
    private var email:String? = null
    private var gender:String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentInfoDisplayBinding.inflate(layoutInflater,container,false)
        return binding?.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        name = arguments?.getString("name")
        surname = arguments?.getString("surname")
        email = arguments?.getString("email")
        gender = arguments?.getString("gender")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.tvName?.text = name
        binding?.tvSurName?.text = surname
        binding?.tvEmail?.text = email
        binding?.tvGender?.text = gender
    }

}