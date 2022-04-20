package hu.bme.aut.f1calendar.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.f1calendar.model.RaceListItem
import javax.inject.Inject

@HiltViewModel
class RaceViewModel @Inject constructor(
    private var raceRepository: RaceRepository
): ViewModel() {

    fun getRaceRepositoryLiveData(): LiveData<ArrayList<RaceListItem>> {
        return raceRepository
    }

    fun getAll() {
        raceRepository.getAll(1000, 0)
    }
}