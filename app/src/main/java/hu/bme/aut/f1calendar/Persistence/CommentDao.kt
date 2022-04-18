package hu.bme.aut.f1calendar.Persistence

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import hu.bme.aut.f1calendar.Model.Comment

@Dao
interface CommentDao {
    @Insert
    suspend fun insertComment(comment: Comment)

    @Query("SELECT * FROM comment WHERE raceID = :raceID" )
    suspend fun getCommentsByRaceID(raceID: String): List<Comment>

    @Delete
    suspend fun deleteComment(comment: Comment)
}