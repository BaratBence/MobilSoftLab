package hu.bme.aut.f1calendar.network


import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.SandwichInitializer
import hu.bme.aut.f1calendar.network.networkObjects.Response
import junit.framework.Assert.assertFalse
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class ApiResponseTest: ApiAbstract()  {


    @Test
    fun allRaces() {
        val retrofit = createService()
        enqueueResponse("AllRacesResponse.json")

        val apiResponse = ApiResponse.of(SandwichInitializer.successCodeRange) { retrofit.create(RaceService::class.java).getAllRaces(1000,0).execute() }
        if(apiResponse is ApiResponse.Success) {
            val response: Response = apiResponse.data
            //assert(response.toS)
            assert(response.MRData.RaceTable.Races.size == 1000)
            //first
            assert(response.MRData.RaceTable.Races[0].season == 1950)
            assert(response.MRData.RaceTable.Races[0].round == 1)
            assert(response.MRData.RaceTable.Races[0].raceName == "British Grand Prix")
            assert(response.MRData.RaceTable.Races[0].Circuit.circuitName == "Silverstone Circuit")
            assert(response.MRData.RaceTable.Races[0].Circuit.Location.locality == "Silverstone")
            //last
            assert(response.MRData.RaceTable.Races[999].season == 2019)
            assert(response.MRData.RaceTable.Races[999].round == 3)
            assert(response.MRData.RaceTable.Races[999].raceName == "Chinese Grand Prix")
            assert(response.MRData.RaceTable.Races[999].Circuit.circuitName == "Shanghai International Circuit")
            assert(response.MRData.RaceTable.Races[999].Circuit.Location.locality == "Shanghai")
            //transformed
            val result = response.produceRaceList()
            assert(result.size == 1000)
            assert(result[0].name == "1950 British Grand Prix")
            assert(result[0].round == 1)
            assert(result[0].season == 1950)

            assert(result[999].name == "2019 Chinese Grand Prix")
            assert(result[999].round == 3)
            assert(result[999].season == 2019)
            print(response.toString())
        }
        else assertFalse(false)
    }

    @Test
    fun oneDetails() {
        val retrofit = createService()
        enqueueResponse("oneDetailsRecent.json")

        val apiResponse = ApiResponse.of(SandwichInitializer.successCodeRange) { retrofit.create(RaceService::class.java).getDetails(2022,1).execute() }
        if(apiResponse is ApiResponse.Success) {
            val response: Response = apiResponse.data
            assert(response.MRData.RaceTable.Races.size == 1)
            assert(response.MRData.RaceTable.Races[0].season == 2022)
            assert(response.MRData.RaceTable.Races[0].round == 1)
            assert(response.MRData.RaceTable.Races[0].raceName == "Bahrain Grand Prix")
            assert(response.MRData.RaceTable.Races[0].Circuit.circuitName == "Bahrain International Circuit")
            assert(response.MRData.RaceTable.Races[0].Circuit.Location.locality == "Sakhir")
            assert(response.MRData.RaceTable.Races[0].FirstPractice!!.time == "12:00:00Z")
        }
        else assertFalse(false)
    }

    @Test
    fun oneDetails2021Test() {
        val retrofit = createService()
        enqueueResponse("oneDetails2021.json")

        val apiResponse = ApiResponse.of(SandwichInitializer.successCodeRange) { retrofit.create(RaceService::class.java).getDetails(2021,1).execute() }
        if(apiResponse is ApiResponse.Success) {
            val response: Response = apiResponse.data
            assert(response.MRData.RaceTable.Races.size == 1)
            assert(response.MRData.RaceTable.Races[0].season == 2021)
            assert(response.MRData.RaceTable.Races[0].round == 1)
            assert(response.MRData.RaceTable.Races[0].raceName == "Bahrain Grand Prix")
            assert(response.MRData.RaceTable.Races[0].Circuit.circuitName == "Bahrain International Circuit")
            assert(response.MRData.RaceTable.Races[0].Circuit.Location.locality == "Sakhir")
            assert(response.MRData.RaceTable.Races[0].FirstPractice!!.time == null)
        }
        else assertFalse(false)
    }

    @Test
    fun oneDetailsOldTest() {
        val retrofit = createService()
        enqueueResponse("oneDetailsOld.json")

        val apiResponse = ApiResponse.of(SandwichInitializer.successCodeRange) { retrofit.create(RaceService::class.java).getDetails(2011,10).execute() }
        if(apiResponse is ApiResponse.Success) {
            val response: Response = apiResponse.data
            assert(response.MRData.RaceTable.Races.size == 1)
            assert(response.MRData.RaceTable.Races[0].season == 2011)
            assert(response.MRData.RaceTable.Races[0].round == 10)
            assert(response.MRData.RaceTable.Races[0].raceName == "German Grand Prix")
            assert(response.MRData.RaceTable.Races[0].Circuit.circuitName == "Nürburgring")
            assert(response.MRData.RaceTable.Races[0].Circuit.Location.locality == "Nürburg")
            assert(response.MRData.RaceTable.Races[0].FirstPractice == null)
            assert(response.MRData.RaceTable.Races[0].Qualifying == null)
        }
        else assertFalse(false)
    }

    @Test
    fun oneDetailsWithSprint() {
        val retrofit = createService()
        enqueueResponse("oneDetailsWithSprint.json")

        val apiResponse = ApiResponse.of(SandwichInitializer.successCodeRange) { retrofit.create(RaceService::class.java).getDetails(2022,4).execute() }
        if(apiResponse is ApiResponse.Success) {
            val response: Response = apiResponse.data
            assert(response.MRData.RaceTable.Races.size == 1)
            assert(response.MRData.RaceTable.Races[0].season == 2022)
            assert(response.MRData.RaceTable.Races[0].round == 4)
            assert(response.MRData.RaceTable.Races[0].raceName == "Emilia Romagna Grand Prix")
            assert(response.MRData.RaceTable.Races[0].Circuit.circuitName == "Autodromo Enzo e Dino Ferrari")
            assert(response.MRData.RaceTable.Races[0].Circuit.Location.locality == "Imola")
            assert(response.MRData.RaceTable.Races[0].ThirdPractice == null)
            assert(response.MRData.RaceTable.Races[0].Sprint!!.time == "14:30:00Z")
        }
        else assertFalse(false)
    }

    @Test
    fun resultNew() {
        val retrofit = createService()
        enqueueResponse("resultNew.json")

        val apiResponse = ApiResponse.of(SandwichInitializer.successCodeRange) { retrofit.create(RaceService::class.java).getResults(2022,1).execute() }
        if(apiResponse is ApiResponse.Success) {
            val response: Response = apiResponse.data
            assert(response.MRData.RaceTable.Races.size == 1)
            assert(response.MRData.RaceTable.Races[0].season == 2022)
            assert(response.MRData.RaceTable.Races[0].round == 1)
            assert(response.MRData.RaceTable.Races[0].raceName == "Bahrain Grand Prix")
            assert(response.MRData.RaceTable.Races[0].Circuit.circuitName == "Bahrain International Circuit")
            assert(response.MRData.RaceTable.Races[0].Circuit.Location.locality == "Sakhir")
            assert(response.MRData.RaceTable.Races[0].FirstPractice == null)
            assert(response.MRData.RaceTable.Races[0].Results!!.size == 20)
            assert(response.MRData.RaceTable.Races[0].Results!![0].Driver.code == "LEC")
            assert(response.MRData.RaceTable.Races[0].Results!![0].Driver.familyName == "Leclerc")
        }
        else assertFalse(false)
    }

    @Test
    fun resultOld() {
        val retrofit = createService()
        enqueueResponse("resultOld.json")

        val apiResponse = ApiResponse.of(SandwichInitializer.successCodeRange) { retrofit.create(RaceService::class.java).getResults(1970,1).execute() }
        if(apiResponse is ApiResponse.Success) {
            val response: Response = apiResponse.data
            assert(response.MRData.RaceTable.Races.size == 1)
            assert(response.MRData.RaceTable.Races[0].season == 1970)
            assert(response.MRData.RaceTable.Races[0].round == 1)
            assert(response.MRData.RaceTable.Races[0].raceName == "South African Grand Prix")
            assert(response.MRData.RaceTable.Races[0].Circuit.circuitName == "Kyalami")
            assert(response.MRData.RaceTable.Races[0].Circuit.Location.locality == "Midrand")
            assert(response.MRData.RaceTable.Races[0].FirstPractice == null)
            assert(response.MRData.RaceTable.Races[0].Results!!.size == 23)
            assert(response.MRData.RaceTable.Races[0].Results!![0].Driver.code == null)
            assert(response.MRData.RaceTable.Races[0].Results!![0].Driver.familyName == "Brabham")
        }
        else assertFalse(false)
    }
}