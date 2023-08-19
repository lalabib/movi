package com.latihan.lalabib.movi.data.local.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.latihan.lalabib.movi.data.local.entity.MoviesEntity

@Dao
interface MoviDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MoviesEntity)

    @Query("Select * from movie_entities")
    fun getAllMovie(): PagingSource<Int, MoviesEntity>

    @Query("Delete from movie_entities")
    suspend fun deleteMovie()
}