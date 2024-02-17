package com.example.weatherappcompose.ui.screens.home.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherappcompose.R
import com.example.weatherappcompose.ui.theme.Linear2
import com.example.weatherappcompose.ui.theme.PrimaryDark
import com.example.weatherappcompose.ui.theme.Typography

@Composable
fun HomeViewError(){
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Image(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
        )
        Card(
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .fillMaxHeight(0.2f)
                .align(Alignment.Center),
            colors = CardDefaults.cardColors(containerColor = Linear2),
            shape = RoundedCornerShape(22.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Error",
                    style = Typography.titleLarge.copy(color = PrimaryDark, fontWeight = FontWeight.SemiBold)
                )
                Button(onClick = { /*TODO*/ }) {
                    Text(
                        text = "Reload",
                        style = Typography.titleSmall.copy(color = PrimaryDark)
                    )
                }

            }
        }
    }
}

@Preview()
@Composable
fun HomeViewError_Preview(){
    HomeViewError()
}