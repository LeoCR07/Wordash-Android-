package com.example.betadiccompose.ui.Foundation.Shared

import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.*
import androidx.compose.material.Icon
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.window.Popup
import com.example.betadiccompose.R
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel


@Composable
fun DialogChageLenguage(
    show: Boolean,
    dimisissDialog: () -> Unit,
    viewModel: VocabularyViewModel
) {



    if(show){

        Popup(
            alignment = Alignment.TopStart,
            onDismissRequest = { dimisissDialog()},
            offset = IntOffset(20, 150)
        ){
            Card(
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    //   .customDialogModifier(CustomDialogPosition.TOP)
                    .width(250.dp)
                    .height(160.dp)
                    .padding(2.dp),
                border = BorderStroke(1.5.dp, Color.LightGray)
                // .fillMaxWidth(0.95f)
                //    .border(1.dp,color = Color.Magenta, shape = RoundedCornerShape(15.dp))
            ) {

                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                ) {


                    Box(){
                        Icon(
                            // = it.icon,
                            //imageVector = if(selected) it.icon else it.iconSelect,
                            painter = painterResource (  R.drawable.flag_states),
                            // imageVector =  it.iconSelect,
                            contentDescription = "BTN",
                            modifier = Modifier
                                .clickable {
                                    viewModel.SaveLearnLenguage("English")
                                    dimisissDialog()
                                }
                                .size(100.dp)
                                .clip(CircleShape),
                            tint = Color.Unspecified)

                        if(viewModel.GetLearnLenguage() =="English"){
                            Icon(
                                // = it.icon,
                                //imageVector = if(selected) it.icon else it.iconSelect,
                                painter = painterResource (  R.drawable.cheque),
                                // imageVector =  it.iconSelect,
                                contentDescription = "BTN",
                                modifier = Modifier
                                    .clickable { }
                                    .size(25.dp)
                                    .clip(CircleShape),
                                tint = Color.Unspecified)
                        }


                    }
                    Box(){

                        Icon(
                            // = it.icon,
                            //imageVector = if(selected) it.icon else it.iconSelect,
                            painter = painterResource (  R.drawable.flag_spain),
                            // imageVector =  it.iconSelect,
                            contentDescription = "BTN",
                            modifier = Modifier
                                .clickable {
                                    viewModel.SaveLearnLenguage("Spanish")
                                    dimisissDialog()
                                }
                                .size(100.dp)
                                .clip(CircleShape),
                            tint = Color.Unspecified)

                        if(viewModel.GetLearnLenguage() =="Spanish"){
                            Icon(
                                // = it.icon,
                                //imageVector = if(selected) it.icon else it.iconSelect,
                                painter = painterResource (  R.drawable.cheque),
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


        /*
        Dialog(

            onDismissRequest = { dimisissDialog()}) {
            Card(
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    //   .customDialogModifier(CustomDialogPosition.TOP)

                    .width(250.dp)
                    .height(180.dp)
                    .padding(2.dp)
                   // .fillMaxWidth(0.95f)
                //    .border(1.dp,color = Color.Magenta, shape = RoundedCornerShape(15.dp))
            ) {

                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                ) {


                    Box(){
                        Icon(
                            // = it.icon,
                            //imageVector = if(selected) it.icon else it.iconSelect,
                            painter = painterResource (  R.drawable.flag_states),
                            // imageVector =  it.iconSelect,
                            contentDescription = "BTN",
                            modifier = Modifier
                                .clickable { }
                                .size(100.dp)
                                .clip(CircleShape),
                            tint = Color.Unspecified)

                        Icon(
                            // = it.icon,
                            //imageVector = if(selected) it.icon else it.iconSelect,
                            painter = painterResource (  R.drawable.cheque),
                            // imageVector =  it.iconSelect,
                            contentDescription = "BTN",
                            modifier = Modifier
                                .clickable { }
                                .size(25.dp)
                                .clip(CircleShape),
                            tint = Color.Unspecified)

                    }


                    Icon(
                        // = it.icon,
                        //imageVector = if(selected) it.icon else it.iconSelect,
                        painter = painterResource (  R.drawable.flag_spain),
                        // imageVector =  it.iconSelect,
                        contentDescription = "BTN",
                        modifier = Modifier
                            .clickable { }
                            .size(100.dp)
                            .clip(CircleShape),
                        tint = Color.Unspecified)

                }
            }
        }

        */
    }
        /*
        AlertDialog(
            onDismissRequest = { dimisissDialog()},
            title = { Text(text = "Dialogo")},
            confirmButton = {
                Button(onClick = { dimisissDialog()}) {
                    Text(text = "OK")
                }
               },
            dismissButton = {
                Button(onClick = { dimisissDialog()}) {
                Text(text = "Cancelar")
            }
         })
        }
    */

    }

