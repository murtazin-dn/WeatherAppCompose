package com.example.weatherappcompose.ui.screens.home.views.cards


import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherappcompose.R
import com.example.weatherappcompose.data.network.weather.model.response.testData
import com.example.weatherappcompose.ui.screens.home.model.HomeViewState
import com.example.weatherappcompose.ui.theme.Linear3
import com.example.weatherappcompose.ui.theme.PrimaryDark
import com.example.weatherappcompose.ui.theme.SecondaryDark
import com.example.weatherappcompose.ui.theme.Typography

@Composable
fun FeelsLikeCard(modifier: Modifier, feelsLike: Double){
    Card(
        modifier = modifier
            .padding(7.dp)
            .aspectRatio(1f),
        colors = CardDefaults.cardColors(
            containerColor = Linear3,
            disabledContainerColor = Linear3
        ),
        border = BorderStroke(1.dp, Color(0x80FFFFFF)),
        shape = RoundedCornerShape(22.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Row {
                Icon(
                    modifier = Modifier
                        .width(17.dp)
                        .height(17.dp),
                    painter = painterResource(id = R.drawable.ic_thermometer),
                    contentDescription = null,
                    tint = SecondaryDark
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    style = Typography.bodySmall.copy(color = SecondaryDark),
                    text = "FEELS LIKE"
                )

            }
            Column {
                Text(
                    modifier = Modifier.padding(top = 10.dp),
                    style = Typography.titleLarge.copy(color = PrimaryDark),
                    text = "$feelsLike°"
                )
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        modifier = Modifier.align(Alignment.BottomStart),
                        style = Typography.labelSmall.copy(color = PrimaryDark),
                        text = "Similar to the actual temperature"
                    )
                }
            }

        }
    }
}


@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun FeelsLikeCard_Preview() {
    val weather = HomeViewState.WeatherLoaded(testData).weather.current
    Row(modifier = Modifier.fillMaxSize()) {
        FeelsLikeCard(
            modifier = Modifier.fillMaxWidth(0.5f),
            feelsLike = weather.feelslike_c
        )
    }
}