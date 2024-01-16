package com.example.fragmentlogandsign.fragments

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.UiThread
import androidx.core.view.isVisible
import com.example.fragmentlogandsign.R
import com.example.fragmentlogandsign.database.AppDataBase
import com.example.fragmentlogandsign.database.Person
import com.example.fragmentlogandsign.databinding.FragmentAuthBinding

class AuthFragment : Fragment() {

    private var binding: FragmentAuthBinding? = null
    private var login: String? = null
    private var password: String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        login = arguments?.getString("login")
        password = arguments?.getString("password")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.edLogin?.setText(login)

        binding?.btLogIn?.setOnClickListener {
            val db = AppDataBase.getDB(requireContext())

            Thread {
                val result: Person? = db.getDao().qetUserByLogin(binding?.edLogin?.text.toString())

                activity?.runOnUiThread() {
                    binding?.let { safeBinding ->
                        if (result != null && result.password == binding?.edPassword?.text.toString()) {

                            val bundle = Bundle()
                            bundle.putString("name", result.name)
                            bundle.putString("surname", result.surname)
                            bundle.putString("email", result.email)
                            bundle.putString("gender", result.gender)

                            parentFragmentManager.beginTransaction()
                                .addToBackStack(AuthFragment::class.java.canonicalName)
                                .replace(
                                    R.id.fragmentContainer,
                                    InfoDisplayFragment::class.java,
                                    bundle
                                )
                                .commit()

                            binding?.edLogin?.setTextColor(Color.BLACK)
                            binding?.edPassword?.setTextColor(Color.BLACK)
                            binding?.tvError?.isVisible = false
                        } else {
                            binding?.edLogin?.setTextColor(Color.RED)
                            binding?.edPassword?.setTextColor(Color.RED)
                            binding?.tvError?.isVisible = true
                        }
                    }
                }
            }.start()
        }

        binding?.btSignUp?.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .addToBackStack(AuthFragment::class.java.canonicalName)
                .replace(R.id.fragmentContainer, SignFragment()).commit()
        }
    }

}