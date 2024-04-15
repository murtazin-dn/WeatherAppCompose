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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherappcompose.R
import com.example.weatherappcompose.domain.weather.model.Precipitation
import com.example.weatherappcompose.ui.theme.Linear3
import com.example.weatherappcompose.ui.theme.PrimaryDark
import com.example.weatherappcompose.ui.theme.SecondaryDark
import com.example.weatherappcompose.ui.theme.Typography

@Composable
fun RainFallCard(modifier: Modifier, precipitation: Precipitation){
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
                    painter = painterResource(id = R.drawable.ic_rainfall),
                    contentDescription = null,
                    tint = SecondaryDark
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    style = Typography.bodySmall.copy(color = SecondaryDark),
                    text = stringResource(R.string.rainfall_header)
                )

            }
            Column {
                Text(
                    modifier = Modifier.padding(top = 10.dp),
                    style = Typography.titleLarge.copy(color = PrimaryDark),
                    text = precipitation.precipitation.toString() + precipitation.unit
                )
                Text(
                    style = Typography.titleSmall.copy(color = PrimaryDark),
                    text = stringResource(R.string.rainfall_bottom1)
                )
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        modifier = Modifier.align(Alignment.BottomStart),
                        style = Typography.labelSmall.copy(color = PrimaryDark),
                        text = "${ precipitation.precipitationSum } ${precipitation.unit}" +
                                stringResource(R.string.rainfall_bottom2)
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
fun RainFallCard_Preview() {
    Row(modifier = Modifier.fillMaxSize()) {
        RainFallCard(
            modifier = Modifier.fillMaxWidth(0.5f),
            precipitation = Precipitation(
                precipitation = 0.00,
                precipitationSum = 0.80,
                unit = "mm"
            )
        )
    }
}