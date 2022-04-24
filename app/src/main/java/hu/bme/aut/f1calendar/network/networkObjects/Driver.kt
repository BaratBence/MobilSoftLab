package hu.bme.aut.f1calendar.network.networkObjects

import java.util.*

data class Driver(
    val code: String?,
    val familyName: String
) {
    fun produceDriver(): String {
        return code ?: familyName.take(3).uppercase(Locale.getDefault())
    }
}