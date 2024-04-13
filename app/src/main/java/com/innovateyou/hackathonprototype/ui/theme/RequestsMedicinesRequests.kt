package com.innovateyou.hackathonprototype.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Card
import com.innovateyou.hackathonprototype.R
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RequestMedicineRequests(viewModel: MainViewModel){
    val requests = listOf(
        MedicineRequestData(
            requestedMedicines = listOf(
                MedicineRequest("Paracetamol", 2, "Tablet"),
                MedicineRequest("Ibuprofen", 1, "Capsule")
            ),
            time = "09:00 AM",
            date = "2024-02-18",
            severity = "Moderate",
            status = "Pending"
        ),
        MedicineRequestData(
            requestedMedicines = listOf(
                MedicineRequest("Amoxicillin", 3, "Syrup"),
                MedicineRequest("Aspirin", 2, "Tablet")
            ),
            time = "10:30 AM",
            date = "2024-02-18",
            severity = "High",
            status = "Received"
        ),
        MedicineRequestData(
            requestedMedicines = listOf(
                MedicineRequest("Loratadine", 1, "Tablet"),
                MedicineRequest("Eye Drops", 1, "Drops")
            ),
            time = "11:45 AM",
            date = "2024-02-18",
            severity = "Low",
            status = "Pending"
        ),
        MedicineRequestData(
            requestedMedicines = listOf(
                MedicineRequest("Cough Syrup", 2, "Syrup"),
                MedicineRequest("Antacid", 1, "Tablet")
            ),
            time = "02:00 PM",
            date = "2024-02-18",
            severity = "High",
            status = "Approved"
        ),
        MedicineRequestData(
            requestedMedicines = listOf(
                MedicineRequest("Inhaler", 1, "Inhaler"),
                MedicineRequest("Vitamin C", 1, "Tablet")
            ),
            time = "09:15 AM",
            date = "2024-02-19",
            severity = "Moderate",
            status = "Shipped"
        ),
        MedicineRequestData(
            requestedMedicines = listOf(
                MedicineRequest("Antihistamine", 2, "Tablet"),
                MedicineRequest("Eye Drops", 1, "Drops")
            ),
            time = "01:45 PM",
            date = "2024-02-19",
            severity = "Low",
            status = "Shipped"
        ),
        MedicineRequestData(
            requestedMedicines = listOf(
                MedicineRequest("Cough Drops", 1, "Lozenge"),
                MedicineRequest("Throat Spray", 1, "Spray")
            ),
            time = "03:30 PM",
            date = "2024-02-19",
            severity = "High",
            status = "Approved"
        )
    )


    viewModel.selectedScreen.value = "Request"
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Request History",
            fontFamily = harmoniaSansFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(){
            items(requests){request ->
                Spacer(modifier = Modifier.height(8.dp))
                requestCard(requestedMedicines = request.requestedMedicines, time = request.time, date = request.date , severity = request.severity, status = request.status)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

    }




}

@Composable
fun requestCard( requestedMedicines:List<MedicineRequest>, time:String, date:String, severity:String, status:String){
    val enableButton = if(status == "Received") true else false

    Row {
        Card(modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .clip(RoundedCornerShape(10))) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(95.dp)
                .background(
                    if (severity == "Normal") {
                        ThemeGreenStart
                    } else if (severity == "Moderate") {
                        ThemeYellowStart
                    } else ThemeRedStart
                )) {
                Row (modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
                    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxHeight()) {

                            Card(modifier = Modifier
                                .width(100.dp)
                                .height(45.dp)) {
                                Box(modifier = Modifier
                                    .fillMaxSize()
                                    .background(ThemeBlueStart)){
                                    Row (modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
                                        Text(
                                            modifier = Modifier.padding(start = 5.dp),
                                            text = status,
                                            fontFamily = harmoniaSansFamily,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 16.sp
                                        )
                                    }





                                }

                            }


                    }

                    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxHeight()) {
                        Text(text = "Date: $date", fontFamily = harmoniaSansFamily, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Text(text = "Time: $time", fontFamily = harmoniaSansFamily, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    }

                }

            }

            Box(modifier = Modifier
                .fillMaxSize()
                .background(
                    if (severity == "Normal") {
                        ThemeGreenEnd
                    } else if (severity == "Moderate") {
                        ThemeYellowEnd
                    } else ThemeRedEnd
                )){
                Column {
                    Text(text = "Requested Medicines: ",modifier= Modifier.padding(start = 8.dp), fontFamily = harmoniaSansFamily, fontWeight = FontWeight.Medium,fontSize = 16.sp)

                    Spacer(modifier = Modifier.height(8.dp))


                    LazyColumn(modifier = Modifier.height(100.dp)){


                        items(requestedMedicines){
                                medicine->
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {


                                Row (verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center){
                                    Text(text = "Name: ", fontFamily = harmoniaSansFamily, color = Color.Gray,fontSize = 16.sp)
                                    Text(text = "${medicine.medicineName}", fontFamily = harmoniaSansFamily, fontWeight = FontWeight.Bold,fontSize = 16.sp)

                                }
                                Row (verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center){
                                    Text(text = "Qty: ", fontFamily = harmoniaSansFamily, color = Color.Gray,fontSize = 16.sp)
                                    Text(text = "${medicine.quantity}", fontFamily = harmoniaSansFamily, fontWeight = FontWeight.Bold,fontSize = 16.sp)

                                }
                                Row (verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center){
                                    Text(text = "Type: ", fontFamily = harmoniaSansFamily, color = Color.Gray,fontSize = 16.sp)
                                    Text(text = "${medicine.type}", fontFamily = harmoniaSansFamily, fontWeight = FontWeight.Bold,fontSize = 16.sp)

                                }



                            }
                        }
                    }
                    Row (horizontalArrangement = Arrangement.End, modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)) {

                        Button(onClick = {
                        }, colors = ButtonDefaults.buttonColors(backgroundColor = if (enableButton) ThemeBlueStart else Color.Gray), enabled = enableButton) {

                            Icon(imageVector = Icons.Rounded.Check, contentDescription = null)
                            Text(text = "Acknowledge ", fontFamily = harmoniaSansFamily, color = Color.Black,fontSize = 16.sp)

                        }
                    }



                }


            }







        }


    }
}
data class MedicineRequestData(
    val requestedMedicines: List<MedicineRequest>,
    val time: String,
    val date: String,
    val severity: String,
    val status: String
)


@Preview
@Composable
fun request(){
    requestCard(

        requestedMedicines = listOf(),
        time = "9-21",
        date = "9-9-9",
        severity = "Normal",
        status = "Pending Approval"
    )
}