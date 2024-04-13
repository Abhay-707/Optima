package com.innovateyou.hackathonprototype.ui.theme


import android.content.ContentValues
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Text
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.firestore.FirebaseFirestore
import com.innovateyou.hackathonprototype.data.Doctor
import com.innovateyou.hackathonprototype.data.Medicine
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter


@Composable
fun Assign(paddingValues: PaddingValues,viewModel: MainViewModel){
    dismissScreen()
    val doctors = listOf(Doctor("Dr.Deshmukh"),
        Doctor("Dr.Shitole"),
        Doctor("Dr.Patil"),
        Doctor("Dr.Agrawal")
    )

    val medicines = remember {
        mutableStateOf(emptyList<Medicine>())

    }
    val context = LocalContext.current
    var selectedDoctor by remember { mutableStateOf<Doctor?>(null) }
    var selectedMedicines by remember { mutableStateOf<List<Medicine>>(emptyList()) }
    var expanded by remember { mutableStateOf(false) }

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
                            Log.e(ContentValues.TAG, "Error converting document to Medicine", e)
                            null
                        }
                    }
                    medicines.value= newMedicines
                }
            }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {

        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.padding(16.dp)) {

            Box(modifier = Modifier.wrapContentSize()){


                    Row {
                        Button(onClick = { expanded = true }, modifier = Modifier.size(150.dp,50.dp),colors = ButtonDefaults.buttonColors(backgroundColor = OrangeStart)) {
                            Text(text = selectedDoctor?.name ?: "Select Doctor")
                            Icon(imageVector = Icons.Rounded.ArrowDropDown, contentDescription = null)
                        }
                    }

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        doctors.forEach { doctor ->

                            DropdownMenuItem(
                                onClick = { selectedDoctor = doctor
                                expanded = false}
                            ) {
                                Text(text = doctor.name)
                            }


                        }
                    }




            }

            Spacer(modifier = Modifier.width(80.dp))

                CurrentDateTimeColumn()



        }



        Spacer(modifier = Modifier.height(16.dp))
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

        LazyColumn(modifier = Modifier.height(400.dp)) {
            items(sortedMedicines) { medicine ->
                AssignCard(
                    name = medicine.name,
                    quantity = medicine.quantity,
                    expDate = medicine.expiryDate,
                    type = medicine.type,
                    minStockLimit = medicine.minStockLimit,
                    manufacturer = medicine.manufacturer
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { /* TODO */ },
                colors = ButtonDefaults.buttonColors(backgroundColor = OrangeStart),
                modifier = Modifier.weight(1f).padding(horizontal = 8.dp)
            ) {
                Text("Assigned Status")
                Icon(imageVector = Icons.Rounded.Info, contentDescription = null)
            }

            Button(
                onClick = { Toast.makeText(context,"Assigned medicines to ${selectedDoctor?.name} Successfully! ",Toast.LENGTH_LONG).show()},
                colors = ButtonDefaults.buttonColors(backgroundColor = OrangeStart),
                modifier = Modifier.weight(1f).padding(horizontal = 8.dp)
            ) {
                Text("Assign")
                Icon(imageVector = Icons.Rounded.Check, contentDescription = null)
            }
        }







    }
}

@Composable
fun CurrentDateTimeColumn() {
    val currentDateTime = remember { mutableStateOf(LocalTime.now()) }
    val currentDate = remember { mutableStateOf(LocalDate.now()) }

    LaunchedEffect(Unit) {
        while (true) {
            currentDateTime.value = LocalTime.now()
            currentDate.value = LocalDate.now()
            // Update every second
            kotlinx.coroutines.delay(1000)
        }
    }
    Card (backgroundColor = OrangeStart, modifier = Modifier
        .size(160.dp, 80.dp)
        .clip(RoundedCornerShape(20))){
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, ) {
            Row (verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center, modifier = Modifier.padding(start = 8.dp)){
                Text(text = "Day: ", fontFamily = harmoniaSansFamily, color = Color.Gray,fontSize = 16.sp)
                Text(text = currentDate.value.format(DateTimeFormatter.ofPattern("EEEE")), fontFamily = harmoniaSansFamily, fontWeight = FontWeight.Bold,fontSize = 16.sp)

            }
            Row (verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center, modifier = Modifier.padding(start = 8.dp)){
                Text(text = "Date: ", fontFamily = harmoniaSansFamily, color = Color.Gray,fontSize = 16.sp)
                Text(text = currentDate.value.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), fontFamily = harmoniaSansFamily, fontWeight = FontWeight.Bold,fontSize = 16.sp)

            }

            Row (verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center, modifier = Modifier.padding(start = 8.dp)){
                Text(text = "Time: ", fontFamily = harmoniaSansFamily, color = Color.Gray,fontSize = 16.sp)
                Text(text = currentDateTime.value.format(DateTimeFormatter.ofPattern("HH:mm:ss")), fontFamily = harmoniaSansFamily, fontWeight = FontWeight.Bold,fontSize = 16.sp)

            }


        }

    }

}




