package com.example.betadiccompose.ui.screens.Menu

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.betadiccompose.R
import com.example.betadiccompose.ui.Foundation.Shared.BtnSuper
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel


@Composable
fun SelectLanguage(VocaVM: VocabularyViewModel) {
    MyDropdown()
}


@Composable
fun language(icono :Int) {
    androidx.compose.material.Icon(
        painter = painterResource(icono),
        contentDescription = "BTN",
        modifier = Modifier
            .size(105.dp)
            .clip(CircleShape),
        tint = Color.Unspecified)
}


@Composable
fun MyDropdown() {
    val items = listOf("Item 1", "Item 2", "Item 3")
    var selectedIndex by remember { mutableStateOf(0) }
    var expanded by remember { mutableStateOf(false) }



    Column (
        verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally){

    Spacer(modifier = Modifier.height(25.dp))

        // Titulo
        Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .width(340.dp)
            .clickable(onClick = { expanded = true })
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.Red)
            ,) {
            Text(
                text = "Hablo ",
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.width(6.dp))

            Text(
                text = items[selectedIndex],
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.width(6.dp))

            Box(){
                Icon(
                    Icons.Rounded.ArrowDropDown,
                    contentDescription = "drop",
                    modifier = Modifier.size(40.dp),
                    tint = MaterialTheme.colors.secondaryVariant
                )
            }
        }

        // Drop
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            DropdownMenu(
                expanded = expanded,
                offset = DpOffset(110.dp,0.dp),
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .height(400.dp)
                    .width(200.dp)
            ) {
                items.forEachIndexed { index, item ->
                    DropdownMenuItem(onClick = {
                        selectedIndex = index
                        expanded = false
                    }) {
                        Text(
                            text = item,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth())
                    }
                }
            }
        }

        // Second titile
        Text(
            text = "Quiero aprender",
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.ExtraBold,
            fontSize = 22.sp,
            color = Color.LightGray,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(10.dp))


        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically){

            language(R.drawable.flag_states)
            Spacer(modifier = Modifier.width(20.dp))
            language(R.drawable.flag_spain)
        }


        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                // Código omitido
                BtnSuper(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                        .padding(10.dp),
                    color = Color.Green,
                    IsIcon = false,
                    title = "continuar", FontColor =Color.White, onClick = { /*TODO*/ }
                )
            }
        }






    }


}