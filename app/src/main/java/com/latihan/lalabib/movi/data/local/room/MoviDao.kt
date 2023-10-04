package com.latihan.lalabib.movi.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.latihan.lalabib.movi.data.local.entity.MoviesEntity

@Dao
interface MoviDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: List<MoviesEntity>)

    @Query("Select * from movie_entities")
    fun getAllMovie(): LiveData<List<MoviesEntity>>

    @Query("Select * from movie_entities where id = :id")
    fun getDetailMovie(id: String): LiveData<MoviesEntity>

    @Update
    fun updateMovie(movie: MoviesEntity)

    @Query("Select * from movie_entities where isFavorite = 1")
    fun getFavoriteMovie(): LiveData<List<MoviesEntity>>

    @Query("Delete from movie_entities")
    suspend fun deleteMovie()
}