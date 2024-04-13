package com.innovateyou.hackathonprototype.ui.theme


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.innovateyou.hackathonprototype.R
import com.innovateyou.hackathonprototype.data.BottomNavigationItemData
@Composable
fun HomeScreen(viewModel: MainViewModel,navController: NavController){
    Scaffold(topBar = { TopAppBar(viewModel = viewModel,onSearchQueryChanged = { viewModel.searchQuery = it })}, bottomBar = { BottomAppBar(
        viewModel = viewModel
    )}) { padding ->


        if (viewModel.selectedScreen.value == "Inventory") {
            Inventory(padding,navController,viewModel)
        }
        else if(viewModel.selectedScreen.value == "Request") {
            RequestMedicines(padding,{},navController)
        }
        else if(viewModel.selectedScreen.value == "Emergency"){
            Emergency(padding,navController)
        }
        else{
            Assign(padding,viewModel)
        }


    }
}

@Composable
fun TopAppBar(viewModel: MainViewModel,onSearchQueryChanged: (MutableState<String>) -> Unit){
    var expanded by remember { mutableStateOf(false) }
    val sortingOptions = listOf(
        "Name",
        "Quantity",
        "Expiry Date",
        "Manufacturer"
    )
    Column(modifier = Modifier
        .height(if ((viewModel.selectedScreen.value == "Emergency") || (viewModel.selectedScreen.value == "Request")) {60.dp} else 120.dp)
        .fillMaxWidth()
        .background(Color.White))
    {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(8.dp)
        ) {
            Row(modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically)
            {



                Text(text = viewModel.selectedScreen.value, fontFamily = harmoniaSansFamily, fontWeight = FontWeight.Bold, fontSize = 24.sp, modifier = Modifier.padding(start = 8.dp))
                Text(text = "Lohgaon PHC | 411047", fontFamily = harmoniaSansFamily, fontWeight = FontWeight.Bold, fontSize = 12.sp, modifier = Modifier.padding(start = 8.dp))



            }
            if( (viewModel.selectedScreen.value != "Emergency") && (viewModel.selectedScreen.value != "Request")){
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ){

                    TextField(value = viewModel.searchQuery.value, onValueChange = {viewModel.searchQuery.value = it} , modifier = Modifier
                        .fillMaxWidth()
                        .clip(
                            RoundedCornerShape(50)
                        )
                        .weight(0.8f)
                        .padding(8.dp))
                    Box {
                        Card(
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .size(width = 40.dp, height = 40.dp)
                                .clip(
                                    RoundedCornerShape(50)
                                ),
                            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(50))
                                    .weight(0.2f)
                            ) {
                                Image(painter = painterResource(id = R.drawable.filter),
                                    contentDescription = "search",
                                    modifier = Modifier.padding(8.dp).clickable { expanded = true })
                            }

                        }
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            modifier = Modifier.wrapContentSize()
                        ) {
                            sortingOptions.forEach { option ->
                                DropdownMenuItem(onClick = {
                                    viewModel.sortingOption = when (option) {
                                        "Name" -> SortingOption.NAME
                                        "Quantity" -> SortingOption.QUANTITY
                                        "Expiry Date" -> SortingOption.EXPIRY_DATE
                                        "Manufacturer" -> SortingOption.MANUFACTURER
                                        else -> SortingOption.NAME
                                    }
                                    expanded = false
                                }) {
                                    Text(option)
                                }
                            }
                        }
                    }


                }

            }


        }


    }
}
@Composable
fun BottomAppBar(viewModel: MainViewModel){




    NavigationBar(contentColor = ThemeBlueStart) {
        Row (modifier = Modifier.background(MaterialTheme.colorScheme.background)){
            items.forEachIndexed{index, item ->
                NavigationBarItem(label = { Text(text = item.title, fontFamily = harmoniaSansFamily, fontWeight = FontWeight.Normal)}
                    ,selected = viewModel.selectedScreen.value == item.title
                    , onClick = { viewModel.selectedScreen.value = item.title }
                    , icon = { Icon(
                    imageVector = item.icon,
                    contentDescription = item.title,

                    ) })
            }
        }
    }

}





val items = listOf(
    BottomNavigationItemData("Inventory",Icons.Rounded.Menu, "inventory_route" ),
    BottomNavigationItemData("Request",Icons.Rounded.ShoppingCart, "request_route"),
    BottomNavigationItemData("Emergency",Icons.Rounded.Warning,"emergency_route"),
    BottomNavigationItemData("Assign",Icons.Rounded.AccountCircle, "assign_route")

)