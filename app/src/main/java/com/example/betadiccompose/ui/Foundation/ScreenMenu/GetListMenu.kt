package com.example.betadiccompose.Foundation.Menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.betadiccompose.Domain.Provider.Provider
import com.example.betadiccompose.R
import com.example.betadiccompose.data.network.model.DataMenu


@Composable
fun GetListMenu (onMediaClick: (DataMenu) -> Unit, provider: Provider) {

    //val dato = DataMenu(1,"Hola", R.drawable.ic_launcher_foreground)
    val lst  = provider.createListMenu()
    LazyColumn(
        Modifier
            .fillMaxHeight(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(28.dp),
        state = rememberLazyListState()
    ){

        items(lst){item->

            GetItemMenu(onClick =  {onMediaClick(item)},item = item)
        }

    }
}