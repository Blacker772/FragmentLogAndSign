package com.example.fragmentlogandsign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fragmentlogandsign.R
import com.example.fragmentlogandsign.databinding.ActivityMainBinding
import com.example.fragmentlogandsign.fragments.AuthFragment

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

//        val authFragment = AuthFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, AuthFragment())
            .commit()

    }
}