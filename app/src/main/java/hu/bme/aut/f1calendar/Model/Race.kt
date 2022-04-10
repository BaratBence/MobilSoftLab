package hu.bme.aut.f1calendar.Model

data class Race(
    val name: String,
    val season: Int,
    val round: Int,
    val first: String?,
    val second: String?,
    val third: String?,
    val sessions: ArrayList<Session> = ArrayList(),
    val comments: ArrayList<Comment> = ArrayList()
)