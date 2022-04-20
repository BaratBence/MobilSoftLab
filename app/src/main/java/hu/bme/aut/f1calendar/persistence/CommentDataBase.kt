package hu.bme.aut.f1calendar.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import hu.bme.aut.f1calendar.model.Comment

@Database(entities = [Comment::class], version = 2, exportSchema = false)
abstract class CommentDataBase: RoomDatabase() {

    abstract fun commentDao(): CommentDao
}