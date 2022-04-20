package hu.bme.aut.f1calendar.network.networkObjects

import java.util.*

data class Sessions (
    val date: Date,
    val time: String?
    ) {
    fun createSessionTIme(length: Int): String {
        return time!!.take(2).toString() + ":00-" + (Integer.parseInt(time.take(2).toString()) + length).toString() + ":00"
    }
}