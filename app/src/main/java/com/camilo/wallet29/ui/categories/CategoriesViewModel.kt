package com.camilo.wallet29.ui.categories

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.camilo.wallet29.R
import com.camilo.wallet29.interfaces.ViewModel
import com.camilo.wallet29.local_data.entity.CategoryEntity
import com.camilo.wallet29.repository.CategoryRepository
import kotlinx.coroutines.launch

class CategoriesViewModel(application: Application) :
    ViewModel<CategoryRepository, CategoryEntity>(application) {

    private val app = application
    override lateinit var repository: CategoryRepository

    val categories: LiveData<List<CategoryEntity?>> by lazy { repository.getAllData() }

    override fun insert(entity: CategoryEntity) {
        viewModelScope.launch {
            repository.insert(entity)
        }
    }

    override fun update(entity: CategoryEntity) {
        viewModelScope.launch {
            repository.update(entity)
        }
    }

    override fun delete(entity: CategoryEntity) {
        viewModelScope.launch {
            repository.delete(entity)
        }
    }

    fun populateCategories() {
        val categoryTransport = CategoryEntity(
            id = 0,
            name = app.getString(R.string.category_transport),
            canModify = false,
            color = -688361,
            iconId = 381
        )

        val categoryFamily = CategoryEntity(
            id = 0,
            name = app.getString(R.string.category_family),
            canModify = false,
            color = -688361,
            iconId = 2
        )

        val categoryVehicle = CategoryEntity(
            id = 0,
            name = app.getString(R.string.category_vehicle),
            canModify = false,
            color = -688361,
            iconId = 391
        )

        val categoryShop = CategoryEntity(
            id = 0,
            name = app.getString(R.string.category_shop),
            canModify = false,
            color = -688361,
            iconId = 255
        )
        viewModelScope.launch {
            repository.populateCategories(categoryTransport, categoryFamily, categoryVehicle, categoryShop)
        }

    }
}