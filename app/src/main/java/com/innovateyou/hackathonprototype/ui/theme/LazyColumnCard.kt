package com.innovateyou.hackathonprototype.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.innovateyou.hackathonprototype.R


@Composable
fun InventoryCard(name:String,quantity:Int, expDate: String,type:String, minStockLimit: Int, manufacturer: String,onClick: () -> Unit){


    val gradient = Brush.horizontalGradient(colors = listOf(ThemeBlueStart , ThemeBlueEnd))
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),

        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(100.dp)
            .clip(
                RoundedCornerShape(20)
            )
            .clickable { onClick() }
    ) {
        Box(modifier = Modifier
            .background(brush = gradient)
            .height(100.dp)
            .fillMaxWidth()){
            Row {


                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                        .padding(12.dp)
                ) {
                    Text(text = name
                        , fontStyle = FontStyle.Normal
                        , fontSize = 18.sp
                        , fontWeight = FontWeight.Bold
                        , fontFamily = harmoniaSansFamily
                        , color = Color.White
                        , modifier = Modifier.padding(4.dp)
                    )

                                    Text(text = manufacturer,
                                         fontStyle = FontStyle.Normal,
                                         fontSize = 18.sp,
                                         fontWeight = FontWeight.Normal,
                                         fontFamily = harmoniaSansFamily,
                                         modifier = Modifier.padding(4.dp),
                                         color = ThemeGray
                                    )

                }

                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                        .padding(12.dp)
                ) {
                    Text(text = "Qty: $quantity"
                        , fontStyle = FontStyle.Normal
                        , fontSize = 18.sp
                        , fontWeight = FontWeight.Bold
                        , fontFamily = harmoniaSansFamily
                        , modifier = Modifier.padding(4.dp)
                        , color = ThemeGray
                    )

                    Text(text = "Exp: $expDate",
                        fontStyle = FontStyle.Normal,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = harmoniaSansFamily,
                        modifier = Modifier.padding(4.dp),
                        color = ThemeGray
                    )

                }
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.2f)
                        .padding(4.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Card(modifier = Modifier
                        .padding(top = 15.dp, bottom = 15.dp, start = 5.dp)

                        .size(15.dp)
                        .clip(RoundedCornerShape(50)),
                        colors = CardDefaults.cardColors(if(quantity<minStockLimit)Orange else Color.Green)

                    ){}
                    Image(painter = painterResource(id =
                    if(type == "Tablet")R.drawable.pill
                    else if(type == "Syrup")R.drawable.syrup
                        else if(type == "Ointment")R.drawable.ointment
                        else if(type == "Spray") R.drawable.spray
                        else R.drawable.syringe), contentDescription = "Capsule", modifier = Modifier.size(50.dp,50.dp))



                }


            }
        }


    }
}
