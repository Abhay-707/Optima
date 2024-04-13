package com.innovateyou.hackathonprototype.ui.theme

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material.OutlinedTextField
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.innovateyou.hackathonprototype.data.Screen

@Composable
fun RequestMedicines(paddingValues: PaddingValues, onSubmitRequest: (List<MedicineRequest>) -> Unit,navController: NavController) {
    dismissScreen()
    var medicineName by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var selectedType by remember { mutableStateOf("Tablet") }
    var showConfirmationDialog by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }
    var selectedSeverity by remember {
        mutableStateOf("Severity")
    }
    var expandedSeverity by remember {
        mutableStateOf(false)
    }

    val addedMedicines = remember { mutableStateListOf<MedicineRequest>( ) }

    val focusManager = LocalFocusManager.current
    val context = LocalContext.current


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Medicines Request", fontFamily = harmoniaSansFamily, fontWeight = FontWeight.Bold, fontSize = 24.sp)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = medicineName,
            onValueChange = { medicineName = it },
            label = { Text("Medicine Name") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = {
                focusManager.clearFocus()
            }),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = LightBlue,
                    focusedLabelColor = LightBlue
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = quantity,
            onValueChange = { quantity = it },
            label = { Text("Quantity") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus()
            }),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = LightBlue,
                focusedLabelColor = LightBlue
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Box(){
                Button(
                    onClick = { expanded = true },
                    colors = ButtonDefaults.buttonColors(backgroundColor = ThemeBlueStart)
                ) {
                    Text(if (selectedType.isNotBlank()) selectedType else "Select Type")
                    Icon(imageVector = Icons.Rounded.ArrowDropDown, contentDescription = null)
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                ) {
                    DropdownMenuItem(onClick = {
                        selectedType = "Tablet"
                        expanded = false
                    }) {
                        Text("Tablet")
                    }
                    DropdownMenuItem(onClick = {
                        selectedType = "Spray"
                        expanded = false
                    }) {
                        Text("Spray")
                    }
                    DropdownMenuItem(onClick = {
                        selectedType = "Syrup"
                        expanded = false
                    }) {
                        Text("Syrup")
                    }
                    DropdownMenuItem(onClick = {
                        selectedType = "Ointment"
                        expanded = false
                    }) {
                        Text("Ointment")
                    }
                    DropdownMenuItem(onClick = {
                        selectedType = "Equipment"
                        expanded = false
                    }) {
                        Text("Equipment")
                    }
                }
            }

            Box(){
                Button(
                    onClick = { expandedSeverity = true },
                    colors = ButtonDefaults.buttonColors(backgroundColor = ThemeBlueStart)
                ) {
                    Text(if (selectedSeverity.isNotBlank()) selectedSeverity else "Select Severity")
                    Icon(imageVector = Icons.Rounded.ArrowDropDown, contentDescription = null)
                }

                DropdownMenu( modifier = Modifier.background(brush = Brush.verticalGradient(listOf(
                    ThemeGreenStart,ThemeRedStart))),
                    expanded = expandedSeverity,
                    onDismissRequest = { expandedSeverity = false },

                    ) {
                    DropdownMenuItem(modifier = Modifier.background(ThemeGreenStart),onClick = {
                        selectedSeverity = "Normal"
                        expandedSeverity = false
                    }) {
                        Text("Normal")
                    }
                    DropdownMenuItem(modifier = Modifier.background(ThemeYellowStart),onClick = {
                        selectedSeverity = "Moderate"
                        expandedSeverity = false
                    }) {
                        Text("Moderate")
                    }
                    DropdownMenuItem(modifier = Modifier.background(ThemeRedStart), onClick = {
                        selectedSeverity = "Severe"
                        expandedSeverity = false
                    }) {
                        Text("Severe")
                    }

                }
            }
        }



        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // Validate inputs before adding
                if (medicineName.isNotBlank() && quantity.isNotBlank() && selectedType.isNotBlank()) {
                    addedMedicines.add(
                        MedicineRequest(
                            medicineName = medicineName,
                            quantity = quantity.toInt(),
                            type = selectedType
                        )
                    )
                    // Clear inputs after adding
                    medicineName = ""
                    quantity = ""
                    selectedType = ""
                }
            },
            modifier = Modifier.align(Alignment.End),colors = ButtonDefaults.buttonColors(backgroundColor = ThemeBlueStart)
        ) {
            Text("Add")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Selected Medicines: ", fontFamily = harmoniaSansFamily, fontWeight = FontWeight.Bold,fontSize = 20.sp)
        Spacer(modifier = Modifier.height(16.dp))

        Box(modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(5))
            .height(250.dp)){
            Column {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .background(
                        if (selectedSeverity == "Normal") {
                            ThemeGreenStart
                        } else if (selectedSeverity == "Moderate") {
                            ThemeYellowStart
                        } else ThemeRedStart
                    )  ){
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Box(modifier = Modifier.weight(1f)){
                            Column (horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()){
                                Text(text = "Name", fontFamily = harmoniaSansFamily, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            }

                        }
                        Box(modifier = Modifier.weight(1f)){
                            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()) {
                                Text(text = "Quantity", fontFamily = harmoniaSansFamily, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            }
                        }
                        Box(modifier = Modifier.weight(1f)){
                            Column (horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()){
                                Text(text = "Type", fontFamily = harmoniaSansFamily, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            }
                        }

                    }



                }

                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(
                        if (selectedSeverity == "Normal") {
                            ThemeGreenEnd
                        } else if (selectedSeverity == "Moderate") {
                            ThemeYellowEnd
                        } else ThemeRedEnd
                    )){
                    LazyColumn(
                        modifier = Modifier.height(200.dp)
                    ) {
                        items(addedMedicines) { medicine ->
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(modifier = Modifier.fillMaxWidth()) {
                                Box(modifier = Modifier.weight(1f)){
                                    Column (horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()){
                                        Text(text = medicine.medicineName, fontFamily = harmoniaSansFamily, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                    }

                                }
                                Box(modifier = Modifier.weight(1f)){
                                    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()) {
                                        Text(text = medicine.quantity.toString(), fontFamily = harmoniaSansFamily, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                    }
                                }
                                Box(modifier = Modifier.weight(1f)){
                                    Column (horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()){
                                        Text(text = medicine.type, fontFamily = harmoniaSansFamily, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                    }
                                }

                            }
                            Spacer(modifier = Modifier.height(8.dp))

                        }
                    }
                }


            }

        }


        Spacer(modifier = Modifier.height(16.dp))

        Row (horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()){
            Button(onClick = { navController.navigate(Screen.RequestMedicineRequests.route)},colors = ButtonDefaults.buttonColors(backgroundColor = ThemeBlueStart)) {
                Text(text = "Request History")
                Icon(imageVector = Icons.Rounded.Refresh, contentDescription = null)
            }
            Button(
                onClick = {
                    // Validate if any medicine is added before submitting the request
                    if (addedMedicines.isNotEmpty()) {
                        showConfirmationDialog = true
                    }
                },colors = ButtonDefaults.buttonColors(backgroundColor = ThemeBlueStart)
            ) {
                Text("Confirm Request")
            }
        }


        // Confirmation dialog for submitting request
        if (showConfirmationDialog) {
            AlertDialog( modifier = Modifier.clip(RoundedCornerShape(10)),
                onDismissRequest = { showConfirmationDialog = false },
                title = { Text("Confirm Request") },
                text = {
                    Column {
                        addedMedicines.forEach { medicine ->
                            Row {
                                Text("${medicine.medicineName}, Quantity: ${medicine.quantity}, Type: ${medicine.type}")
                            }

                        }
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            onSubmitRequest(addedMedicines)
                            showConfirmationDialog = false

                            addedMedicines.clear()
                            Toast.makeText(context,"Request Submitted Successfully! ", Toast.LENGTH_SHORT).show()
                        },colors = ButtonDefaults.buttonColors(backgroundColor = ThemeBlueStart)
                    ) {
                        Text("Submit")
                    }
                },
                dismissButton = {
                    Button(
                        onClick = { showConfirmationDialog = false },
                        colors = ButtonDefaults.buttonColors(backgroundColor = ThemeBlueStart)
                    ) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}


data class MedicineRequest(
    val medicineName: String,
    val quantity: Int,
    val type: String
)
