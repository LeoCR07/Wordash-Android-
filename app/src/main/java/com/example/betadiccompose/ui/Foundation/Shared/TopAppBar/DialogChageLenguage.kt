package com.example.betadiccompose.ui.Foundation.Shared

import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.window.Popup
import com.example.betadiccompose.R
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel


@Composable
fun DialogLenguage(
    show: Boolean,
    dimisissDialog: () -> Unit,
    viewModel: VocabularyViewModel
) {

    val interactionSource = remember { MutableInteractionSource() }

    if(show) {

        Popup(
            alignment = Alignment.TopStart,
            onDismissRequest = { dimisissDialog() },
            offset = IntOffset(20, 150)
        ) {
            Card(
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .width(250.dp)
                    .height(160.dp)
                    .padding(2.dp),
                border = BorderStroke(0.5.dp, MaterialTheme.colors.onSecondary),
                backgroundColor = MaterialTheme.colors.onPrimary,
            ) {

                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxSize()
                ) {


                    Box() {
                        Icon(
                            // = it.icon,
                            //imageVector = if(selected) it.icon else it.iconSelect,
                            painter = painterResource(R.drawable.flag_states),
                            // imageVector =  it.iconSelect,
                            contentDescription = "BTN",
                            modifier = Modifier
                                .clickable(interactionSource = interactionSource,indication = null) {
                                    viewModel.saveLearnLenguage("English")
                                    viewModel.getListOfAlllevel()
                                    viewModel.getListOfAllCategories()
                                    viewModel.getListOfSentesFromRoom()
                                    viewModel.getListOfWordsFromRoom()
                                    dimisissDialog()
                                }
                                .size(100.dp)
                                .clip(CircleShape)
                            ,
                            tint = Color.Unspecified)

                        if (viewModel.GetLearnLenguage() == "English") {
                            Icon(
                                // = it.icon,
                                //imageVector = if(selected) it.icon else it.iconSelect,
                                painter = painterResource(R.drawable.cheque),
                                // imageVector =  it.iconSelect,
                                contentDescription = "BTN",
                                modifier = Modifier
                                    .size(25.dp)
                                    .clip(CircleShape),
                                tint = Color.Unspecified)
                        }


                    }
                    Box() {

                        Icon(
                            // = it.icon,
                            //imageVector = if(selected) it.icon else it.iconSelect,
                            painter = painterResource(R.drawable.flag_spain),
                            // imageVector =  it.iconSelect,
                            contentDescription = "BTN",
                            modifier = Modifier
                                .clickable(interactionSource = interactionSource,indication = null){

                                    viewModel.saveLearnLenguage("Spanish")
                                    dimisissDialog()

                                    viewModel.getListOfAlllevel()
                                    viewModel.getListOfAllCategories()
                                    viewModel.getListOfSentesFromRoom()
                                    viewModel.getListOfWordsFromRoom()


                                }
                                .size(100.dp)
                                .clip(CircleShape),
                            tint = Color.Unspecified)

                        if (viewModel.GetLearnLenguage() == "Spanish") {
                            Icon(
                                // = it.icon,
                                //imageVector = if(selected) it.icon else it.iconSelect,
                                painter = painterResource(R.drawable.cheque),
                                // imageVector =  it.iconSelect,
                                contentDescription = "BTN",
                                modifier = Modifier
                                    .clickable { }
                                    .size(25.dp)
                                    .clip(CircleShape),
                                tint = Color.Unspecified)
                        }
                    }


                }
            }
        }

    }
    }




