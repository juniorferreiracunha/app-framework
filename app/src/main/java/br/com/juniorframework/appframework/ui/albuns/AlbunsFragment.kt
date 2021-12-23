package br.com.juniorframework.appframework.ui.albuns

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.juniorframework.appframework.R
import br.com.juniorframework.appframework.databinding.FragmentAlbunsBinding
import br.com.juniorframework.appframework.model.Albuns
import br.com.juniorframework.appframework.model.Postagens
import br.com.juniorframework.appframework.ui.albuns.adapter.AlbunsAdapter
import br.com.juniorframework.appframework.util.ClickListener
import br.com.juniorframework.appframework.viewmodel.AlbunsViewModel

class AlbunsFragment : Fragment() {

    private var _binding: FragmentAlbunsBinding? = null
    private val binding get() = _binding!!

    private lateinit var mViewModel: AlbunsViewModel
    private val mAdapter = AlbunsAdapter()
    private lateinit var mListener: ClickListener<Albuns>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mViewModel = ViewModelProvider(this).get(AlbunsViewModel::class.java)

        _binding = FragmentAlbunsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recycler = root.findViewById<RecyclerView>(R.id.recycler_all_albuns)
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
        mViewModel.albunsList.observe(viewLifecycleOwner, Observer {
            mAdapter.updateList(it)
        })
    }

    private fun clickListener() {
        mListener = object : ClickListener<Albuns> {
            override fun onListClick(value: Albuns) {
            }

            override fun onDeleteClick(value: Albuns) {
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