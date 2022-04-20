package hu.bme.aut.f1calendar.model

data class Race(
    val ID: String,
    val locality: String,
    val raceName: String,
    val circuitName: String,
    val eventID: String,
    var first: String?,
    var second: String?,
    var third: String?,
    val sessions: ArrayList<Session> = ArrayList(),
    var comments: ArrayList<Comment> = ArrayList()
)