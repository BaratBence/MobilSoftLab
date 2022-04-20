package hu.bme.aut.f1calendar.network.networkObjects

import android.text.format.DateFormat
import hu.bme.aut.f1calendar.model.Comment
import hu.bme.aut.f1calendar.model.Race
import hu.bme.aut.f1calendar.model.RaceListItem
import hu.bme.aut.f1calendar.model.Session

class Response(
    val MRData: MRData
) {
        fun produceRaceList(): ArrayList<RaceListItem> {
            val resultList = ArrayList<RaceListItem>()
            MRData.RaceTable
            for(race in MRData.RaceTable.Races) {
                resultList.add(RaceListItem(race.season.toString() + " " + race.raceName, race.season, race.round))
            }
            return resultList
        }

        fun produceDetails(): Race {
            if(MRData.RaceTable.Races.isNotEmpty()) {
                val race = MRData.RaceTable.Races[0]
                return Race(
                    ID = race.season.toString() + " " + race.raceName,
                    locality = race.Circuit.Location.locality,
                    raceName = race.raceName,
                    circuitName = race.Circuit.circuitName,
                    eventID = race.season.toString() + " season round " + race.round.toString() + " " + DateFormat.format(
                        "MMMM",
                        race.date
                    ),
                    first = if (race.Results == null) "TBD" else race.Results[0].Driver.code,
                    second = if (race.Results == null) "TBD" else race.Results[1].Driver.code,
                    third = if (race.Results == null) "TBD" else race.Results[2].Driver.code,
                    sessions = race.produceSession(),
                    comments = ArrayList<Comment>()
                )
            }
            else return Race(
                ID = "0",
                locality = "NoWhere",
                raceName = "NoName",
                circuitName = "NoCircuit",
                eventID = "NoID",
                first = "TBD",
                second = "TBD",
                third = "TBD",
                sessions = ArrayList<Session>(),
                comments = ArrayList<Comment>()
            )
        }

    companion object {
        fun mock(): Response {
            return  Response(MRData(RaceTable(listOf(Races()))))
        }
    }

}