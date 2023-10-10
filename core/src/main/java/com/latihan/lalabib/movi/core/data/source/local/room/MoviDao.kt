package com.latihan.lalabib.movi.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.latihan.lalabib.movi.core.data.source.local.entity.MoviesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: List<MoviesEntity>)

    @Query("Select * from movie_entities")
    fun getAllMovie(): Flow<List<MoviesEntity>>

    @Query("Select * from movie_entities where title like '%' || :query || '%'")
    fun searchMovie(query: String): Flow<List<MoviesEntity>>

    @Query("Select * from movie_entities where id = :id")
    fun getMovieById(id: String): Flow<MoviesEntity>

    @Update
    suspend fun updateMovie(movie: MoviesEntity)

    @Query("Select * from movie_entities where isFavorite = 1")
    fun getFavoriteMovie(): Flow<List<MoviesEntity>>
}