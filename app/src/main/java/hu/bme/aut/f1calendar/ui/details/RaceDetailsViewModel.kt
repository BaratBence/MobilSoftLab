package hu.bme.aut.f1calendar.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.f1calendar.model.Comment
import hu.bme.aut.f1calendar.model.Race
import javax.inject.Inject

@HiltViewModel
class RaceDetailsViewModel @Inject constructor(
    private var raceDetailsRepository: RaceDetailsRepository
): ViewModel() {

    fun getRaceRepositoryLiveData(): LiveData<Race> {
        return raceDetailsRepository
    }

    fun getDetails(season: Int, round: Int) {
        raceDetailsRepository.getDetails(season, round)
    }

    fun getComments(raceID: String) {
        raceDetailsRepository.loadComments(raceID)
    }

    fun insertComment(comment: Comment) {
        raceDetailsRepository.insertComment(comment)
    }

    fun deleteComment(comment: Comment) {
        raceDetailsRepository.deleteComment(comment)
    }
}