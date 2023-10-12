package com.latihan.lalabib.movi.core.di

import android.content.Context
import androidx.room.Room
import com.latihan.lalabib.movi.core.data.source.local.room.MoviDao
import com.latihan.lalabib.movi.core.data.source.local.room.MoviDatabase
import com.latihan.lalabib.movi.core.utils.SharedObject.passphrase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MoviDatabase {
        val passphrase: ByteArray = SQLiteDatabase.getBytes(passphrase.toCharArray())
        val factory = SupportFactory(passphrase)

        return Room.databaseBuilder(
            context,
            MoviDatabase::class.java, "Movi.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }

    @Provides
    fun provideMovieDao(database: MoviDatabase): MoviDao = database.movieDao()
}