package br.com.juniorframework.appframework.ui.postagens

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.juniorframework.appframework.R
import br.com.juniorframework.appframework.databinding.FragmentPostagensBinding
import br.com.juniorframework.appframework.model.Postagens
import br.com.juniorframework.appframework.ui.postagens.adapter.PostagemAdapter
import br.com.juniorframework.appframework.util.ClickListener
import br.com.juniorframework.appframework.viewmodel.PostagensViewModel

class PostagensFragment : Fragment() {

    private lateinit var mViewModel: PostagensViewModel
    private var _binding: FragmentPostagensBinding? = null
    private val mAdapter = PostagemAdapter()
    private lateinit var mListener: ClickListener<Postagens>
    var dialog: ProgressDialog? = null

    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mViewModel = ViewModelProvider(this).get(PostagensViewModel::class.java)

        _binding = FragmentPostagensBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recycler = root.findViewById<RecyclerView>(R.id.recycler_all_postagens)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = mAdapter

        initializer()

        return root
    }

    private fun initializer() {
        dialog = ProgressDialog(context)
        clickListener()
        observadores()
    }

    private fun clickListener() {
        mListener = object : ClickListener<Postagens> {
            override fun onListClick(value: Postagens) {
            }

            override fun onDeleteClick(value: Postagens) {
            }

        }
    }

    private fun observadores() {

        mViewModel.qtdSincronized.observe(viewLifecycleOwner, Observer {
            if (!dialog!!.isShowing) {
                carregarDialog("Carregando dados...", "")
            }

            if(it == 3) {
                mViewModel.mudarSincronizado()
                removerDialog()
                mViewModel.list()
            }
        })

        mViewModel.fail.observe(viewLifecycleOwner, Observer {
            if(it) {
                removerDialog()
                Toast.makeText(context, "Falha ao carregar os dados", Toast.LENGTH_LONG).show()
            }
        })

        mViewModel.postagemList.observe(viewLifecycleOwner, Observer {
            mAdapter.updateList(it)
        })
    }

    fun carregarDialog(titulo: String?, message: String?) {
        dialog!!.setTitle(titulo)
        dialog!!.setMessage(message)
        dialog!!.setCancelable(false)
        dialog!!.show()
    }

    fun removerDialog() {
        if (dialog!!.isShowing) dialog?.dismiss()
    }

    override fun onResume() {
        super.onResume()
        mAdapter.attachListener(mListener)
        mViewModel.initSincronized()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}