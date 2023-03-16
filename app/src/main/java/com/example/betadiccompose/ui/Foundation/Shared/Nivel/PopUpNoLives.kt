package com.example.betadiccompose.ui.Foundation.Shared.Nivel

import android.app.Activity
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.os.CountDownTimer
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.example.betadiccompose.R
import com.example.betadiccompose.ui.Foundation.Shared.BtnSuper
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.OnUserEarnedRewardListener
import com.google.android.gms.ads.admanager.AdManagerAdRequest
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit


@Composable
fun PopUpNoLives(
    show: Boolean,
    dimisissDialog: () -> Unit,
    viewModel: VocabularyViewModel,
    time :Boolean = true

) {
    /******* ads *************/

    var date by remember {
        mutableStateOf(viewModel.GetFisrtTime())
    }

    val context = LocalContext.current

    var reloj by remember{
        mutableStateOf("")
    }



    LaunchedEffect(key1 =true){
        if(date != "null"){

            var total = 1800000
            var rest = total - viewModel.CalculeteDates()



        if(rest>=total){
            rest = 0
        }

        println("valor restante ${rest}")


            Temporizador.startTimer(
                timeInMillis = rest,
                interval = 1000, // 1 segundo
                onTick = { it ->
                    reloj = formatDuration(it)
                },
                onFinish = {
                    CoroutineScope(Dispatchers.IO).launch {
                        viewModel.SetFirstTime("null")
                        viewModel.plusLives()
                        dimisissDialog()
                    }


                // El temporizador ha finalizado
                }
            )
        }

    }





    if(show) {

        Popup(
            alignment = Alignment.TopStart,
            onDismissRequest = { dimisissDialog() },
            offset = IntOffset(40, 500)
        ) {
            Card(
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .width(380.dp)
                    .height(460.dp)
                    .padding(2.dp),
                border = BorderStroke(5.dp, MaterialTheme.colors.onSecondary),
                backgroundColor = MaterialTheme.colors.background,
               // backgroundColor = Color(0xFFFFFFFF)
            ) {
                Column(modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally) {

                    Spacer(modifier = Modifier.height(30.dp))

                    Text(
                        color = MaterialTheme.colors.secondaryVariant,
                        fontWeight = FontWeight.SemiBold,
                        text = "No hay mas vidas",
                        fontSize = 30.sp)

                    Box( contentAlignment = Alignment.Center){
                        Icon(
                            painter = painterResource(R.drawable.vidas),
                            contentDescription = "BTN",
                            modifier = Modifier
                                .size(200.dp),
                            tint = Color.Unspecified)

                        Text(
                            text = "0",
                            fontSize = 60.sp,
                            color = Color.White)
                    }

                    if(time){
                        Text(
                            color = MaterialTheme.colors.secondaryVariant,
                            fontSize = 15.sp,
                            text =  "Tu vidas se recargaran en  $reloj")
                    }else{
                        Text(
                            color = MaterialTheme.colors.secondaryVariant,
                            fontSize = 15.sp,
                            text =  "Tu vidas se recargaran en 30:00" +
                                    "")
                    }




                    Spacer(modifier = Modifier.height(20.dp))

                    //Ad button
                    BtnSuper(
                        outlineColor = MaterialTheme.colors.secondaryVariant,
                        fontSize = 20.sp,
                        FontColor = Color.White,
                        title = "Ver anuncio",
                        color =  Color(0xFF23A4DF),
                        onClick = {
                            println("vidasvidas")
                                  viewModel.ShowRewarded(context as Activity, onClick = {
                                      dimisissDialog()
                                  })
                        },
                        icon = R.drawable.healthcare,
                        modifier = Modifier
                            .height(60.dp)
                            .fillMaxWidth()
                            .padding(10.dp, 6.dp, 10.dp, 6.dp),
                        icon_size = 50.dp)


                    BtnSuper(
                        fontSize = 15.sp,
                        Outline = false,
                        IsIcon = false,
                        FontColor = MaterialTheme.colors.secondaryVariant,
                        title = "Cancelar",
                        color = MaterialTheme.colors.background,
                        onClick = { dimisissDialog() },
                        modifier = Modifier
                            .height(60.dp)
                            .fillMaxWidth()
                            .padding(10.dp, 6.dp, 10.dp, 6.dp))

                }

            }
        }

    }
}

fun formatDuration(durationMillis: Long): String {
    val minutes = (durationMillis % 3600000) / 60000
    val seconds = (durationMillis % 60000) / 1000

    // Formatear como cadena
    return String.format("%02d:%02d",minutes, seconds)
}

object Temporizador {
    private var countDownTimer: CountDownTimer? = null
    private var isRunning = false

    fun startTimer(timeInMillis: Long, interval: Long, onTick: (Long) -> Unit, onFinish: () -> Unit) {
        countDownTimer?.cancel()
        isRunning = true
        countDownTimer = object : CountDownTimer(timeInMillis, interval) {
            override fun onTick(millisUntilFinished: Long) {
                onTick.invoke(millisUntilFinished)
            }

            override fun onFinish() {
                onFinish.invoke()
                isRunning = false
            }
        }
        countDownTimer?.start()
    }

    fun stopTimer() {
        countDownTimer?.cancel()
        isRunning = false
    }

    fun isRunning(): Boolean {
        return isRunning
    }
}
