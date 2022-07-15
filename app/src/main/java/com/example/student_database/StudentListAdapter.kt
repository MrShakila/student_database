package com.example.student_database

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.common.data.DataHolder
import kotlinx.coroutines.NonDisposableHandle.parent
import java.text.ParsePosition

class StudentListAdapter :ListAdapter<StudentData, StudentListAdapter.StudentViewHolder>(StudentCompartor()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        return StudentViewHolder.create(parent)
    }
    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.id.toString())
    }
    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val wordItemView: TextView = itemView.findViewById(R.id.textView)

        fun bind(text: String?) {
            wordItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): StudentViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyleerview_item, parent, false)
                return StudentViewHolder(view)
            }
        }
    }

    class StudentCompartor : DiffUtil.ItemCallback<StudentData>() {
        override fun areItemsTheSame(oldItem:StudentData, newItem: StudentData): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: StudentData, newItem: StudentData): Boolean {
            return oldItem.id == newItem.id
        }
    }
}