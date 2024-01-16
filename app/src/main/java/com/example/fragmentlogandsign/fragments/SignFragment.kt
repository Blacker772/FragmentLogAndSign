package com.example.fragmentlogandsign.fragments

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.isVisible
import com.example.fragmentlogandsign.R
import com.example.fragmentlogandsign.database.AppDataBase
import com.example.fragmentlogandsign.database.Person
import com.example.fragmentlogandsign.databinding.FragmentSignBinding

class SignFragment : Fragment() {

    private var binding: FragmentSignBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = AppDataBase.getDB(requireContext())

        binding?.Registration?.setOnClickListener {
            val name = binding?.etName?.text.toString()
            val surname = binding?.edSurName?.text.toString()
            val login = binding?.edLogin?.text.toString()
            val password = binding?.edPassword?.text.toString()
            val email = binding?.edEmail?.text.toString()

            var gender: String? = null

            when (binding?.radioGroup?.checkedRadioButtonId) {
                binding?.male?.id -> {
                    gender = "Male"
                }

                binding?.Female?.id -> {
                    gender = "Female"
                }

                else -> {}
            }

            if (name.isEmpty()
                || surname.isEmpty()
                || login.isEmpty()
                || password.isEmpty()
                || email.isEmpty()
                || gender.isNullOrEmpty()
            ) {
                binding?.tvError?.isVisible = true
            } else {
                binding?.tvError?.text = "Ваш Аккаунт создан успешно!"
                binding?.tvError?.setTextColor(Color.GREEN)
                Toast.makeText(context, "Ваш Аккаунт создан успешно!", Toast.LENGTH_SHORT).show()

                    Thread{
                        db.getDao().insertPerson(
                            Person(null, name, surname, login, password, email, gender)
                        )
                    }.start()

                val bundle = Bundle()
                bundle.putString("login", login)
                bundle.putString("password", password)

                parentFragmentManager.beginTransaction()
                    .addToBackStack(SignFragment::class.java.canonicalName)
                    .replace(R.id.fragmentContainer, AuthFragment::class.java, bundle)
                    .commit()
            }
        }
    }

}