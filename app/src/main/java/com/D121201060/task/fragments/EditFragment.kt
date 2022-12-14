package com.D121201060.task.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.D121201060.task.R
import com.D121201060.task.databinding.FragmentDetailBinding
import com.D121201060.task.databinding.FragmentEditBinding
import com.D121201060.task.model.Task
import com.D121201060.task.viewmodel.TugasViewModel


class EditFragment : Fragment() {
    private var _binding: FragmentEditBinding?=null
    private val binding get() = _binding!!
    private lateinit var tugasViewModel: TugasViewModel
    private val args by navArgs<EditFragmentArgs>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentEditBinding.inflate(inflater,container,false)
        val view = binding.root

        tugasViewModel = ViewModelProvider(this)[TugasViewModel::class.java]

        binding.judulTugas.setText(args.currentTask.nama_tugas)
        binding.deskripsiTugas.setText(args.currentTask.deskripsi_tugas)
        binding.dropdownMenu.setText(args.currentTask.kategori_tugas)
        binding.dropdownMenu.setAdapter(ArrayAdapter.createFromResource(requireContext(),R.array.kategoriArray, android.R.layout.simple_list_item_1))

        binding.btnSave.setOnClickListener {
            val kategori = binding.dropdownMenu.text.toString()
            val judul_tugas = binding.judulTugas.text.toString()
            val isi_tugas = binding.deskripsiTugas.text.toString()

            if(kategori.isEmpty()){
                Toast.makeText(requireContext(),"Kategori kosong", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(judul_tugas.isEmpty()){
                binding.judulTugas.error = "Judul kosong"
                binding.judulTugas.requestFocus()
                return@setOnClickListener
            }
            if(isi_tugas.isEmpty()){
                binding.deskripsiTugas.error = "Isi kosong"
                binding.deskripsiTugas.requestFocus()
                return@setOnClickListener
            }
            val updateTugas = Task(args.currentTask.id, judul_tugas,isi_tugas,kategori,"Belum Selesai")
            tugasViewModel.updateTask(updateTugas)
            Toast.makeText(requireContext(),"Berhasil Edit Tugas", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_editFragment_to_homeFragment)
        }
        return view
    }
}