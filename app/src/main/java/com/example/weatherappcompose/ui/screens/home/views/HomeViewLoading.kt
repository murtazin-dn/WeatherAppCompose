package com.example.weatherappcompose.ui.screens.home.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherappcompose.R
import com.example.weatherappcompose.ui.theme.Linear2

@Composable
fun HomeViewLoading(){
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Image(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
        )
        Card(
            modifier = Modifier
                .align(Alignment.Center),
            colors = CardDefaults.cardColors(containerColor = Linear2),
            shape = RoundedCornerShape(22.dp)
        ) {
            CircularProgressIndicator(
                modifier = Modifier.padding(20.dp)
            )
        }
    }
}

@Preview()
@Composable
fun HomeViewLoading_Preview(){
    HomeViewLoading()
}
