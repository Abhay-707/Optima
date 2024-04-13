package com.innovateyou.hackathonprototype.ui.theme

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material3.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.firestore.FirebaseFirestore
import com.innovateyou.hackathonprototype.data.Medicine
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@Composable
fun DetailsActivity(medicineId: String?, onUpdate: (Medicine) -> Unit,
                    onDelete: () -> Unit) {
    val medicine = remember { mutableStateOf<Medicine?>(null) }



    LaunchedEffect(medicineId) {
        medicine.value = fetchMedicineDetails(medicineId)
    }

    val medicineData = medicine.value

    if (medicineData != null) {
        Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Medicines Detail Screen", fontFamily = harmoniaSansFamily, fontWeight = FontWeight.Bold, fontSize = 24.sp, modifier = Modifier.padding(start = 8.dp))
            Column(
                modifier = Modifier.padding(8.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .height(100.dp)
                        .clip(
                            RoundedCornerShape(10)
                        ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
                ){
                    Box(modifier = Modifier
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    OrangeStart,
                                    OrangeEnd
                                )
                            )
                        )
                        .height(100.dp)
                        .fillMaxWidth()
                    ){
                        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "Name: ${medicineData.name}", fontFamily = harmoniaSansFamily, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                            Text(text = "Manufacturer: ${medicineData.manufacturer}", fontFamily = harmoniaSansFamily, fontSize = 16.sp)
                        }

                    }
                }

            }
            Spacer(modifier = Modifier.height(16.dp))

            Row (verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center){
                Text(text = "Quantity: ", fontFamily = harmoniaSansFamily, color = Color.Gray,fontSize = 16.sp)
                Text(text = "${medicineData.quantity}", fontFamily = harmoniaSansFamily, fontWeight = FontWeight.Bold,fontSize = 16.sp)

            }
            Spacer(modifier = Modifier.height(8.dp))

            Row (verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center){
                Text(text = "Expiry Date: ", fontFamily = harmoniaSansFamily, color = Color.Gray,fontSize = 16.sp)
                Text(text = "${medicineData.expiryDate}", fontFamily = harmoniaSansFamily, fontWeight = FontWeight.Bold,fontSize = 16.sp)

            }
            //Text(text = "Expiry Date: ${medicineData.expiryDate}", fontFamily = harmoniaSansFamily)
            Spacer(modifier = Modifier.height(8.dp))
            Row (verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center){
                Text(text = "Drug Type: ", fontFamily = harmoniaSansFamily, color = Color.Gray,fontSize = 16.sp)
                Text(text = "${medicineData.type}", fontFamily = harmoniaSansFamily, fontWeight = FontWeight.Bold,fontSize = 16.sp)

            }
           // Text(text = "Type: ${medicineData.type}", fontFamily = harmoniaSansFamily)
            Spacer(modifier = Modifier.height(8.dp))
            Row (verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center){
                Text(text = "Minimum Stock Limit: ", fontFamily = harmoniaSansFamily, color = Color.Gray,fontSize = 16.sp)
                Text(text = "${medicineData.minStockLimit}", fontFamily = harmoniaSansFamily, fontWeight = FontWeight.Bold,fontSize = 16.sp)

            }
           // Text(text = "Minimum Stock Limit: ${medicineData.minStockLimit}", fontFamily = harmoniaSansFamily)
            Spacer(modifier = Modifier.height(8.dp))
            Row (verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center){
                Text(text = "Price: ₹", fontFamily = harmoniaSansFamily, color = Color.Gray,fontSize = 16.sp)
                Text(text = "${medicineData.price}", fontFamily = harmoniaSansFamily, fontWeight = FontWeight.Bold,fontSize = 16.sp)

            }
           // Text(text = "Price: ₹${medicineData.price}", fontFamily = harmoniaSansFamily)
            Spacer(modifier = Modifier.height(16.dp))



            OutlinedTextField(
                value = medicine.value?.quantity?.toString() ?: "",
                onValueChange = { value ->
                    medicine.value = medicine.value?.copy(quantity = value.toIntOrNull() ?: 0)
                },
                label = { Text("Quantity") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = LightBlue,
                    focusedLabelColor = LightBlue
                )
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = medicine.value?.minStockLimit?.toString() ?: "",
                onValueChange = { value ->
                    medicine.value = medicine.value?.copy(minStockLimit = value.toIntOrNull() ?: 0)
                },
                label = { Text("Minimum Stock Limit") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = LightBlue,
                    focusedLabelColor = LightBlue
                )
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = medicine.value?.price?.toString() ?: "",
                onValueChange = { value ->
                    medicine.value = medicine.value?.copy(price = value.toFloatOrNull() ?: 0.0f)
                },
                label = { Text("Price") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = LightBlue,
                    focusedLabelColor = LightBlue
                )
            )








            Spacer(modifier = Modifier.height(16.dp))
            Card (modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(40.dp)
                .clip(
                    RoundedCornerShape(10)
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)){
                    Box(modifier = Modifier.background(brush = Brush.horizontalGradient(colors = listOf(ThemeRedStart,
                        ThemeRedEnd))).fillMaxSize()){
                        Row (modifier = Modifier.padding(8.dp),
                            horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
                            Icon(imageVector = Icons.Rounded.Warning, contentDescription = null )
                            Text(text = "This change will be notified to District PHC",fontFamily = harmoniaSansFamily, fontWeight = FontWeight.Bold,fontSize = 16.sp)
                        }
                    }
            }

            Row {
                Button(onClick = { onUpdate(medicine.value!!) },colors = ButtonDefaults.buttonColors(backgroundColor = ThemeBlueStart), modifier = Modifier.clip(
                    RoundedCornerShape(20)
                )) {
                    Text(text = "Update",)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(onClick = onDelete,colors = ButtonDefaults.buttonColors(backgroundColor = ThemeBlueStart)) {
                    Text(text = "Delete")
                }
            }
        }
    } else {
        Text("No medicine details available.")
    }
}


suspend fun fetchMedicineDetails(medicineId: String?): Medicine? {
    Log.d(TAG, "Fetching medicine details for ID: $medicineId")
    if (medicineId == null) {
        Log.e(TAG, "Medicine ID is null")
        return null
    }

    val db = FirebaseFirestore.getInstance()

    return try {
        suspendCoroutine { continuation ->
            db.collection("medicines")
                .document(medicineId)
                .get()
                .addOnSuccessListener { documentSnapshot ->
                    if (documentSnapshot.exists()) {
                        val medicine = documentSnapshot.toObject(Medicine::class.java)
                        if (medicine != null) {
                            continuation.resume(medicine)
                        } else {
                            Log.e(TAG, "Medicine data is null")
                            continuation.resume(null)
                        }
                    } else {
                        Log.e(TAG, "Medicine document does not exist")
                        continuation.resume(null)
                    }
                }
                .addOnFailureListener { exception ->
                    Log.e(TAG, "Error getting medicine document", exception)
                    continuation.resume(null)
                }
        }
    } catch (e: Exception) {
        Log.e(TAG, "Exception in fetchMedicineDetails", e)
        null
    }
}








