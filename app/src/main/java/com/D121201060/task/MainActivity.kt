package com.D121201060.task

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.D121201060.task.databinding.ActivityMainBinding
import com.D121201060.task.databinding.TaskAdapterBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding :ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navControl = findNavController(R.id.fragment)
        binding.bottomNavigationView.setupWithNavController(navControl)

        navControl.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.detailFragment||destination.id == R.id.edit_tugas) {
                binding.addTask.visibility = View.GONE
                binding.bottomAppBar.visibility = View.GONE
            } else {
                binding.addTask.visibility = View.VISIBLE
                binding.bottomAppBar.visibility = View.VISIBLE
            }
        }
        binding.addTask.setOnClickListener {
            val intent = Intent(this,AddTaskActivity::class.java)
            startActivity(intent)
        }


    }

    override fun onSupportNavigateUp(): Boolean {
        val navControl = findNavController(R.id.fragment)
        return navControl.navigateUp()|| super.onSupportNavigateUp()
    }
}