package br.com.juniorframework.appframework.ui.albuns.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.juniorframework.appframework.R
import br.com.juniorframework.appframework.model.Albuns
import br.com.juniorframework.appframework.model.Postagens
import br.com.juniorframework.appframework.util.ClickListener

class AlbunsAdapter: RecyclerView.Adapter<AlbunsViewHolder>() {

    private var mList: List<Albuns> = arrayListOf()
    private lateinit var mListener: ClickListener<Albuns>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbunsViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.list_albuns, parent, false)
        return AlbunsViewHolder(item, mListener)
    }

    override fun onBindViewHolder(holder: AlbunsViewHolder, position: Int) {
        holder.bindData(mList[position])
    }

    override fun getItemCount(): Int {
        return mList.count()
    }

    fun attachListener(listener: ClickListener<Albuns>) {
        mListener = listener
    }

    fun updateList(list: List<Albuns>) {
        mList = list
        notifyDataSetChanged()
    }
}