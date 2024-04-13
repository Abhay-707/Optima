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
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.material.OutlinedTextField
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun EmergencyRequest(viewModel: MainViewModel,onSubmitRequest: (List<MedicineRequest>) -> Unit){
    viewModel.selectedScreen.value = "Emergency"
    var medicineName by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var selectedType by remember { mutableStateOf("Type") }
    val context = LocalContext.current

    var selectedPHC by remember {
        mutableStateOf("PHC")
    }
    var selectedSeverity by remember {
        mutableStateOf("Severity")
    }

    var showConfirmationDialog by remember { mutableStateOf(false) }
    var description by remember {
        mutableStateOf("")
    }
    var expandedType by remember { mutableStateOf(false) }
    var expandedPHC by remember { mutableStateOf(false) }
    var expandedSeverity by remember {
        mutableStateOf(false)
    }


    val addedMedicines = remember { mutableStateListOf<MedicineRequest>( ) }

    val focusManager = LocalFocusManager.current

    Column (modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)){
        Text(text = "Emergency Request", fontFamily = harmoniaSansFamily, fontWeight = FontWeight.Bold, fontSize = 24.sp)
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

        // Button to show the selected type
        Row (horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()){
            Box(){
                Button(
                    onClick = { expandedType = true },
                    modifier = Modifier.size(100.dp,50.dp),colors = ButtonDefaults.buttonColors(backgroundColor = ThemeBlueStart)
                ) {
                    Text(if (selectedType.isNotBlank()) selectedType else "Select Type")
                    Icon(imageVector = Icons.Rounded.ArrowDropDown, contentDescription = null)
                }

                DropdownMenu(
                    expanded = expandedType,
                    onDismissRequest = { expandedType = false },

                    ) {
                    DropdownMenuItem(onClick = {
                        selectedType = "Tablet"
                        expandedType = false
                    }) {
                        Text("Tablet")
                    }
                    DropdownMenuItem(onClick = {
                        selectedType = "Spray"
                        expandedType = false
                    }) {
                        Text("Spray")
                    }
                    DropdownMenuItem(onClick = {
                        selectedType = "Syrup"
                        expandedType = false
                    }) {
                        Text("Syrup")
                    }
                    DropdownMenuItem(onClick = {
                        selectedType = "Ointment"
                        expandedType = false
                    }) {
                        Text("Ointment")
                    }
                    DropdownMenuItem(onClick = {
                        selectedType = "Equipment"
                        expandedType = false
                    }) {
                        Text("Equipment")
                    }
                }
            }

            Box(){
                Button(
                    onClick = { expandedPHC = true },
                    modifier = Modifier.size(110.dp,50.dp),colors = ButtonDefaults.buttonColors(backgroundColor = ThemeBlueStart)
                ) {
                    Text(if (selectedPHC.isNotBlank()) selectedPHC else "Select PHC")
                    Icon(imageVector = Icons.Rounded.ArrowDropDown, contentDescription = null)
                }

                DropdownMenu(
                    expanded = expandedPHC,
                    onDismissRequest = { expandedPHC = false },

                    ) {
                    DropdownMenuItem(onClick = {
                        selectedPHC = "Wadgaon"
                        expandedPHC = false
                    }) {
                        Text("Wadgoan")
                    }
                    DropdownMenuItem(onClick = {
                        selectedPHC = "Dhanori"
                        expandedPHC = false
                    }) {
                        Text("Dhanori")
                    }
                    DropdownMenuItem(onClick = {
                        selectedPHC = "Golegoan"
                        expandedPHC = false
                    }) {
                        Text("Golegoan")
                    }
                    DropdownMenuItem(onClick = {
                        selectedPHC = "Pimplegoan"
                        expandedPHC = false
                    }) {
                        Text("Pimplegoan")
                    }
                    DropdownMenuItem(onClick = {
                        selectedPHC = "Tulapur"
                        expandedPHC = false
                    }) {
                        Text("Tulapur")
                    }
                }
            }
            Box(){
                Button(
                    onClick = { expandedSeverity = true },
                    modifier = Modifier.size(100.dp,50.dp),colors = ButtonDefaults.buttonColors(backgroundColor = ThemeBlueStart)
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
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            Button(modifier = Modifier.wrapContentSize(),
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

                        medicineName = ""
                        quantity = ""
                        selectedType = ""
                    }
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = ThemeBlueStart)
            ) {
                Text("Add")
            }
        }


        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Selected Medicines: ", fontFamily = harmoniaSansFamily, fontWeight = FontWeight.Bold,fontSize = 20.sp)

        Spacer(modifier = Modifier.height(16.dp))

        Box(modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(5))
            .height(300.dp)){
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
        OutlinedTextField(
            value = description,
            onValueChange = {description = it },
            label = { Text("Description") },
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
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            Button(onClick = {showConfirmationDialog = true},colors = ButtonDefaults.buttonColors(backgroundColor = ThemeBlueStart)) {
                Text(text = "Confirm")
            }
        }







    }

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
                        description = ""
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