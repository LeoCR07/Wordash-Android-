package com.example.betadiccompose.ui.Foundation.Shared

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ChevronLeft
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.unit.dp
import com.example.betadiccompose.R
import com.example.betadiccompose.ui.Foundation.Shared.TopAppBar.topAppBarIcon
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel
import java.util.*


@Composable
fun TopApp(
    viewModel: VocabularyViewModel,
    opcion:Int = 0,
    title: String? = null,
    navToSettings:()->Unit = {}){


    /*
    0 - details
    1 - account
    2 - settings
    4 - store
    */

    val interactionSource = remember { MutableInteractionSource() }

    viewModel.getDataUser()

    var datauser =  viewModel.lstdatauser.value
    var opendialog = remember { mutableStateOf(false) }

    DialogLenguage(
        viewModel = viewModel,
        show = opendialog.value,
        dimisissDialog =  {opendialog.value = false})

    TopAppBar(
        backgroundColor = MaterialTheme.colors.onPrimary,
        title = {  if(title!=null) Text(
            text = title.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
            ,color= MaterialTheme.colors.secondaryVariant ) },
        navigationIcon =  {


            if(opcion == 3){
                Icon(
                    Icons.Rounded.ChevronLeft,
                    contentDescription = "BTN",
                    modifier = Modifier
                        .clickable(interactionSource = interactionSource,indication = null)
                        {navToSettings() }
                        .size(35.dp))
            }else{
                Spacer(modifier = Modifier.width(10.dp))
                if(viewModel.GetLearnLenguage() == "English"){
                    Icon(
                        painter = painterResource (  R.drawable.flag_states),
                        contentDescription = "BTN",
                        modifier = Modifier
                            .clickable(interactionSource = interactionSource,indication = null) { opendialog.value = true }
                            .size(35.dp)
                            .clip(CircleShape),
                        tint = Color.Unspecified)
                }else{
                    Icon(
                        painter = painterResource (  R.drawable.flag_spain),
                        contentDescription = "BTN",
                        modifier = Modifier
                            .clickable(interactionSource = interactionSource,indication = null) { opendialog.value = true }
                            .size(35.dp)
                            .clip(CircleShape),
                        tint = Color.Unspecified)
                }
            }


        },
        actions = {

            if(title==null){
                if(opcion == 0 ) {
                    Row(modifier = Modifier
                        .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround) {

                        topAppBarIcon(icon = R.drawable.exp, value = datauser.exp,codigo = Color(0xFF85C458))

                        topAppBarIcon(icon = R.drawable.heart, value = datauser.lives, codigo = Color(
                            0xFFFF5D5D
                        )
                        )

                        topAppBarIcon(icon = R.drawable.crown, value = datauser.crowns, codigo = Color(
                            0xFFFFC107
                        )
                        )

                    }
                }else if(opcion == 1){

                    Icon(
                        painter = painterResource (R.drawable.setting),
                        contentDescription = null,
                        modifier = Modifier
                            .clickable(interactionSource = interactionSource,indication = null) {
                                navToSettings() }
                            .size(30.dp),
                        tint = Color.Unspecified
                    )


                    /*
                    Spacer(modifier = Modifier.width(30.dp))
                    Icon(
                        painter = painterResource (R.drawable.sign_out),
                        contentDescription = null,
                        modifier = Modifier
                            .clickable(interactionSource = interactionSource,indication = null) {
                                showAlertDialog = true
                            }
                            .size(28.dp),
                        tint = MaterialTheme.colors.secondaryVariant
                    )*/

                    Spacer(modifier = Modifier.width(10.dp))

                }else if(opcion == 2){
                    Icon(
                        painter = painterResource (R.drawable.settings),
                        contentDescription = null,
                        modifier = Modifier
                            .clickable(interactionSource = interactionSource,indication = null) {
                                navToSettings() }
                            .size(33.dp),
                        tint = Color.Unspecified
                    )

                    Spacer(modifier = Modifier.width(10.dp))
                }
                else if(opcion == 4){
                    topAppBarIcon(
                        icon = R.drawable.crown,
                        value = datauser.crowns,
                        codigo = Color(0xFFF8F7F7)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                }
            }



        },
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxWidth()
            .background(MaterialTheme.colors.onSecondary)
            .padding(0.dp, 0.dp, 0.dp, 1.dp)
    )
}

