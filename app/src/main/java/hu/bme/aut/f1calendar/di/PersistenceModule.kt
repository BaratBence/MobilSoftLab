package hu.bme.aut.f1calendar.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.f1calendar.persistence.CommentDao
import javax.inject.Singleton
import hu.bme.aut.f1calendar.persistence.CommentDataBase

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): CommentDataBase {
        return Room.databaseBuilder(
                application,
                CommentDataBase::class.java,
                "comments.db"
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providePosterDao(commentDataBase: CommentDataBase): CommentDao {
        return commentDataBase.commentDao()
    }
}