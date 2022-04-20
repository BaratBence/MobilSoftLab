package hu.bme.aut.f1calendar.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Comment(
    @PrimaryKey(autoGenerate = true)val id: Long = 0,
    var comment: String,
    var raceID: String
) {
    companion object {
        fun mock() = Comment(
            id = 1,
            comment = "test comment",
            raceID = "3022 Hungarian Grand Prix"
        )
    }
}