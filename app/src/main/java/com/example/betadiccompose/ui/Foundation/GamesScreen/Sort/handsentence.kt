package com.example.betadiccompose.ui.Foundation.GamesScreen.Sort

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.betadiccompose.ui.Foundation.Shared.Button.ButtonSample


@Composable
fun handsentence(
    lstCompleta: MutableState<List<String>>,
    onclick: (String, Int) -> Unit,
    lstId: ArrayList<Int>
){

    var cindex by remember{ mutableStateOf(0) }

    LazyHorizontalGrid(
        rows = GridCells.Adaptive(70.dp),
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
    ) {

        lstCompleta.value.forEachIndexed { index, item ->
            cindex = 0 //1
            for (i in 0 until lstId.size) {
                if (lstId[i] != index) {
                    cindex++ //2
                }

                if (cindex == lstId.size) {
                    item() {
                        ButtonSample(
                            item = item,
                            modifier = Modifier
                                .padding(1.dp),
                            onClick = {onclick(item,index)}
                        )
                    }
                }
            }

            if (lstId.isEmpty()) {

                item() {
                    ButtonSample(
                        item = item,
                        modifier = Modifier
                            .padding(1.dp),
                        onClick = {onclick(item,index)}
                    )
                }
            }
        }

    }
}