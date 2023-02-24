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


    Box(
        //here make the line
        modifier = Modifier
            .background(MaterialTheme.colors.onSecondary)
            .height(80.dp)
            .fillMaxWidth()
    ){
        BottomNavigation(
            backgroundColor = MaterialTheme.colors.onPrimary,
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
                            painter = painterResource (  if(selected) it.iconSelected else it.iconUnselected ),
                            contentDescription = "BTN",
                            modifier =  Modifier
                                .size(45.dp),
                            tint = if(!selected) Color.Unspecified else MaterialTheme.colors.secondaryVariant
                        )
                    },
                    selectedContentColor = Color.Unspecified,
                    alwaysShowLabel = false)
            }

        }
    }


}



