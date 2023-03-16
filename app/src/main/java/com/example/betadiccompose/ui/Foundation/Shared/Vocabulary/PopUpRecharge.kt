package com.example.betadiccompose.ui.Foundation.Shared.Vocabulary

import android.app.Activity
import android.content.ContentValues
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.example.betadiccompose.R
import com.example.betadiccompose.ui.Foundation.Shared.BtnSuper
import com.example.betadiccompose.ui.Foundation.Shared.Nivel.Temporizador
import com.example.betadiccompose.ui.Foundation.Shared.Nivel.formatDuration
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.OnUserEarnedRewardListener
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun PopUpRecharge(
    show: Boolean,
    dimisissDialog: () -> Unit,
    viewModel: VocabularyViewModel
) {

    var date by remember {
        mutableStateOf(viewModel.GetFisrtTime())
    }

    var rewardedAd: RewardedAd? = null
    var adRequest = AdRequest.Builder().build()
    val context = LocalContext.current

    var reloj by remember{
        mutableStateOf("")
    }


    RewardedAd.load(
        context,
        "ca-app-pub-3940256099942544/5224354917",
        adRequest,
        object : RewardedAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                Log.d(ContentValues.TAG, adError?.toString())
                rewardedAd = null
            }

            override fun onAdLoaded(ad: RewardedAd) {
                Log.d(ContentValues.TAG, "Ad was loaded.")
                rewardedAd = ad
            }
        })

    rewardedAd?.fullScreenContentCallback = object: FullScreenContentCallback() {
        override fun onAdClicked() {
            println("Click")
        }

        override fun onAdDismissedFullScreenContent() {
            // Called when ad is dismissed.
            // Set the ad reference to null so you don't show the ad a second time.
            //Log.d(TAG, "Ad dismissed fullscreen content.")
            println("Se cerro")
            rewardedAd = null
        }

        override fun onAdFailedToShowFullScreenContent(p0: AdError) {
            // Called when ad fails to show.
            //Log.e(TAG, "Ad failed to show fullscreen content.")
            println(" ${p0.message}")

            rewardedAd = null
        }

        override fun onAdImpression() {
            // Called when an impression is recorded for an ad.
            println("impresion")
        }

        override fun onAdShowedFullScreenContent() {
            // Called when ad is shown.
            println("Ad showed fullscreen content.")
        }
    }


    LaunchedEffect(key1 =true){
        if(date != "null"){

            var total = 420000
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
                }
            )
        }

    }




    if(show) {

        Popup(
            alignment = Alignment.TopStart,
            onDismissRequest = { false },
            offset = IntOffset(40, 500)
        ) {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF9800)),
                contentAlignment = Alignment.Center){
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
                            modifier = Modifier.fillMaxWidth(),
                            color = MaterialTheme.colors.secondaryVariant,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center,
                            text = "Accede a esta sección desbloqueándola",
                            fontSize = 30.sp)

                        Icon(
                            Icons.Outlined.Lock,
                            contentDescription = "BTN",
                            modifier = Modifier
                                .size(150.dp),
                            tint =  Color(0xFFFF9800)
                        )

                        Text(
                            modifier = Modifier.fillMaxWidth()
                                .padding(6.dp),
                            color = MaterialTheme.colors.secondaryVariant,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Center,
                            text =  "Disfruta de 7 minutos de acceso al contenido después de ver un breve " +
                                    "anuncio, Los anuncios son la forma en que esta aplicación se mantiene gratuita")




                        Spacer(modifier = Modifier.height(20.dp))

                        //Ad button
                        BtnSuper(
                            outlineColor = MaterialTheme.colors.secondaryVariant,
                            fontSize = 20.sp,
                            FontColor = Color.White,
                            title = "Ver anuncio",
                            color =  Color(0xFF4CAF50),
                            onClick = {

                                rewardedAd?.let { ad ->
                                    ad.show(context as Activity, OnUserEarnedRewardListener { rewardItem ->

                                        CoroutineScope(Dispatchers.IO).launch {
                                            viewModel.plusLives()
                                            dimisissDialog()
                                        }

                                    })
                                } ?: run {
                                    Toast.makeText(context,"el anuncio esta cargando, toca de nuevo",
                                        Toast.LENGTH_LONG).show()
                                    Log.d(ContentValues.TAG, "The rewarded ad wasn't ready yet.")
                                }
                            },
                            icon = R.drawable.thunder,
                            modifier = Modifier
                                .height(60.dp)
                                .fillMaxWidth()
                                .padding(10.dp, 6.dp, 10.dp, 6.dp),
                            icon_size = 50.dp)


                    }

                }
            }
        }

    }
}