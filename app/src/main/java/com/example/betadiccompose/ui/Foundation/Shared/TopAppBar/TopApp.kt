package com.example.betadiccompose.ui.Foundation.Shared

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.betadiccompose.R
import com.example.betadiccompose.data.network.model.DataUser
import com.example.betadiccompose.ui.Foundation.Shared.TopAppBar.topAppBarIcon
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel


@Composable
fun TopApp(
    viewModel: VocabularyViewModel,
    opcion:Int = 0,
    title: String? = null,
    navToSomeWhere:()->Unit = {}) {
    /* 0 - details
    1 - account
    2 - settings*/

    var showAlertDialog by remember { mutableStateOf(false) }

    var datauser by remember {
        mutableStateOf(DataUser())
    }

    viewModel.GetDataUser()

    datauser = viewModel.lstdatauser.value

    if(showAlertDialog){
        ExitDialog(
            hideAlertDialog = { showAlertDialog = false },
            showAlertDialog = {showAlertDialog = true },
            texto = "Estas seguro que  desea cerrar sesion ?",
            onBack = {}
        )
    }


    var opendialog = remember { mutableStateOf(false) }
    DialogChageLenguage(
        viewModel = viewModel,
        show = opendialog.value,
        dimisissDialog =  {opendialog.value = false})


    TopAppBar(
        backgroundColor =Color.White,
        title = {  if(title!=null) Text(text = title) },
        navigationIcon = {

            Spacer(modifier = Modifier.width(10.dp))

            if(opcion == 3){
                Icon(
                    Icons.Rounded.ArrowBack,
                    contentDescription = "BTN",
                    modifier = Modifier
                        .clickable { }
                        .size(35.dp))
            }else{

                if(viewModel.GetLearnLenguage() == "English"){
                    Icon(
                        painter = painterResource (  R.drawable.flag_states),
                        contentDescription = "BTN",
                        modifier = Modifier
                            .clickable { opendialog.value = true }
                            .size(35.dp)
                            .clip(CircleShape),
                        tint = Color.Unspecified)
                }else{
                    Icon(
                        painter = painterResource (  R.drawable.flag_spain),
                        contentDescription = "BTN",
                        modifier = Modifier
                            .clickable { opendialog.value = true }
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

                        topAppBarIcon(icon = R.drawable.exp, value = datauser.exp)
                        topAppBarIcon(icon = R.drawable.vidas, value = datauser.level)
                        topAppBarIcon(icon = R.drawable.star_on, value = datauser.stars)

                    }
                }else if(opcion == 1){


                    Icon(
                        painter = painterResource (R.drawable.sign_out),
                        contentDescription = null,
                        modifier = Modifier
                            .clickable { showAlertDialog = true }
                            .size(28.dp),
                        tint = Color.Unspecified
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                }else if(opcion == 2){
                    Icon(
                        painter = painterResource (R.drawable.settings),
                        contentDescription = null,
                        modifier = Modifier
                            .clickable { navToSomeWhere() }
                            .size(33.dp),
                        tint = Color.Unspecified
                    )

                    Spacer(modifier = Modifier.width(10.dp))
                }
            }



        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 0.dp, 0.dp, 1.dp)
        // .clip(RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp))
        // .clip(CutCornerShape(0.dp,0.dp,5.dp,5.dp))
    )
}

