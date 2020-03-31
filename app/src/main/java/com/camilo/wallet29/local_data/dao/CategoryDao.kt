package com.camilo.wallet29.local_data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.camilo.wallet29.local_data.entity.CategoryEntity

@Dao
interface CategoryDao {

    @Query("SELECT * FROM categories")
    fun getAllData(): LiveData<List<CategoryEntity?>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(categoryEntity: CategoryEntity)

    @Delete
    suspend fun delete(categoryEntity: CategoryEntity)

    @Update
    suspend fun update(categoryEntity: CategoryEntity)
}