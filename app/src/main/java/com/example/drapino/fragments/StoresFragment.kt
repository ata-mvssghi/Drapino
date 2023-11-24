package com.example.drapino.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.drapino.adapter.HistoryAdapter
import com.example.drapino.adapter.StoreAdapter
import com.example.drapino.databinding.FragmentStoresBinding
import com.example.drapino.entity.HistoryEntity
import com.example.drapino.entity.StoreItem


class StoresFragment : Fragment() {
    private lateinit var binding : FragmentStoresBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter : StoreAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      binding = FragmentStoresBinding.inflate(inflater)
        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView = binding.storeRecyclerView
        recyclerView.layoutManager = layoutManager
        adapter = StoreAdapter()
        val his1 = StoreItem("افق کوروش",10)
        val his2 = StoreItem("رفاه",20)
        val his3 = StoreItem("اراز",2)
        val his4 = StoreItem("آراگون",7)

        val myArray = ArrayList<StoreItem>()
        myArray.add(his1)
        myArray.add(his2)
        myArray.add(his3)
        myArray.add(his4)
        adapter.differ.submitList(myArray)
        recyclerView.adapter = adapter
        return binding.root
    }

}