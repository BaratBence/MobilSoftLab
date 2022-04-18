package hu.bme.aut.f1calendar.network

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import hu.bme.aut.f1calendar.Persistence.CommentDataBase

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
abstract class CommentDB {

    lateinit var db: CommentDataBase

    @Before
    fun initDB() {
        db = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), CommentDataBase::class.java)
            .allowMainThreadQueries()
            .build()
    }

    @After
    fun closeDB() {
        db.close()
    }
}