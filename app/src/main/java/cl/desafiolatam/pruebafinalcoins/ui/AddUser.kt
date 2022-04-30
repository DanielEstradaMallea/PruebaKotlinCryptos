package cl.desafiolatam.pruebafinalcoins.ui

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import cl.desafiolatam.pruebafinalcoins.R
import cl.desafiolatam.pruebafinalcoins.databinding.FragmentAddUserBinding


class AddUser : Fragment() {

    lateinit var b: FragmentAddUserBinding
    lateinit var sharedPreferences: SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        b = FragmentAddUserBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        with(b) {
            sharedPreferences =
                context?.getSharedPreferences("archivo", AppCompatActivity.MODE_PRIVATE)!!

            btnGuardar.setOnClickListener {
                val msg = inputAddUser.text.toString()


                if (msg.isEmpty()) {
                    inputAddUser.error = getString(R.string.campo_obligatorio)

                    inputAddUser.requestFocus()
                } else {
                    sharedPreferences.edit().putString("usuario", msg).commit()

                    Navigation.findNavController(requireView())
                        .navigate(R.id.action_addUser_to_mainFragment)
                }

            }
        }





        return b.root
    }

}