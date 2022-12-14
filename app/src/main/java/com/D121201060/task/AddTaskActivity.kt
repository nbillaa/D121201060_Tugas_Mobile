package com.D121201060.task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.D121201060.task.databinding.ActivityAddTaskBinding
import com.D121201060.task.model.Task
import com.D121201060.task.viewmodel.TugasViewModel
import kotlinx.coroutines.launch

class AddTaskActivity : AppCompatActivity() {
    private lateinit var binding :ActivityAddTaskBinding
    private lateinit var tugasViewModel: TugasViewModel
    lateinit var kategoriTugas: ArrayAdapter<CharSequence>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tugasViewModel = ViewModelProvider(this)[TugasViewModel::class.java]
        kategoriTugas = ArrayAdapter.createFromResource(this,R.array.kategoriArray, android.R.layout.simple_list_item_1)
        binding.dropdownMenu.setAdapter(kategoriTugas)
        onAction()
    }
    private fun onAction(){
        binding.apply {
            back.setOnClickListener {
                finish()
            }
            btnAdd.setOnClickListener {
                sendToDB()
            }
        }
    }
    private fun sendToDB(){
        binding.apply {
            val kategori = dropdownMenu.text.toString()
            val judul = judulTugas.text.toString()
            val isi = deskripsiTugas.text.toString()
            if(kategori.isEmpty()){
                Toast.makeText(this@AddTaskActivity,"Kategori pilih dulu bambank",Toast.LENGTH_SHORT).show()
                dropdownMenu.requestFocus()
                return
            }
            if(judul.isEmpty()){
                judulTugas.error = "Judulnya kosong bang"
                judulTugas.requestFocus()
                return
            }
            if(isi.isEmpty()){
                deskripsiTugas.error = "Deskripsi tugas kosong"
                deskripsiTugas.requestFocus()
                return
            }
            lifecycleScope.launch{
                val tugas = Task(0,judul,isi,kategori, "Belum Selesai")
                tugasViewModel.addTask(tugas)
                Toast.makeText(this@AddTaskActivity,"Berhasil menambahkan tugas",Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}