package com.example.drapino

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.drapino.databinding.FragmentSignUpBinding


class SignUpFragment : Fragment() {
    private lateinit var binding : FragmentSignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater)
        binding.signUpText.setOnClickListener{
            findNavController().popBackStack()
        }
        binding.logInButton.setOnClickListener{
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }

}