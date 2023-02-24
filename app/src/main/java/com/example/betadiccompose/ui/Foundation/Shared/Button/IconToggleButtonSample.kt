package compose.material.theme

import androidx.compose.animation.animateColorAsState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@Composable
fun IconToggleButtonSample(
    modifier: Modifier = Modifier,
    checked: Boolean,
    ClickCheck:(it:Boolean)->Unit
){

    var icon =  Icons.Rounded.Favorite
    IconToggleButton(
        checked = checked,
        onCheckedChange = {
            ClickCheck(it)
        }) {

        val tint by animateColorAsState(
            //if (checked)  Color(0xFFEC407A) else Color.Black
            if (checked)  Color(0xFFEC407A) else MaterialTheme.colors.secondaryVariant.copy(alpha = 0.6f)
        )
        Icon(
            icon,
            modifier = modifier,
            contentDescription = "Localized description",
            tint = tint)
    }
}