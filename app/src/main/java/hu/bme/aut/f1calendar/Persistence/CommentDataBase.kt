package hu.bme.aut.f1calendar.Persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import hu.bme.aut.f1calendar.Model.Comment

@Database(entities = [Comment::class], version = 2, exportSchema = false)
abstract class CommentDataBase: RoomDatabase() {

    abstract fun commentDao(): CommentDao
}