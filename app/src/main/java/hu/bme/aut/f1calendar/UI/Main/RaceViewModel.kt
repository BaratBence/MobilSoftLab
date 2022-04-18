package hu.bme.aut.f1calendar.UI.Main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RaceViewModel @Inject constructor(
    private var raceRepository: RaceRepository
): ViewModel() {
    fun getRaceRepositoryLiveData(): RaceRepository {
        return raceRepository
    }
}