package com.example.betadiccompose.ui.Foundation.Shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.betadiccompose.ui.Navigation.ItemsMenu


@Composable
fun navegationinferior(menu: List<ItemsMenu>, current: String?, onclickNav: (ItemsMenu) -> Unit){

   /*
    BottomAppBar(
        modifier = Modifier
            .height(85.dp)
            .fillMaxWidth()
            .padding(0.dp,0.dp,0.dp,0.dp),
           //.clip(RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp))


        backgroundColor = Color.Blue
      ){


    }
*/

    Box(
        modifier = Modifier
            .background(Color(0xFFECECEC))
            .height(80.dp)
            .fillMaxWidth()
    ){
        BottomNavigation(
            backgroundColor = Color.White,
            modifier = Modifier
                .height(90.dp)
                .padding(0.dp, 2.dp, 0.dp, 0.dp),
            elevation = 10.dp
        ){


            menu.forEach {

                val selected = it.route!=current

                BottomNavigationItem(
                    selected = current == it.route,
                    onClick = { if(it.route!=current)onclickNav(it) },
                    icon = {
                        Icon(
                            // = it.icon,
                            //imageVector = if(selected) it.icon else it.iconSelect,
                            painter = painterResource (  if(selected) it.iconSelected else it.iconUnselected ),
                           // imageVector =  it.iconSelect,
                            contentDescription = "BTN",
                            modifier =  Modifier
                                .size(45.dp),
                            tint = Color.Unspecified)
                    },
                   // label = { Text(text = it.title, color = MaterialTheme.colors.onBackground) },
                 //  unselectedContentColor = Color.Gray,
                    selectedContentColor = Color.Unspecified,
                    alwaysShowLabel = false)
            }

        }
    }


}



