package hu.bme.aut.f1calendar.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import hu.bme.aut.f1calendar.model.RaceListItem
import hu.bme.aut.f1calendar.network.networkObjects.Response
import hu.bme.aut.f1calendar.network.RaceService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import javax.inject.Inject
import kotlin.collections.ArrayList

class RaceRepository @Inject constructor(
    private val retrofit: Retrofit
): LiveData<ArrayList<RaceListItem>>(){

    fun getAll(limit: Int,offset: Int){
        val call = retrofit.create(RaceService::class.java).getAllRaces(limit,offset)
        val resultList = ArrayList<RaceListItem>()
        call.enqueue(object : Callback<Response> {
            override fun onResponse(call: Call<Response>, message: retrofit2.Response<Response>) {
                if (message.code() == 200) {
                    val response: Response = message.body()!!
                    resultList += response.produceRaceList()
                    val call2 = retrofit.create(RaceService::class.java).getAllRaces(limit,offset+limit)
                    call2.enqueue(object : Callback<Response> {
                        override fun onResponse(call: Call<Response>, message: retrofit2.Response<Response>) {
                            if (message.code() == 200) {
                                val response2: Response = message.body()!!
                                resultList += response2.produceRaceList()
                                resultList.reverse()
                                postValue(resultList)
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