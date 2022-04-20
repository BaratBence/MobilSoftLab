package hu.bme.aut.f1calendar.network

import hu.bme.aut.f1calendar.model.Race
import hu.bme.aut.f1calendar.network.networkObjects.Response
import retrofit2.Call
import retrofit2.http.*

interface RaceService {
    @GET("f1.json")
    fun getAllRaces(@Query("limit") limit:Int, @Query("offset") offset:Int): Call<Response>

    @GET("f1/{season}/{round}.json")
    fun getDetails(@Path("season") season: Int, @Path("round") round: Int): Call<Response>

    @GET("f1/{season}/{round}/results.json")
    fun getResults(@Path("season") season: Int, @Path("round") round: Int): Call<Response>

    //SHOULD NOT BE CALLED!!
    @POST("f1")
    fun createRace(@Body race: Race): Call<Race>

    @PUT("f1")
    fun updateRace(@Body race: Race): Call<Race>

    @POST("f1")
    fun deleteRace(@Body race: Race): Call<Race>
}