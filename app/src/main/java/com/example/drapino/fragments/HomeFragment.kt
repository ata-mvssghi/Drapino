package com.example.drapino.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.drapino.HomeViewModel
import com.example.drapino.adapter.HistoryAdapter
import com.example.drapino.databinding.FragmentHomeBinding
import com.example.drapino.entity.HistoryEntity

class HomeFragment : Fragment() {
    private lateinit var binding  : FragmentHomeBinding
    private lateinit var adapter:HistoryAdapter
    private lateinit var recyclerView:RecyclerView
    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        recyclerView = binding.recyclerView
        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager
        adapter = HistoryAdapter()
        val his1 = HistoryEntity("افق کوروش",2000,"1402/01/21")
        val his2 = HistoryEntity("رفاه",2000,"1402/01/21")
        val his3 = HistoryEntity("اراز",2000,"1402/03/11")
        val his4 = HistoryEntity("سوطلان",2000,"1402/11/25")
        val myArray = ArrayList<HistoryEntity>()
        myArray.add(his1)
        myArray.add(his2)
        myArray.add(his3)
        myArray.add(his4)
        adapter.differ.submitList(myArray)
        recyclerView.adapter = adapter
        return  binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
    }

}