package hu.bme.aut.f1calendar.UI.Main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import hu.bme.aut.f1calendar.R

class MainFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_main, container, false)

        val button = view.findViewById<Button>(R.id.button)
        button.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_detailsFragment)
        }

        return view
    }
}