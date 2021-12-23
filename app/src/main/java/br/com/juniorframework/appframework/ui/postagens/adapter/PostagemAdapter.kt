package br.com.juniorframework.appframework.ui.postagens.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.juniorframework.appframework.R
import br.com.juniorframework.appframework.model.Postagens
import br.com.juniorframework.appframework.util.ClickListener

class PostagemAdapter: RecyclerView.Adapter<PostagemViewHolder>() {

    private var mList: List<Postagens> = arrayListOf()
    private lateinit var mListener: ClickListener<Postagens>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostagemViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.list_postagens, parent, false)
        return PostagemViewHolder(item, mListener)
    }

    override fun onBindViewHolder(holder: PostagemViewHolder, position: Int) {
        holder.bindData(mList[position])
    }

    override fun getItemCount(): Int {
        return mList.count()
    }

    fun attachListener(listener: ClickListener<Postagens>) {
        mListener = listener
    }

    fun updateList(list: List<Postagens>) {
        mList = list
        notifyDataSetChanged()
    }
}