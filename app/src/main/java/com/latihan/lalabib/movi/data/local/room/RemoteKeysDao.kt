package com.latihan.lalabib.movi.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.latihan.lalabib.movi.data.local.entity.RemoteKeys

@Dao
interface RemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RemoteKeys>)

    @Query("Select * from remote_keys where id = :id")
    suspend fun getRemoteKeysId(id: String): RemoteKeys?

    @Query("Delete from remote_keys")
    suspend fun deleteRemoteKeys()
}