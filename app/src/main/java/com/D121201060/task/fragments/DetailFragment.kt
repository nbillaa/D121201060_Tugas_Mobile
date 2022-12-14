package com.D121201060.task.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.D121201060.task.R
import com.D121201060.task.adapter.HistoryAdapter
import com.D121201060.task.databinding.FragmentDetailBinding
import com.D121201060.task.databinding.FragmentHistoryBinding
import com.D121201060.task.model.Task
import com.D121201060.task.viewmodel.TugasViewModel


class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding?=null
    private val binding get() = _binding!!
    private val args by navArgs<DetailFragmentArgs>()
    private lateinit var tugasViewModel: TugasViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDetailBinding.inflate(inflater,container,false)
        val view = binding.root

        tugasViewModel = ViewModelProvider(this)[TugasViewModel::class.java]

        binding.kategoriTugas.text = args.currentTask.kategori_tugas
        binding.judulTugas.text = args.currentTask.nama_tugas
        binding.deskripsiTugas.text = args.currentTask.deskripsi_tugas

        if (args.currentTask.status.equals("Selesai")){
            binding.btnDone.visibility = View.GONE
        }else{
            binding.btnDone.setOnClickListener {
                val selesai = Task(args.currentTask.id,args.currentTask.nama_tugas,args.currentTask.deskripsi_tugas,args.currentTask.kategori_tugas,"Selesai")
                tugasViewModel.updateTask(selesai)
                Toast.makeText(requireContext(),"Tugas Selesai",Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_detailFragment_to_homeFragment)
            }
        }

        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_detailFragment_to_homeFragment)
        }

        return view
    }
}