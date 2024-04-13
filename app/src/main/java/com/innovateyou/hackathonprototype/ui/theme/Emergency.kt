package com.innovateyou.hackathonprototype.ui.theme

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.innovateyou.hackathonprototype.data.Screen


@Composable
fun Emergency(padding: PaddingValues,navController: NavController) {
    val context = LocalContext.current

    dismissScreen()

    val items = listOf(
        Items("124", "Wadgoan", "12/12/23", "Pending"),
        Items("125", "Akurdi", "30/12/23", "Approved"),
        Items("126", "Pimpri", "04/01/24", "Pending"),
        Items("127", "Induri", "09/01/24", "Rejected"),
        Items("128", "Tulapur", "15/01/24", "Pending"),
        Items("129", "Kharadi", "21/01/24", "Approved"),
        Items("130", "Sol", "31/01/24", "Approved"),
        Items("131", "Alandi", "13/02/24", "Approved"),
        Items("132", "Dehu", "11/08/23", "Rejected"),
        Items("133", "Dhanori", "05/05/23", "Rjected"),
        Items("134", "Theur", "09/11/24", "Rejected")
    )

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        RequestsInfoCard()
        Row(
            modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "Recent Requests",
                fontFamily = harmoniaSansFamily,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { Toast.makeText(context,"We have prosperity so we will be prosperous!! Goodluck Team!", Toast.LENGTH_SHORT).show() }

            )

        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .height(420.dp)
                .clip(
                    RoundedCornerShape(5)
                )

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
                .height(400.dp)
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(10)
                )
            ){
                Column{
                    LazyColumn(
                        contentPadding = padding,
                        modifier = Modifier
                            .height(420.dp)
                            .weight(1f)
                    ) {
                        items(items) { item ->
                            OrderStatus(OrderID = item.orderId, PHC = item.phc, Date = item.date, Status = item.status)
                        }

                    }


                }


            }

        }


        Row(
            modifier = Modifier.padding(start = 180.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ){
            Row (modifier = Modifier.clickable { navController.navigate(Screen.SeeAllRequests.route) }){

                Text(text = "See all requests", fontFamily = harmoniaSansFamily, fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 75.dp))
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "Go", tint = Color.Black)}
        }




        EmergencyRequestCard(){navController.navigate(Screen.EmergencyRequest.route)}
    }
}

data class Items(val orderId: String, val phc: String, val date: String, val status: String)

@Composable
fun RequestsInfoCard(){
    Column(
        modifier = Modifier.padding(8.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        androidx.compose.material3.Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(100.dp)
                .clip(
                    RoundedCornerShape(10)
                ),
            elevation = CardDefaults.cardElevation(defaultElevation = 20.dp),
        ) {
            Box(
                modifier = Modifier
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
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column(
                        modifier = Modifier.padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        androidx.compose.material.Text(
                            text = "13",
                            fontSize = 24.sp,
                            fontFamily = harmoniaSansFamily,
                            fontWeight = FontWeight.Bold
                        )
                        androidx.compose.material.Text(
                            text = "Total",
                            fontSize = 16.sp,
                            fontFamily = harmoniaSansFamily,
                            fontWeight = FontWeight.Light,
                            color = Color.Gray
                        )

                    }
                    Column(
                        modifier = Modifier.padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        androidx.compose.material.Text(
                            text = "8",
                            fontSize = 24.sp,
                            fontFamily = harmoniaSansFamily,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                        androidx.compose.material.Text(
                            text = "Approved",
                            fontSize = 16.sp,
                            fontFamily = harmoniaSansFamily,
                            fontWeight = FontWeight.Light,
                            textAlign = TextAlign.Center,
                            color = Color.Gray
                        )

                    }
                    Column(
                        modifier = Modifier.padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        androidx.compose.material.Text(
                            text = "2",
                            fontSize = 24.sp,
                            fontFamily = harmoniaSansFamily,
                            fontWeight = FontWeight.Bold
                        )
                        androidx.compose.material.Text(
                            text = "Rejected",
                            fontSize = 16.sp,
                            fontFamily = harmoniaSansFamily,
                            fontWeight = FontWeight.Light,
                            color = Color.Gray
                        )

                    }
                    Column(
                        modifier = Modifier.padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        androidx.compose.material.Text(
                            text = "3",
                            fontSize = 24.sp,
                            fontFamily = harmoniaSansFamily,
                            fontWeight = FontWeight.Bold
                        )
                        androidx.compose.material.Text(
                            text = "Pending",
                            fontSize = 16.sp,
                            fontFamily = harmoniaSansFamily,
                            fontWeight = FontWeight.Light,
                            color = Color.Gray
                        )

                    }

                }

            }
        }

    }

}

@Composable

fun OrderStatus(OrderID:String , PHC:String , Date:String , Status:String){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
    ){
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clip(RoundedCornerShape(10))){
            Column(
                modifier = Modifier.fillMaxSize(),
            ){
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .background(ThemeBlueStart)){
                    Row (modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceEvenly , verticalAlignment = Alignment.CenterVertically){
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally

                        ) {
                            Text(
                                text = "OrderID",
                                fontFamily = harmoniaSansFamily,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold

                            )

                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally

                        ) {
                            Text(
                                text = "PHC",
                                fontFamily = harmoniaSansFamily,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold

                            )

                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Date",
                                fontFamily = harmoniaSansFamily,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold

                            )

                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Status",
                                fontFamily = harmoniaSansFamily,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold

                            )

                        }


                    }

                }
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .background(ThemeBlueEnd)){
                    Row (modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceEvenly , verticalAlignment = Alignment.CenterVertically){
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally

                        ) {
                            Text(
                                text = "$OrderID",
                                fontFamily = harmoniaSansFamily,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold

                            )

                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally

                        ) {
                            Text(
                                text = "$PHC",
                                fontFamily = harmoniaSansFamily,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold

                            )

                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "$Date",
                                fontFamily = harmoniaSansFamily,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold

                            )

                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "$Status",
                                fontFamily = harmoniaSansFamily,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold

                            )

                        }


                    }
                }
            }




        }
    }


}
@Composable
fun EmergencyRequestCard(onClick: () -> Unit){
    Column(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick() },
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment =  Alignment.CenterHorizontally
    ){
        androidx.compose.material3.Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(60.dp)
                .clip(
                    RoundedCornerShape(10)
                ),
            elevation = CardDefaults.cardElevation(defaultElevation = 20.dp),
        ) {
            Box(
                modifier = Modifier
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                ThemeRedStart,
                                ThemeRedEnd
                            )
                        )
                    )
                    .height(60.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    androidx.compose.material.Text(
                        text = "Make an Emergency Request",
                        fontFamily = harmoniaSansFamily,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                    Spacer(modifier = Modifier.width(60.dp))
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Go",
                        tint = Color.Black
                    )

                }

            }

        }

    }
}