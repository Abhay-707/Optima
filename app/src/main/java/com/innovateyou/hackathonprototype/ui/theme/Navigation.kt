    package com.innovateyou.hackathonprototype.ui.theme


    import android.content.ContentValues
    import android.util.Log
    import androidx.compose.runtime.Composable
    import androidx.navigation.NavHostController
    import androidx.navigation.compose.NavHost
    import androidx.navigation.compose.composable
    import androidx.navigation.compose.rememberNavController
    import com.google.firebase.firestore.FirebaseFirestore
    import com.innovateyou.hackathonprototype.data.Medicine
    import com.innovateyou.hackathonprototype.data.Screen


    @Composable
    fun Navigation(navController: NavHostController = rememberNavController(), viewModel: MainViewModel){



        NavHost(navController = navController,
            startDestination = Screen.LoginPage.route){
            composable(Screen.LoginPage.route){
                LoginScreen(navController = navController)
            }

            composable(Screen.HomeScreen.route){
                HomeScreen(viewModel = viewModel,navController)
            }
            composable("DetailsActivity/{medicineId}") { backStackEntry ->
                val medicineId = backStackEntry.arguments?.getString("medicineId")
                DetailsActivity(
                    medicineId = medicineId,
                    onUpdate = { updatedMedicine ->
                        updateMedicine(updatedMedicine)
                        navController.navigate(Screen.HomeScreen.route)
                    },
                    onDelete = {
                        medicineId?.let { id ->
                            deleteMedicine(id)
                            navController.navigate(Screen.HomeScreen.route)
                        }
                    }
                )
            }

            composable(Screen.SeeAllRequests.route){
                seeAllRequests(viewModel)
            }

            composable(Screen.EmergencyRequest.route){
                EmergencyRequest(viewModel,{})
            }

            composable(Screen.RequestMedicineRequests.route){
                RequestMedicineRequests(viewModel)
            }



        }


    }
    fun updateMedicine(medicine: Medicine) {
        val firestore = FirebaseFirestore.getInstance()
        val medicineId = medicine.id
        val medicinesRef = firestore.collection("medicines")
        medicinesRef.whereEqualTo("id", medicineId)

            .get()
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot.documents) {
                    // Get the document reference for the matching document
                    val medicineDocRef = document.reference

                    // Create updates map
                    val updates = hashMapOf<String, Any>(
                        "quantity" to medicine.quantity,
                        "minStockLimit" to medicine.minStockLimit,
                        "price" to medicine.price
                    )

                    // Update the document
                    medicineDocRef.update(updates)
                        .addOnSuccessListener {
                            Log.d(ContentValues.TAG, "Medicine updated successfully")
                        }
                        .addOnFailureListener { e ->
                            Log.e(ContentValues.TAG, "Error updating medicine", e)
                        }
                }
            }
            .addOnFailureListener { e ->
                Log.e(ContentValues.TAG, "Error querying medicine", e)
            }
    }



    fun deleteMedicine(medicineId: String) {
        val firestore = FirebaseFirestore.getInstance()
        val medicineRef = firestore.collection("medicines").document(medicineId)
        medicineRef.delete()
            .addOnSuccessListener {
                Log.d(ContentValues.TAG, "Medicine deleted successfully")
            }
            .addOnFailureListener { e ->
                Log.e(ContentValues.TAG, "Error deleting medicine", e)
            }
    }

