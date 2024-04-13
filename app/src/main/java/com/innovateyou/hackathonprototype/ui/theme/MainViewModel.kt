package com.innovateyou.hackathonprototype.ui.theme



import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel



class MainViewModel : ViewModel() {
    var selectedScreen = mutableStateOf("Inventory")
    var sortingOption by mutableStateOf(SortingOption.NAME)
    var searchQuery: MutableState<String> = mutableStateOf("")



}


enum class SortingOption {
    NAME,
    QUANTITY,
    EXPIRY_DATE,
    MANUFACTURER
}


