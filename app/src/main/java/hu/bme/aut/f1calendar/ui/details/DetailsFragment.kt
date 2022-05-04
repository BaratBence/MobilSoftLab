package hu.bme.aut.f1calendar.ui.details

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.f1calendar.R
import hu.bme.aut.f1calendar.adapter.CommentAdapter
import hu.bme.aut.f1calendar.databinding.FragmentDetailsBinding
import hu.bme.aut.f1calendar.model.Comment

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: RaceDetailsViewModel by viewModels()
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getDetails(
            DetailsFragmentArgs.fromBundle(requireArguments()).race.season,
            DetailsFragmentArgs.fromBundle(requireArguments()).race.round
        )
        firebaseAnalytics = Firebase.analytics
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)

        val adapter = CommentAdapter(ArrayList(), binding)
        binding.rvComments.adapter = adapter
        binding.rvComments.layoutManager = LinearLayoutManager(binding.root.context)

        binding.addCommentButton.setOnClickListener {
            viewModel.insertComment(Comment(comment = binding.commentEditText.text.toString(), raceID = binding.race!!.eventID))
        }

        val swipeToDeleteCallback = object : SwipeToDeleteCallback() {
            @SuppressLint("NotifyDataSetChanged")
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val pos = viewHolder.adapterPosition
                viewModel.deleteComment(adapter.findOneByPos(pos))
                adapter.notifyDataSetChanged()
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(binding.rvComments)

        viewModel.getRaceRepositoryLiveData().observe(viewLifecycleOwner) {
                t ->
            run {
                binding.race = t
                adapter.setComments(t.comments)
                Log.d("INFO","dataset changed")
            }
        }

        return binding.root
    }

}