package com.innovateyou.hackathonprototype.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.innovateyou.hackathonprototype.R
@Composable
fun seeAllRequests(viewModel: MainViewModel){
        viewModel.selectedScreen.value = "Emergency"
        var isPendingExpanded by remember { mutableStateOf(false) }
        var isHistoryExpanded by remember { mutableStateOf(false) }
        var scroll = rememberScrollState()


        Column(modifier = Modifier.fillMaxSize().padding(16.dp).verticalScroll(scroll)) {



                Row {
                    Image(painter = painterResource(id = R.drawable.pending), contentDescription = null, modifier = Modifier.size(30.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Pending Requests",
                        fontFamily = harmoniaSansFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        modifier = Modifier.clickable { isPendingExpanded = !isPendingExpanded }
                    )

                    Icon(imageVector = Icons.Rounded.ArrowDropDown, contentDescription = null )


                }
            val pendingDataList = listOf(
                PendingData(
                    phcName = "Lohagoan",
                    description = "General Shortage",
                    requestedMedicines = listOf(
                        MedicineRequest("Paracetamol", 10, "Tablet"),
                        MedicineRequest("Amoxicillin", 5, "Capsule"),
                        MedicineRequest("Loratadine", 15, "Syrup"),
                        MedicineRequest("Ibuprofen", 8, "Tablet"),
                        MedicineRequest("Aspirin", 20, "Tablet")
                    ),
                    time = "9:10 PM",
                    date = "17/02/2024",
                    severity = "Moderate"
                ),
                PendingData(
                    phcName = "Wadgoan",
                    description = "General Shortage",
                    requestedMedicines = listOf(
                        MedicineRequest("Azithromycin", 7, "Tablet"),
                        MedicineRequest("Omeprazole", 3, "Capsule"),
                        MedicineRequest("Dexamethasone", 12, "Injection"),
                        MedicineRequest("Cetirizine", 9, "Tablet"),
                        MedicineRequest("Ranitidine", 6, "Syrup")
                    ),
                    time = "9:10 PM",
                    date = "17/02/2024",
                    severity = "Normal"
                ),
                PendingData(
                    phcName = "Wadgoan",
                    description = "General Shortage",
                    requestedMedicines = listOf(
                        MedicineRequest("Azithromycin", 7, "Tablet"),
                        MedicineRequest("Omeprazole", 3, "Capsule"),
                        MedicineRequest("Dexamethasone", 12, "Injection"),
                        MedicineRequest("Cetirizine", 9, "Tablet"),
                        MedicineRequest("Ranitidine", 6, "Syrup")
                    ),
                    time = "9:10 PM",
                    date = "17/02/2024",
                    severity = "Normal"
                )
            )
            if(isPendingExpanded){
                LazyColumn(modifier = Modifier.height(600.dp)){
                    items(pendingDataList){
                            data->
                        Spacer(modifier = Modifier.height(16.dp))
                        pendingCard(
                            phcName = data.phcName,
                            description = data.description ,
                            requestedMedicines = data.requestedMedicines,
                            time = data.time,
                            date = data.date,
                            severity = data.severity
                        )
                    }


                }
            }


                



                Spacer(modifier = Modifier.height(16.dp))

            Row {
                Image(painter = painterResource(id = R.drawable.history), contentDescription = null, modifier = Modifier.size(30.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "History",
                    fontFamily = harmoniaSansFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    modifier = Modifier.clickable { isHistoryExpanded = !isHistoryExpanded }
                )
                Icon(imageVector = Icons.Rounded.ArrowDropDown, contentDescription = null )
            }

                Spacer(modifier = Modifier.height(16.dp))
            val historyDataList = listOf(
                HistoryData(
                    phcName = "PHC 1",
                    description = "Description 1",
                    requestedMedicines = listOf(
                        MedicineRequest("Medicine 1", 10, "Type A"),
                        MedicineRequest("Medicine 2", 5, "Type B")
                    ),
                    time = "10:00 AM",
                    date = "2024-02-17",
                    severity = "Normal",
                    status = "Approved"
                ),
                HistoryData(
                    phcName = "PHC 2",
                    description = "Description 2",
                    requestedMedicines = listOf(
                        MedicineRequest("Medicine 3", 8, "Type C"),
                        MedicineRequest("Medicine 4", 15, "Type D")
                    ),
                    time = "11:00 AM",
                    date = "2024-02-18",
                    severity = "Moderate",
                    status = "Rejected"
                ),
                HistoryData(
                    phcName = "PHC 3",
                    description = "Description 3",
                    requestedMedicines = listOf(
                        MedicineRequest("Medicine 5", 12, "Type E"),
                        MedicineRequest("Medicine 6", 20, "Type F")
                    ),
                    time = "12:00 PM",
                    date = "2024-02-19",
                    severity = "Severe",
                    status = "Approved"
                ),
                HistoryData(
                    phcName = "PHC 4",
                    description = "Description 4",
                    requestedMedicines = listOf(
                        MedicineRequest("Medicine 7", 6, "Type G"),
                        MedicineRequest("Medicine 8", 18, "Type H")
                    ),
                    time = "1:00 PM",
                    date = "2024-02-20",
                    severity = "Critical",
                    status = "Approved"
                )
            )


            if(isHistoryExpanded){
                LazyColumn(modifier = Modifier.height(600.dp)) {
                    items(historyDataList) { historyData ->
                        Spacer(modifier = Modifier.height(8.dp))
                        historyCard(
                            phcName = historyData.phcName,
                            description = historyData.description,
                            requestedMedicines = historyData.requestedMedicines,
                            time = historyData.time,
                            date = historyData.date,
                            severity = historyData.severity,
                            status = historyData.status
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }







        }









}


@Composable
fun pendingCard(phcName:String, description:String, requestedMedicines:List<MedicineRequest>, time:String, date:String, severity:String){
    Row {
        Card(modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .clip(RoundedCornerShape(10))) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
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
                        Text(text = "PHC: $phcName", fontFamily = harmoniaSansFamily, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Text(text = "Severity: $severity", fontFamily = harmoniaSansFamily, fontWeight = FontWeight.Bold, fontSize = 16.sp)
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
                    Text(text = "Requested Medicines: ", fontFamily = harmoniaSansFamily, fontWeight = FontWeight.Medium,fontSize = 16.sp)

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
                        .padding(16.dp)){
                        Button( onClick = {
                                          },colors = ButtonDefaults.buttonColors(backgroundColor = ThemeBlueStart)) {

                            Icon(imageVector = Icons.Rounded.Check, contentDescription = null)
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Button(
                            onClick = {
                            },colors = ButtonDefaults.buttonColors(backgroundColor = ThemeBlueStart)
                        ) {
                            Icon(imageVector = Icons.Rounded.Clear, contentDescription = null)
                        }
                    }


                }


            }







        }


    }

}
@Composable
fun historyCard(phcName:String,description:String,requestedMedicines:List<MedicineRequest>,time:String,date:String,severity:String,status:String){

    Row {
        Card(modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .clip(RoundedCornerShape(10))) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
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
                        Text(text = "PHC: $phcName", fontFamily = harmoniaSansFamily, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Text(text = "Severity: $severity", fontFamily = harmoniaSansFamily, fontWeight = FontWeight.Bold, fontSize = 16.sp)
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
                    Text(text = "Requested Medicines: ", fontFamily = harmoniaSansFamily, fontWeight = FontWeight.Medium,fontSize = 16.sp)

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
                        .padding(16.dp)){
                        Card(modifier = Modifier
                            .width(100.dp)) {
                            Box(modifier = Modifier
                                .fillMaxSize()
                                .background(ThemeBlueStart)){
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalArrangement =  Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ){
                                    Text(
                                        text = status,
                                        fontFamily = harmoniaSansFamily,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp
                                    )
                                }
                            }

                        }
                    }


                }


            }







        }


    }



}
data class HistoryData(
    val phcName: String,
    val description: String,
    val requestedMedicines: List<MedicineRequest>,
    val time: String,
    val date: String,
    val severity: String,
    val status: String
)
data class PendingData(
    val phcName: String,
    val description: String,
    val requestedMedicines: List<MedicineRequest>,
    val time: String,
    val date: String,
    val severity: String,

)


@Preview
@Composable
fun pre(){
    historyCard(
        phcName = "Lohagaon",
        description = "",
        requestedMedicines = listOf(MedicineRequest("paracetamol",5,"Tablet")),
        time = "9:15PM",
        date = "21/5/2023",
        severity = "Moderate",
        status = "pending"
    )
}