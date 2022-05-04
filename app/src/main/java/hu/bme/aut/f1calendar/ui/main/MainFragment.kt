package hu.bme.aut.f1calendar.ui.main

import android.os.Bundle
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
import hu.bme.aut.f1calendar.databinding.FragmentMainBinding
import hu.bme.aut.f1calendar.model.RaceListItem


@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val viewModel: RaceViewModel by viewModels()
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getAll()
        firebaseAnalytics = Firebase.analytics
        logEvent("MainFragment","onCreate")

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_main, container, false)
        //throw RuntimeException("Test Crash")
        val adapter = RaceListAdapter(ArrayList<RaceListItem>(), binding)
        binding.rvRaces.adapter = adapter
        binding.rvRaces.layoutManager = LinearLayoutManager(binding.root.context)

        viewModel.getRaceRepositoryLiveData().observe(viewLifecycleOwner) {
                t -> adapter.setRaces(t)//Log.d("Info", t.size.toString())
        }

        return binding.root
    }

    fun logEvent(id: String,name: String ) {
        val bundle = Bundle()
        FirebaseAnalytics.Event.LOGIN
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, id)
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name)
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image")
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.LOGIN, bundle)
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)
    }
}