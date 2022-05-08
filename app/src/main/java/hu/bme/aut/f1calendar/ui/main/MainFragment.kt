package hu.bme.aut.f1calendar.ui.main

import android.os.Bundle
import android.util.Log
import android.util.StatsLog.logEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.f1calendar.R
import hu.bme.aut.f1calendar.adapter.RaceListAdapter
import hu.bme.aut.f1calendar.createLog
import hu.bme.aut.f1calendar.databinding.FragmentMainBinding
import hu.bme.aut.f1calendar.model.RaceListItem


@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val viewModel: RaceViewModel by viewModels()

    //Analytics
    private lateinit var firebaseAnalytics: FirebaseAnalytics
    private var started: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getAll()
        //Analytics
        firebaseAnalytics = Firebase.analytics
        started = System.currentTimeMillis().toInt()
        firebaseAnalytics.createLog("appStarted","Created MainFragment","MainFragment", FirebaseAnalytics.Event.APP_OPEN)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_main, container, false)
        //CrashLytics
        //throw RuntimeException("Test Crash")
        val adapter = RaceListAdapter(ArrayList<RaceListItem>(), binding, firebaseAnalytics,started)
        binding.rvRaces.adapter = adapter
        binding.rvRaces.layoutManager = LinearLayoutManager(binding.root.context)

        viewModel.getRaceRepositoryLiveData().observe(viewLifecycleOwner) {
                t -> adapter.setRaces(t)
        }

        return binding.root

    }

}