package hu.bme.aut.f1calendar.UI.Main

import androidx.lifecycle.LiveData
import hu.bme.aut.f1calendar.Model.Comment
import hu.bme.aut.f1calendar.Persistence.CommentDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class RaceRepository @Inject constructor(
    private val commentDao: CommentDao
): LiveData<List<Comment>>(){


    fun loadComments(raceID: String): List<Comment> = runBlocking(Dispatchers.IO) {
        postValue(commentDao.getCommentsByRaceID(raceID))
        commentDao.getCommentsByRaceID(raceID)
    }

    fun insertComment(comment: Comment) = runBlocking(Dispatchers.IO) {
        commentDao.insertComment(comment)
    }

    fun deleteComment(comment: Comment) = runBlocking(Dispatchers.IO) {
        commentDao.deleteComment(comment)
    }

}