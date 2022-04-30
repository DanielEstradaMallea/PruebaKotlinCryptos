package cl.desafiolatam.pruebafinalcoins.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import cl.desafiolatam.pruebafinalcoins.R
import cl.desafiolatam.pruebafinalcoins.adapter.CryptoAdapter
import cl.desafiolatam.pruebafinalcoins.databinding.FragmentListBinding
import cl.desafiolatam.pruebafinalcoins.model.CryptoItem
import cl.desafiolatam.pruebafinalcoins.viewModel.CryptoViewModel


class MainFragment : Fragment() {

    lateinit var b: FragmentListBinding
    private val viewmodel by activityViewModels<CryptoViewModel>()
    private val adapter = CryptoAdapter()
    lateinit var sharedPreferences: SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        b = FragmentListBinding.inflate(layoutInflater)

        setHasOptionsMenu(true)
        val layoutManager = LinearLayoutManager(requireContext())

        with(b)
        {

            sharedPreferences = context?.getSharedPreferences("archivo", AppCompatActivity.MODE_PRIVATE)!!

            rvList.adapter = adapter
            rvList.layoutManager = layoutManager

            val user = sharedPreferences.getString("usuario", "")

            if (user?.length == 0) {
                txtShowUser.text = getString(R.string.sin_registrar)
            } else {
                txtShowUser.text = user
            }

            btnAddUser.setOnClickListener {

                Navigation.findNavController(requireView()).navigate(
                    R.id.action_mainFragment_to_addUser
                )

            }
        }

        adapter.setMiListener(object : CryptoAdapter.MiListener {
            override fun miOnClick(coin: CryptoItem) {
                viewmodel.updateCrypto(coin)
                Navigation.findNavController(requireView())
                    .navigate(R.id.action_mainFragment_to_detailsFragment)
            }

        })



        viewmodel.cryptos.observe(viewLifecycleOwner, Observer {

            adapter.updateData(it)
        })

        viewmodel.filter.observe(viewLifecycleOwner) {
            adapter.updateData(viewmodel.cryptos.value!!.filter { c ->
                c.name.contains(
                    it,
                    true
                ) || c.symbol.contains(it, true)
            })
        }




        return b.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val searchItem = menu.findItem(R.id.app_search)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewmodel.filter.value = query
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText!!.isEmpty()) {
                    viewmodel.filter.value = ""
                }
                return false
            }

        })

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.cryptoteca)
    }

}