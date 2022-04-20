package hu.bme.aut.f1calendar.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.f1calendar.adapter.RaceListAdapter
import hu.bme.aut.f1calendar.R
import hu.bme.aut.f1calendar.databinding.FragmentMainBinding
import hu.bme.aut.f1calendar.model.RaceListItem

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val viewModel: RaceViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getAll()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_main, container, false)

        val adapter = RaceListAdapter(ArrayList<RaceListItem>(), binding)
        binding.rvRaces.adapter = adapter
        binding.rvRaces.layoutManager = LinearLayoutManager(binding.root.context)

        viewModel.getRaceRepositoryLiveData().observe(viewLifecycleOwner) {
                t -> adapter.setRaces(t)//Log.d("Info", t.size.toString())
        }

        return binding.root
    }
}