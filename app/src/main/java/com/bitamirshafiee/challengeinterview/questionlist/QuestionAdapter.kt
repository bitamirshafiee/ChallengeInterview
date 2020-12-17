package com.bitamirshafiee.challengeinterview.questionlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bitamirshafiee.challengeinterview.R
import com.bitamirshafiee.challengeinterview.data.Question
import com.bitamirshafiee.challengeinterview.databinding.ItemQuestionBinding

class QuestionAdapter(private val list: List<Question?>, private val clicekd : (Unit) -> Unit) :
    RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>() {

    private lateinit var binding : ItemQuestionBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {

        val context = parent.context
        binding= ItemQuestionBinding.inflate(LayoutInflater.from(context), parent, false)
        return QuestionViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        list[position]?.let {
            holder.bind(it)
        }
    }

    override fun getItemCount() = list.size

    inner class QuestionViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: Question) {
            binding.textView.text = item.title

            view.setOnClickListener {
                clicekd(Unit)
            }
        }
    }
}