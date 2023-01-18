package compose.material.theme

import androidx.compose.animation.animateColorAsState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.betadiccompose.data.database.model.DataMyFavoriteWord
import com.example.betadiccompose.data.network.model.DataWorld

@Preview
@Composable
fun IconButtonSample() {
    IconButton(onClick = { /* doSomething() */ }) {
        Icon(Icons.Filled.Favorite, contentDescription = "Localized description")
    }
}


@Composable
fun IconToggleButtonSample(
    modifier: Modifier = Modifier,
    checked: Boolean,
    ClickCheck:(it:Boolean)->Unit
){

  //  var checked = false


    var icon =  Icons.Rounded.Favorite


  //  println("img 0: ${item.Img}")



    IconToggleButton(
        checked = checked,
        onCheckedChange = {
            ClickCheck(it)
                          },

        modifier = modifier) {

        val tint by animateColorAsState(
            if (checked)  Color(0xFFEC407A) else Color(0x99B0BEC5)
        )
        Icon(
            icon,
            contentDescription = "Localized description",
            tint = tint)
    }
}