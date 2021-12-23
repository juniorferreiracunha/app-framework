package br.com.juniorframework.appframework.ui.todos.adapter

import android.annotation.SuppressLint
import android.text.SpannableStringBuilder
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.bold
import androidx.recyclerview.widget.RecyclerView
import br.com.juniorframework.appframework.R
import br.com.juniorframework.appframework.model.Postagens
import br.com.juniorframework.appframework.model.ToDos
import br.com.juniorframework.appframework.util.ClickListener

class ToDosViewHolder(itemView: View, val listener: ClickListener<ToDos>) :
RecyclerView.ViewHolder(itemView) {

    private var mId: TextView = itemView.findViewById(R.id.text_id)
    private var mTitle: TextView = itemView.findViewById(R.id.text_title)
    private var mComplete: ImageView = itemView.findViewById(R.id.img_complete)

    @SuppressLint("SetTextI18n")
    fun bindData(toDos: ToDos) {
        mId.text = SpannableStringBuilder()
            .bold { append("Id: ") }
            .append(toDos.id.toString())

        mTitle.text = SpannableStringBuilder()
            .bold { append("Title: ") }
            .append(toDos.title)

        if (toDos.completed) {
            mComplete.setImageResource(R.drawable.ic_check_circle_24);
        } else {
            mComplete.setImageResource(R.drawable.ic_highlight_off_24);
        }

        mComplete.setOnClickListener {
            listener.onListClick(toDos)
        }
    }
}