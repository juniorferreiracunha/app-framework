package br.com.juniorframework.appframework.ui.postagens.adapter

import android.annotation.SuppressLint
import android.text.SpannableStringBuilder
import android.view.View
import android.widget.TextView
import androidx.core.text.bold
import androidx.recyclerview.widget.RecyclerView
import br.com.juniorframework.appframework.R
import br.com.juniorframework.appframework.model.Postagens
import br.com.juniorframework.appframework.util.ClickListener

class PostagemViewHolder(itemView: View, val listener: ClickListener<Postagens>) :
RecyclerView.ViewHolder(itemView) {

    private var mId: TextView = itemView.findViewById(R.id.text_id)
    private var mTitle: TextView = itemView.findViewById(R.id.text_title)
    private var mBody: TextView = itemView.findViewById(R.id.text_body)


    @SuppressLint("SetTextI18n")
    fun bindData(postagens: Postagens) {
        mId.text = SpannableStringBuilder()
            .bold { append("Id: ") }
            .append(postagens.id.toString())

        mTitle.text = SpannableStringBuilder()
            .bold { append("Title: ") }
            .append(postagens.title)

        mBody.text = SpannableStringBuilder()
            .bold { append("Body: ") }
            .append(postagens.body)
    }
}