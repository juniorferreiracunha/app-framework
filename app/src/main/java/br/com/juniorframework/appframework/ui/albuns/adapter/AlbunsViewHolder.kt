package br.com.juniorframework.appframework.ui.albuns.adapter

import android.annotation.SuppressLint
import android.text.SpannableStringBuilder
import android.view.View
import android.widget.TextView
import androidx.core.text.bold
import androidx.recyclerview.widget.RecyclerView
import br.com.juniorframework.appframework.R
import br.com.juniorframework.appframework.model.Albuns
import br.com.juniorframework.appframework.model.Postagens
import br.com.juniorframework.appframework.util.ClickListener

class AlbunsViewHolder(itemView: View, val listener: ClickListener<Albuns>) :
RecyclerView.ViewHolder(itemView) {

    private var mId: TextView = itemView.findViewById(R.id.text_id)
    private var mTitle: TextView = itemView.findViewById(R.id.text_title)


    @SuppressLint("SetTextI18n")
    fun bindData(albuns: Albuns) {
        mId.text = SpannableStringBuilder()
            .bold { append("Id: ") }
            .append(albuns.id.toString())

        mTitle.text = SpannableStringBuilder()
            .bold { append("Title: ") }
            .append(albuns.title)
    }
}