package hu.bme.aut.f1calendar.adapter


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.f1calendar.model.RaceListItem
import hu.bme.aut.f1calendar.ui.main.MainFragmentDirections
import hu.bme.aut.f1calendar.databinding.FragmentMainBinding
import hu.bme.aut.f1calendar.databinding.RacelistRowBinding
import hu.bme.aut.f1calendar.model.TransferObject

class RaceListAdapter(private var races: ArrayList<RaceListItem>, private val fragmentMainBinding: FragmentMainBinding):
    RecyclerView.Adapter<RaceListAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: RacelistRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(race: RaceListItem) {
            binding.race = race
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = RacelistRowBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(races[position])
        viewHolder.binding.race?.name = races[position].name
        viewHolder.binding.detailsButton.setOnClickListener {
            val transferObject = TransferObject(races[position].season, races[position].round)
            val action = MainFragmentDirections.actionMainFragmentToDetailsFragment(transferObject)
            Navigation.findNavController(fragmentMainBinding.root).navigate(action)
        }

    }
    override fun getItemCount() = races.size

    @SuppressLint("NotifyDataSetChanged")
    fun setRaces(races: ArrayList<RaceListItem>) {
        this.races = races
        notifyDataSetChanged()
    }

}