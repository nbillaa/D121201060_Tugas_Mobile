package com.D121201060.task.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.D121201060.task.R
import com.D121201060.task.adapter.TaskAdapter
import com.D121201060.task.databinding.FragmentHomeBinding
import com.D121201060.task.viewmodel.TugasViewModel
import com.airbnb.lottie.LottieDrawable

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding?=null
    private val binding get() = _binding!!

    private lateinit var tugasViewModel: TugasViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        val view = binding.root


        val adapter = TaskAdapter()
        binding.tugasRecycle.adapter = adapter
        binding.tugasRecycle.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL,false)

        tugasViewModel = ViewModelProvider(this)[TugasViewModel::class.java]
        tugasViewModel.readAllData.observe(viewLifecycleOwner){ tugas->
            adapter.setData(tugas)
            checkSize(tugas.size)
        }
        return view
    }
    private fun checkSize(size: Int) {
        binding.apply {
            if (size<=0){
                tugasKosong.visibility= View.VISIBLE
                tugasRecycle.visibility = View.GONE
            }else{
                tugasKosong.visibility= View.GONE
                tugasRecycle.visibility = View.VISIBLE
            }
        }
    }


}