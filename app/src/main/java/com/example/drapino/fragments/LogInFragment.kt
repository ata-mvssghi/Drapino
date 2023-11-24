package com.example.drapino.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.drapino.MainActivity
import com.example.drapino.R
import com.example.drapino.databinding.FragmentLogInBinding
import com.example.drapino.viewModels.LogInViewModel


class LogInFragment : Fragment() {
    private lateinit var binding :FragmentLogInBinding
    private lateinit var   correctedNumber:String
    lateinit var viewModel: LogInViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        ).get(LogInViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLogInBinding.inflate(inflater)
        viewModel.isChecked.observe(viewLifecycleOwner, Observer { newValue ->
            if(newValue==true){
                binding.phoneNumber.visibility = View.GONE
                binding.otp.visibility = View.VISIBLE
                binding.otpSubmitButton.visibility = View.VISIBLE
                binding.logInButton.visibility = View.GONE
            }
        })
        binding.logInButton.setOnClickListener{
            val phoneNumberField = binding.phoneNumber.text.toString()
             correctedNumber =  validatePhoneNumber(phoneNumberField)
            viewModel.getFirstApiRequest(correctedNumber)
//            val intent = Intent(activity, MainActivity::class.java)
//            startActivity(intent)
        }
        binding.otpSubmitButton.setOnClickListener{
            val otpCode = binding.otp.text.toString()
            viewModel.submitButtonClicked(correctedNumber,otpCode)
        }
        binding.signUpText.setOnClickListener {
            findNavController().navigate(R.id.action_logInFragment_to_signUpFragment)
        }

        return binding.root
    }
    fun validatePhoneNumber(givenNumber:String):String{
            // Remove any non-digit characters
            val digitsOnly = givenNumber.replace(Regex("[^\\d]"), "")

            // Check if the number starts with "0"
            val correctedNumber = if (digitsOnly.startsWith("0")) {
                // Add "98" at the beginning if it starts with "0"
                "98" + digitsOnly.substring(1)
            } else {
                // Add "98" if no country code is provided
                "98$digitsOnly"
            }
            return correctedNumber

    }
}