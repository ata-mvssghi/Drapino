package com.example.drapino

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.drapino.databinding.FragmentLogInBinding


class LogInFragment : Fragment() {
    private lateinit var binding :FragmentLogInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLogInBinding.inflate(inflater)
        binding.logInButton.setOnClickListener{
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }
        binding.signUpText.setOnClickListener {
            findNavController().navigate(R.id.action_logInFragment_to_signUpFragment)
        }


        return binding.root
    }

}