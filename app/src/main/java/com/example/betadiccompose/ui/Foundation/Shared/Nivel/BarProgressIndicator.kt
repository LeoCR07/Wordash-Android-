package com.example.betadiccompose.ui.Foundation.Shared.Nivel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun BarProgressIndicator() {
    LinearProgressIndicator(
        modifier = Modifier
            .height(22.dp)
            .width(310.dp)
            .clip(RoundedCornerShape(8.dp,8.dp,8.dp,8.dp)),
        progress = 0.2f,
       // backgroundColor = Color.Red,
    )
}


@Composable
fun customProgressBar(porcentaje: Float) {
    // in this method we are creating a column
    Column(
        // in this column we are specifying modifier to
        // align the content within the column
        // to center of the screen.

        // on below line we are specifying horizontal
        // and vertical alignment for the content of our column
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // in this column we are creating a variable
        // for the progress of our progress bar.
        var progress = porcentaje

        // on the below line we are creating a box.
        Box(
            // inside this box we are adding a modifier
            // to add rounded clip for our box with
            // rounded radius at 15
            modifier = Modifier
                .clip(RoundedCornerShape(15.dp))
                // on below line we are specifying
                // height for the box
                .height(20.dp)

                // on below line we are specifying
                // background color for box.
                .background(Color.LightGray.copy(alpha = 0.4f))

                // on below line we are
                // specifying width for the box.
                .width(300.dp)
        ) {
            // in this box we are creating one more box.
            Box(
                // on below line we are adding modifier to this box.
                modifier = Modifier
                    // on below line we are adding clip \
                    // for the modifier with round radius as 15 dp.
                    .clip(RoundedCornerShape(15.dp))

                    // on below line we are
                    // specifying height as 30 dp
                    .height(20.dp)

                    // on below line we are adding background
                    // color for our box as brush
                    .background(
                        // on below line we are adding brush for background color.
                        Brush.verticalGradient(
                            // in this color we are specifying a gradient
                            // with the list of the colors.
                            listOf(
                                // on below line we are adding two colors.
                                Color(0xFF4CAF50),
                                Color(0xFF8BC34A),
                                Color(0xFFCDDC39)
                            )
                        )
                    )
                    // on below line we are specifying width for the inner box
                    .width(300.dp * progress / 100)
            )

        }

    }

}