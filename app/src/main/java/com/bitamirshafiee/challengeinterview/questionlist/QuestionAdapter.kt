package com.bitamirshafiee.challengeinterview.questionlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bitamirshafiee.challengeinterview.R
import com.bitamirshafiee.challengeinterview.data.Question

class QuestionAdapter(private val list: List<Question?>, private val clicekd : (Unit) -> Unit) :
    RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_question, parent, false)
        return QuestionViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        list[position]?.let {
            holder.bind(it)
        }
    }

    override fun getItemCount() = list.size

    inner class QuestionViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: Question) {
            val text = view.findViewById(R.id.textView) as TextView
            text.text = item.title

            view.setOnClickListener {
                clicekd(Unit)
            }
        }
    }
}