package com.innovateyou.hackathonprototype.ui.theme

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.firestore.FirebaseFirestore
import com.innovateyou.hackathonprototype.data.Medicine



@Composable
fun Inventory(padding:PaddingValues,navController: NavController,viewModel: MainViewModel){


    dismissScreen()
    val medicines = remember {
        mutableStateOf(emptyList<Medicine>())

    }

    LaunchedEffect(Unit) {
        val db = FirebaseFirestore.getInstance()
        db.collection("medicines")
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    Log.w("Inventory", "Error fetching medicines: $error")
                    return@addSnapshotListener
                }
                if (snapshot != null) {
                        val newMedicines = snapshot.mapNotNull { document ->
                            try {
                                Medicine(
                                    id = document.id, // Add ID if needed
                                    name = document["name"] as? String ?: "",
                                    quantity = (document["quantity"] as? Long)?.toInt() ?: 0,
                                    expiryDate = document["expiryDate"] as? String ?: "",
                                    manufacturer = document["manufacturer"] as? String ?: "",
                                    minStockLimit = (document["minStockLimit"] as? Long)?.toInt() ?: 0,
                                    price = document["price"] as? Float ?: 0.0f,
                                    type = document["type"] as? String ?: ""
                                )
                            } catch (e: Exception) {
                                Log.e(TAG, "Error converting document to Medicine", e)
                                null
                            }
                        }
                    medicines.value= newMedicines
                }
            }
    }
    val sortedMedicines = remember(medicines.value, viewModel.sortingOption, viewModel.searchQuery.value) {
        val filteredMedicines = if (viewModel.searchQuery.value.isNotBlank()) {
            medicines.value.filter { it.name.contains(viewModel.searchQuery.value, ignoreCase = true) }
        } else {
            medicines.value
        }

        when (viewModel.sortingOption) {
            SortingOption.NAME -> filteredMedicines.sortedBy { it.name }
            SortingOption.QUANTITY -> filteredMedicines.sortedByDescending { it.quantity }
            SortingOption.EXPIRY_DATE -> filteredMedicines.sortedBy { it.expiryDate }
            SortingOption.MANUFACTURER -> filteredMedicines.sortedBy { it.manufacturer }
        }
    }
        Column (modifier = Modifier.padding(padding)){
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(sortedMedicines) { medicine ->
                    InventoryCard(
                        name = medicine.name,
                        quantity = medicine.quantity,
                        expDate = medicine.expiryDate,
                        type = medicine.type,
                        minStockLimit = medicine.minStockLimit,
                        manufacturer = medicine.manufacturer
                    ){
                        navController.navigate("DetailsActivity/${medicine.id}")
                    }



                }
            }

        }
}


