package br.com.juniorframework.appframework.ui.todos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.juniorframework.appframework.R
import br.com.juniorframework.appframework.model.ToDos
import br.com.juniorframework.appframework.util.ClickListener

class ToDosAdapter: RecyclerView.Adapter<ToDosViewHolder>() {

    private var mList: List<ToDos> = arrayListOf()
    private lateinit var mListener: ClickListener<ToDos>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDosViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.list_todos, parent, false)
        return ToDosViewHolder(item, mListener)
    }

    override fun onBindViewHolder(holder: ToDosViewHolder, position: Int) {
        holder.bindData(mList[position])
    }

    override fun getItemCount(): Int {
        return mList.count()
    }

    fun attachListener(listener: ClickListener<ToDos>) {
        mListener = listener
    }

    fun updateList(list: List<ToDos>) {
        mList = list
        notifyDataSetChanged()
    }
}