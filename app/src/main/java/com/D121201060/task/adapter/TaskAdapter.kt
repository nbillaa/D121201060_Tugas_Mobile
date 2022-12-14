package com.D121201060.task.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.compose.animation.fadeIn
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.D121201060.task.R
import com.D121201060.task.fragments.HomeFragmentDirections
import com.D121201060.task.model.Task
import com.D121201060.task.viewmodel.TugasViewModel

class TaskAdapter:RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    private var context :Context ?= null
    private var list_task = emptyList<Task>()
    private lateinit var tugasViewModel :TugasViewModel
    class TaskViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val judul_tugas:TextView = itemView.findViewById(R.id.judul_tugas)
        val isi_tugas:TextView = itemView.findViewById(R.id.deskripsi_tugas)
        val kategori_tugas:TextView = itemView.findViewById(R.id.kategori)
        val popup_tugas:ImageView = itemView.findViewById(R.id.popup_tugas)
        val klik_tugas:CardView = itemView.findViewById(R.id.klikTugas)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        context = parent.context
        tugasViewModel = ViewModelProvider(context as FragmentActivity)[TugasViewModel::class.java]
        return TaskViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.task_adapter,parent,false))
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentTask = list_task[position]
        holder.judul_tugas.text = currentTask.nama_tugas
        holder.isi_tugas.text = currentTask.deskripsi_tugas
        holder.kategori_tugas.text = currentTask.kategori_tugas
        when (currentTask.kategori_tugas) {
            context!!.resources.getStringArray(R.array.kategoriArray)[0] -> {
                holder.kategori_tugas.backgroundTintList = ColorStateList.valueOf(context!!.resources.getColor(R.color.penting_mendesak))
                holder.popup_tugas.imageTintList = ColorStateList.valueOf(context!!.resources.getColor(R.color.penting_mendesak))
            }
            context!!.resources.getStringArray(R.array.kategoriArray)[1] -> {
                holder.kategori_tugas.backgroundTintList = ColorStateList.valueOf(context!!.resources.getColor(R.color.tidak_penting_mendesak))
                holder.popup_tugas.imageTintList = ColorStateList.valueOf(context!!.resources.getColor(R.color.tidak_penting_mendesak))
            }
            context!!.resources.getStringArray(R.array.kategoriArray)[2] -> {
                holder.kategori_tugas.backgroundTintList = ColorStateList.valueOf(context!!.resources.getColor(R.color.penting_tidak_mendesak))
                holder.popup_tugas.imageTintList = ColorStateList.valueOf(context!!.resources.getColor(R.color.penting_tidak_mendesak))
            }
            context!!.resources.getStringArray(R.array.kategoriArray)[3] -> {
                holder.kategori_tugas.backgroundTintList = ColorStateList.valueOf(context!!.resources.getColor(R.color.tidak_penting_tidak_mendesak))
                holder.popup_tugas.imageTintList = ColorStateList.valueOf(context!!.resources.getColor(R.color.tidak_penting_tidak_mendesak))
            }
        }
        holder.popup_tugas.setOnClickListener {
            val popupMenus = PopupMenu(context,it)
            popupMenus.inflate(R.menu.menu_pop)
            popupMenus.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.edit_tugas->{
                        val action = HomeFragmentDirections.actionHomeFragmentToEditFragment(list_task[position])
                        holder.itemView.findNavController().navigate(action)
                        true
                    }
                    R.id.hapus_tugas->{
                        tugasViewModel.deleteTask(list_task[position])
                        Toast.makeText(context,"Berhasil dihapus",Toast.LENGTH_SHORT).show()
                        true
                    }
                    else->true
                }
            }
            popupMenus.show()
            val popup = PopupMenu::class.java.getDeclaredField("mPopup")
            popup.isAccessible = true
            val menu = popup.get(popupMenus)
            menu.javaClass.getDeclaredMethod("setForceShowIcon",Boolean::class.java).invoke(menu,true)
        }
        holder.klik_tugas.setOnClickListener {
            holder.itemView.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(list_task[position]))
        }
    }

    override fun getItemCount(): Int {
        return list_task.size
    }
    fun setData(task:List<Task>){
        this.list_task = task
        notifyDataSetChanged()
    }
}