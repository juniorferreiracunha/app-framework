package br.com.juniorframework.appframework.ui.todos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.juniorframework.appframework.R
import br.com.juniorframework.appframework.databinding.FragmentTodosBinding
import br.com.juniorframework.appframework.model.ToDos
import br.com.juniorframework.appframework.ui.todos.adapter.ToDosAdapter
import br.com.juniorframework.appframework.util.ClickListener
import br.com.juniorframework.appframework.viewmodel.ToDosViewModel

class ToDosFragment : Fragment() {

    private var _binding: FragmentTodosBinding? = null
    private val binding get() = _binding!!

    private lateinit var mViewModel: ToDosViewModel
    private val mAdapter = ToDosAdapter()
    private lateinit var mListener: ClickListener<ToDos>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mViewModel = ViewModelProvider(this).get(ToDosViewModel::class.java)

        _binding = FragmentTodosBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recycler = root.findViewById<RecyclerView>(R.id.recycler_all_todos)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = mAdapter

        initializer()

        return root
    }

    private fun initializer() {
        clickListener()
        observadores()
    }

    private fun observadores() {
        mViewModel.todosList.observe(viewLifecycleOwner, Observer {
            mAdapter.updateList(it)
        })
    }

    private fun clickListener() {
        mListener = object : ClickListener<ToDos> {
            override fun onListClick(value: ToDos) {
                mViewModel.updateCompleted(value.id, value.completed)
            }

            override fun onDeleteClick(value: ToDos) {
            }

        }
    }

    override fun onResume() {
        super.onResume()
        mAdapter.attachListener(mListener)
        mViewModel.list()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}