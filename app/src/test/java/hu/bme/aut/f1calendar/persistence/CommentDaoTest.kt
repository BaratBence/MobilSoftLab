package hu.bme.aut.f1calendar.persistence

import hu.bme.aut.f1calendar.model.Comment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class CommentDaoTest: CommentDB() {

    private lateinit var commentDao: CommentDao
    private val comment = Comment.mock()

    @Before
    fun init() {
        commentDao = db.commentDao()
    }

    @Test
    fun insertAndGetTest() = runBlocking(Dispatchers.IO) {
            commentDao.insertComment(comment)
            val result = commentDao.getCommentsByRaceID(comment.raceID)
            assert(result.size == 1)
            assert(result[0].raceID == comment.raceID)
            assert(result[0].id == comment.id)
            assert(result[0].comment == comment.comment)

    }
    @Test
    fun delete() = runBlocking {
        commentDao.deleteComment(comment)
        val result = commentDao.getCommentsByRaceID(comment.raceID)
        assert(result.isEmpty())
    }
}