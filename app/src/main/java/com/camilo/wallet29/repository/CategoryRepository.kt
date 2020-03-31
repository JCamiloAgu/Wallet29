package com.camilo.wallet29.repository

import androidx.lifecycle.LiveData
import com.camilo.wallet29.R
import com.camilo.wallet29.interfaces.Repository
import com.camilo.wallet29.local_data.dao.CategoryDao
import com.camilo.wallet29.local_data.entity.CategoryEntity

class CategoryRepository(override val dao: CategoryDao) :
    Repository<CategoryDao, CategoryEntity> {

    override fun getAllData(): LiveData<List<CategoryEntity?>> = dao.getAllData()

    override suspend fun insert(entity: CategoryEntity) {
        dao.insert(entity)
    }

    override suspend fun update(entity: CategoryEntity) {
        dao.update(entity)
    }

    override suspend fun delete(entity: CategoryEntity) {
        dao.delete(entity)
    }

    suspend fun populateCategories(vararg categoryEntity: CategoryEntity){
        categoryEntity.forEach {
            insert(it)
        }

    }


}