package com.example.appcrud

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.appcrud.data.AppDatabase
import com.example.appcrud.data.ItemRepository
import com.example.appcrud.ui.theme.ItemScreen
import com.example.appcrud.viewmodel.ItemViewModel
import com.example.appcrud.viewmodel.ItemViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "item-database"
        ).build()
        val repository = ItemRepository(db.itemDao())
        val viewModel = ViewModelProvider(this, ItemViewModelFactory(repository))
            .get(ItemViewModel::class.java)

        setContent {
            ItemScreen(viewModel = viewModel)
        }
    }
}
