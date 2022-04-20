package hu.bme.aut.f1calendar.ui.details

import android.util.Log
import androidx.lifecycle.LiveData
import hu.bme.aut.f1calendar.network.networkObjects.Response
import hu.bme.aut.f1calendar.network.RaceService
import hu.bme.aut.f1calendar.model.Comment
import hu.bme.aut.f1calendar.persistence.CommentDao
import hu.bme.aut.f1calendar.model.Race
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import javax.inject.Inject

class RaceDetailsRepository @Inject constructor(private val commentDao: CommentDao, private val retrofit: Retrofit): LiveData<Race>() {

    fun loadComments(raceID: String) = runBlocking(Dispatchers.IO){
        val comments = commentDao.getCommentsByRaceID(raceID) as ArrayList<Comment>
        comments.reverse()
        value!!.comments = comments
        postValue(value)
    }

    fun raceComments(raceID: String): ArrayList<Comment> = runBlocking(Dispatchers.IO){
        commentDao.getCommentsByRaceID(raceID) as ArrayList<Comment>
    }

    fun insertComment(comment: Comment) = runBlocking(Dispatchers.IO) {
        commentDao.insertComment(comment)
        loadComments(comment.raceID)
    }

    fun deleteComment(comment: Comment) = runBlocking(Dispatchers.IO) {
        commentDao.deleteComment(comment)
        loadComments(comment.raceID)
    }

    fun getDetails(season: Int, round: Int) {
        val call = retrofit.create(RaceService::class.java).getDetails(season, round)
        call.enqueue(object : Callback<Response> {
            override fun onResponse(call: Call<Response>, message: retrofit2.Response<Response>) {
                if (message.code() == 200) {
                    val response: Response = message.body()!!
                    val race = response.produceDetails()
                    val callResults = retrofit.create(RaceService::class.java).getResults(season,round)
                    callResults.enqueue(object : Callback<Response> {
                        override fun onResponse(call: Call<Response>, message: retrofit2.Response<Response>) {
                            if (message.code() == 200) {
                                val responseResult: Response = message.body()!!
                                val raceDetails = responseResult.produceDetails()
                                race.first = raceDetails.first
                                race.second = raceDetails.second
                                race.third = raceDetails.third
                                race.comments = raceComments(race.eventID)
                                postValue(race)
                            }
                        }
                        override fun onFailure(call: Call<Response>, t: Throwable) {
                            Log.d("INFO", t.message.toString())
                        }
                    })
                }
            }
            override fun onFailure(call: Call<Response>, t: Throwable) {
                Log.d("INFO", t.message.toString())
            }
        })
    }
}