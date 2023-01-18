package com.example.betadiccompose.ui.Foundation.GamesScreen.Sort

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.betadiccompose.ui.Foundation.Shared.Button.OutlinedButtonSample

@Composable
public fun handwriting(
    lstCompleta: MutableState<List<String>>,
    onclick: (String, Int) -> Unit,
    lstId: ArrayList<Int>
){

    var cindex by remember{ mutableStateOf(0) }

    LazyVerticalGrid(
        columns = GridCells.Adaptive(80.dp),
        modifier = Modifier.padding(6.dp)
    ) {

        //lstCompleta es la palabra completo
        lstCompleta.value.forEachIndexed { index, item ->
            cindex = 0 //1

            for (i in 0 until lstId.size) {
                if (lstId[i] != index) {
                    cindex++ //2
                }

                if (cindex == lstId.size) {
                    item() {
                        OutlinedButtonSample(
                            onMediaClick = {onclick(item,index)},
                            word = item,
                            modifier = Modifier
                                .padding(1.dp),
                            BorderColor = Color(0xFF0CB1B1),
                            fontsize = 22.sp
                        )
                    }
                }
            }


            //Cuando no hay ningun boton apretado
            if (lstId.isEmpty()) {

                item() {
                    OutlinedButtonSample(
                        onMediaClick = {onclick(item,index)},
                        word = item,
                        modifier = Modifier
                            .padding(1.dp),
                        BorderColor = Color(0xFF0CB1B1),
                        fontsize = 22.sp
                    )
                }
            }
        }

    }
}

