package hu.bme.aut.f1calendar.network.networkObjects

import android.text.format.DateFormat
import hu.bme.aut.f1calendar.model.Session
import java.util.*

data class Races (
    val season: Int,
    val round: Int,
    val raceName: String,
    val date: Date,
    val time: String,
    val Circuit: Circuit,
    val FirstPractice: Sessions?,
    val SecondPractice: Sessions?,
    val ThirdPractice: Sessions?,
    val Qualifying: Sessions?,
    val Results: List<Results>?
) {
    fun produceSession(): ArrayList<Session> {
        val resultList = ArrayList<Session>()
        if(FirstPractice?.time == null) {
            resultList.add(Session("Race", "", "NO DATA"))
            resultList.add(Session("Qualifying", "", "NO DATA"))
            resultList.add(Session("Practice 3", "", "NO DATA"))
            resultList.add(Session("Practice 2", "", "NO DATA"))
            resultList.add(Session("Practice 1", "", "NO DATA"))
            return resultList
        }
        resultList.add(Session("Race", DateFormat.format("d", date).toString(), time.take(2)+ ":00-" + (Integer.parseInt(time.take(2).toString()) + 2).toString() + ":00"))
        resultList.add(Session("Qualifying", DateFormat.format("d", Qualifying!!.date).toString(), Qualifying.createSessionTIme(1) ))
        resultList.add(Session("Practice 3", DateFormat.format("d", ThirdPractice!!.date).toString(), ThirdPractice.createSessionTIme(2) ))
        resultList.add(Session("Practice 2", DateFormat.format("d", SecondPractice!!.date).toString(), SecondPractice.createSessionTIme(2) ))
        resultList.add(Session("Practice 1", DateFormat.format("d", FirstPractice!!.date).toString(), FirstPractice.createSessionTIme(2) ))
        return resultList

    }
}