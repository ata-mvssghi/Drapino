package com.example.drapino.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.drapino.R
import com.example.drapino.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navigationView = binding.bottomNavigationView
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment

        navigationView.setupWithNavController(navHostFragment.navController)
//        navigationView.setOnNavigationItemSelectedListener{item ->
//            when(item.itemId){
//                R.id.navigation_home->{
//                    findNavController().navigate(R.id.)
//                    true
//                }
//                R.id.favorites->{
//                    navController.navigate(R.id.action_previewFragment_to_favoriteFragment)
//
//                    true
//                }
//                else->false
//            }
//        }
    }
}