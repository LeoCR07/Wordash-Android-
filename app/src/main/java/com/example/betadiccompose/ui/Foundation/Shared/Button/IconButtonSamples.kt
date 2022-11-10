package compose.material.theme

import androidx.compose.animation.animateColorAsState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun IconButtonSample() {
    IconButton(onClick = { /* doSomething() */ }) {
        Icon(Icons.Filled.Favorite, contentDescription = "Localized description")
    }
}

@Composable
fun IconToggleButtonSample(modifier: Modifier = Modifier) {

    var checked by remember { mutableStateOf(false) }
    IconToggleButton(

        checked = checked,
        onCheckedChange = { checked = it },
                modifier = modifier) {

        val tint by animateColorAsState(
            if (checked) Color(0xFFEC407A) else Color(0xFFB0BEC5))
        Icon(
            Icons.Rounded.FavoriteBorder,
            contentDescription = "Localized description",
            tint = tint)
    }
}