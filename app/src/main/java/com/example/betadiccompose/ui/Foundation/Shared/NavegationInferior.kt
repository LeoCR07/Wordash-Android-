package com.example.betadiccompose.ui.Foundation.Shared

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.betadiccompose.ui.model.ItemsMenu


@Composable
fun navegationinferior(menu: List<ItemsMenu>, current: String?, onclickNav: (ItemsMenu) -> Unit){
    BottomAppBar(
        modifier = Modifier
            .height(65.dp)
            .clip(RoundedCornerShape(10.dp,0.dp,0.dp,10.dp)),
        cutoutShape = MaterialTheme.shapes.small.copy(CornerSize(percent = 50))){

        BottomNavigation(modifier = Modifier
            .padding(0.dp,0.dp,60.dp,0.dp)) {

            menu.forEach {
                BottomNavigationItem(
                    selected = current == it.route,
                    onClick = { if(it.route!=current)onclickNav(it) },
                    icon = { Icon(it.icon, contentDescription = it.title) },
                    label = { Text(text = it.title) },
                    alwaysShowLabel = false)
            }

        }
    }
}



