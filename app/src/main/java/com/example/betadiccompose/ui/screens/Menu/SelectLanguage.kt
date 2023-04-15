package com.example.betadiccompose.ui.screens.Menu

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.betadiccompose.R
import com.example.betadiccompose.Runtime.MyGame
import com.example.betadiccompose.ui.Foundation.Shared.BtnSuper
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel
import java.util.*


@Composable
fun SelectLanguage(viewmodel: VocabularyViewModel, NavToLogin:()->Unit) {


    MyGame(viewModel = viewmodel, content = {

        val interactionSource = remember { MutableInteractionSource() }


        /**  code of language **/
        var code by remember {
            mutableStateOf(viewmodel.GetCode())
        }

        var BtnContinue by remember {
            mutableStateOf(viewmodel.GetSettings().ContinueBtn[code]!!.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            })
        }

        var IWantTxt by remember {
            mutableStateOf(viewmodel.GetSettings().IWantToLearn[code]!!.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            })
        }

        var MyLanguage by remember {
            mutableStateOf(viewmodel.GetSettings().MyLanguage[code]!!.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            })
        }


        val lst = viewmodel.GetFilesLocalLanguages()

        var value by remember {
            mutableStateOf(0)
        }


        val defaultLocale = Locale.getDefault()
        val codes = defaultLocale.language


        val sortedList = lst.sortedBy { it.local }



        sortedList.forEachIndexed { index, e ->
            if (e.code == code) {
                value = index
            }
        }

        var title by remember {
            mutableStateOf(sortedList[value].local)
        }

        var expanded by remember { mutableStateOf(false) }




        LazyColumn(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            item {

               Spacer(modifier = Modifier.height(25.dp))
                Text(
                    color = MaterialTheme.colors.secondaryVariant.copy(0.7f),
                    text = MyLanguage,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )

                // Titulo
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .width(340.dp)
                        .clickable (interactionSource = interactionSource, indication = null) {
                            expanded = true
                        }
                        .padding(16.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .border(
                            BorderStroke(0.5.dp, MaterialTheme.colors.secondaryVariant),
                            RoundedCornerShape(16.dp)
                        ),
                ) {

                    Text(
                        color = MaterialTheme.colors.secondaryVariant,
                        text = title,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    Box() {
                        Icon(
                            Icons.Rounded.ArrowDropDown,
                            contentDescription = "drop",
                            modifier = Modifier.size(40.dp),
                            tint = MaterialTheme.colors.secondaryVariant
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                    DropdownMenu(
                        expanded = expanded,
                        offset = DpOffset(110.dp, 0.dp),
                        onDismissRequest = { expanded = false },
                        modifier = Modifier
                            .height(400.dp)
                            .width(200.dp)
                    ) {
                        sortedList.forEachIndexed { index, item ->
                            DropdownMenuItem(
                                onClick = {
                                //value =
                                title = sortedList[index].local
                                viewmodel.SaveLocalLanguage(sortedList[index].language)

                                BtnContinue =viewmodel.GetSettings().ContinueBtn[sortedList[index].code]!!.replaceFirstChar {
                                    if (it.isLowerCase()) it.titlecase(
                                        Locale.getDefault()
                                    ) else it.toString()
                                }


                                MyLanguage =viewmodel.GetSettings().MyLanguage[sortedList[index].code]!!.replaceFirstChar {
                                    if (it.isLowerCase()) it.titlecase(
                                        Locale.getDefault()
                                    ) else it.toString()
                                }


                                IWantTxt =viewmodel.GetSettings().IWantToLearn[sortedList[index].code]!!.replaceFirstChar {
                                    if (it.isLowerCase()) it.titlecase(
                                        Locale.getDefault()
                                    ) else it.toString()
                                }

                                expanded = false

                            }) {
                                Text(
                                    text = item.local,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                )
                            }
                        }
                    }
                }

                // Drop

               // Spacer(Modifier.height(100.dp))
            }



            item {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = IWantTxt,
                        modifier = Modifier.fillMaxWidth(),
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 22.sp,
                        color = MaterialTheme.colors.secondaryVariant.copy(0.7f),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(10.dp))


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        language(viewmodel,interactionSource)
                        Spacer(modifier = Modifier.width(20.dp))
                        language(viewmodel,interactionSource)
                    }
                }

            }


            item{
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Código omitido
                        BtnSuper(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(70.dp)
                                .padding(10.dp),
                            outlineColor = androidx.compose.material.MaterialTheme.colors.secondaryVariant,
                            IsIcon = false,
                            color =androidx.compose.material.MaterialTheme.colors.onPrimary,
                            title = BtnContinue,
                            fontSize = 15.sp,
                            Outline = true,
                            FontColor = androidx.compose.material.MaterialTheme.colors.secondaryVariant,
                            onClick = {
                                NavToLogin()
                            }
                        )

                    }
                }
            }






            // Second titile


        }


    })

}



@Composable
fun language(
    viewModel: VocabularyViewModel,
    interactionSource: MutableInteractionSource,) {

    var selectedLanguage by remember {
        mutableStateOf(viewModel.GetLearnLenguage())
    }


    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.Top,
        modifier = Modifier
            .fillMaxWidth()
    ) {


        Box() {
            androidx.compose.material.Icon(
                painter = painterResource(R.drawable.flag_states),
                contentDescription = "BTN",
                modifier = Modifier
                    .clickable(interactionSource = interactionSource, indication = null) {
                        viewModel.ChangeLanguage("English")
                        selectedLanguage = "English"
                    }
                    .size(100.dp)
                    .clip(CircleShape),
                tint = Color.Unspecified)

            if (selectedLanguage == "English") {
                androidx.compose.material.Icon(
                    painter = painterResource(R.drawable.cheque),
                    contentDescription = "BTN",
                    modifier = Modifier
                        .size(25.dp)
                        .clip(CircleShape),
                    tint = Color.Unspecified
                )
            }


        }
        Box() {

            androidx.compose.material.Icon(
                painter = painterResource(R.drawable.flag_spain),
                contentDescription = "BTN",
                modifier = Modifier
                    .clickable(interactionSource = interactionSource, indication = null) {
                        viewModel.ChangeLanguage("Spanish")
                        selectedLanguage = "Spanish"
                    }
                    .size(100.dp)
                    .clip(CircleShape),
                tint = Color.Unspecified)

            if ( selectedLanguage == "Spanish") {
                androidx.compose.material.Icon(
                    painter = painterResource(R.drawable.cheque),
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


