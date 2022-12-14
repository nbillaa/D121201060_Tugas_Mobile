package com.D121201060.task.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.D121201060.task.R
import com.D121201060.task.adapter.HistoryAdapter
import com.D121201060.task.databinding.FragmentHistoryBinding
import com.D121201060.task.viewmodel.TugasViewModel

class HistoryFragment : Fragment() {
    private var _binding: FragmentHistoryBinding?=null
    private val binding get() = _binding!!
    private lateinit var tugasViewModel: TugasViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHistoryBinding.inflate(inflater,container,false)
        val view = binding.root

        tugasViewModel = ViewModelProvider(this)[TugasViewModel::class.java]

        val adapter = HistoryAdapter()
        binding.tugasRecycle.adapter = adapter
        binding.tugasRecycle.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL,false)

        tugasViewModel = ViewModelProvider(this)[TugasViewModel::class.java]
        tugasViewModel.readAllDataHistory.observe(viewLifecycleOwner){ tugas->
            adapter.setData(tugas)
        }
        binding.deleteHistory.setOnClickListener {
            tugasViewModel.deleteAllHistory()
            Toast.makeText(requireContext(),"Berhasil dibersihkan",Toast.LENGTH_SHORT).show()
        }
        return view
    }

}