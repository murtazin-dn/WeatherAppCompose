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
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherappcompose.R
import com.example.weatherappcompose.data.network.weather.model.response.base.Astro
import com.example.weatherappcompose.data.network.weather.model.response.testData
import com.example.weatherappcompose.ui.screens.home.model.HomeViewState
import com.example.weatherappcompose.ui.screens.home.model.SunState
import com.example.weatherappcompose.ui.theme.Linear3
import com.example.weatherappcompose.ui.theme.PrimaryDark
import com.example.weatherappcompose.ui.theme.SecondaryDark
import com.example.weatherappcompose.ui.theme.SunRiseColor
import com.example.weatherappcompose.ui.theme.SunSetColor
import com.example.weatherappcompose.ui.theme.TertiaryDark
import com.example.weatherappcompose.ui.theme.TertiaryLight
import com.example.weatherappcompose.ui.theme.Typography
import com.example.weatherappcompose.ui.utils.ext.fillWidthOfParent
import com.example.weatherappcompose.ui.utils.ext.hhToHHInt
import kotlin.math.PI
import kotlin.math.cos


@Composable
fun SunRiseCard(modifier: Modifier, astro: Astro){
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
                    painter = painterResource(id = R.drawable.ic_sunrise),
                    contentDescription = null,
                    tint = SecondaryDark
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    style = Typography.bodySmall.copy(color = SecondaryDark),
                    text = "SUNRISE"
                )
            }
            Text(
                style = Typography.titleLarge.copy(color = PrimaryDark),
                text = astro.sunrise
            )
            DrawSunLine(sunRise = astro.sunrise, sunSet = astro.sunset)
            Box(modifier = Modifier.fillMaxSize()){
                Text(
                    modifier = Modifier.align(Alignment.BottomStart),
                    style = Typography.labelSmall.copy(color = PrimaryDark),
                    text = "Sunset: ${astro.sunset}"
                )
            }

        }
    }
}


@Composable
fun DrawSunLine(sunRise: String, sunSet: String){
    Box(
        modifier = Modifier
            .fillWidthOfParent(19.dp)
            .fillMaxWidth()
            .height(60.dp)
            .drawWithContent {
                val height = size.height
                val width = size.width
                val pointsSet = mutableListOf<Offset>()
                val pointsRise = mutableListOf<Offset>()

                fun getY(x: Int) =
                    (cos((2 * PI * x) / width) * 50 + (height / 2)).toFloat()

                for (x in 0..width.toInt()) {
                    var y = getY(x)

                    (1..10).forEach {
                        y++
                        if (y > (height / 2)) {
                            pointsSet.add(Offset(x.toFloat(), y))
                        } else {
                            pointsRise.add(Offset(x.toFloat(), y))
                        }
                    }

                }
//                for (x in 0..width.toInt()) {
//                    val y = cos((2 * PI * x) / width) * 50 + (height / 2)
//                    if(y > (height / 2)){
//                        pointsSet.add(Offset(x.toFloat(), y.toFloat()))
//                    }else{
//                        pointsRise.add(Offset(x.toFloat(), y.toFloat()))
//                    }
//
//                }

                drawPoints(
                    points = pointsSet,
                    strokeWidth = 1.dp.toPx(),
                    pointMode = PointMode.Points,
                    color = SunSetColor
                )


                drawPoints(
                    points = pointsRise,
                    strokeWidth = 1.dp.toPx(),
                    pointMode = PointMode.Points,
                    color = SunRiseColor
                )
                drawLine(
                    color = TertiaryLight,
                    start = Offset(y = (height / 2), x = 0f),
                    end = Offset(y = (height / 2), x = width),
                    strokeWidth = 1.dp.toPx()
                )

                val sunState = hhToHHInt(sunRise, sunSet)
                println("sunState: $sunState")

                val xOffset = when (sunState.sunState) {
                    SunState.BEFORE_SUN_RISE -> (width / 4) * sunState.sunScale
                    SunState.AFTER_SUN_RISE -> ((width / 2) * sunState.sunScale) + (width / 4)
                    SunState.AFTER_SUN_SET -> ((width / 4) * sunState.sunScale) + (width * (1f / 4))
                }
                println("x: $xOffset")
                drawCircle(
                    brush = Brush.radialGradient(
                        listOf(TertiaryDark, Color.Transparent),
                        Offset(xOffset, getY(xOffset.toInt()) + 5),
                        radius = 12.dp.toPx()
                    ),
                    radius = 12.dp.toPx(),
                    center = Offset(xOffset, getY(xOffset.toInt()) + 5)
                )
                drawCircle(
                    color = Color.White,
                    radius = 4.dp.toPx(),
                    center = Offset(xOffset, getY(xOffset.toInt()) + 5)
                )


            }
    )
}

@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun SunRiseCard_Preview() {
    Row(modifier = Modifier.fillMaxSize()) {
        SunRiseCard(
            modifier = Modifier.fillMaxWidth(0.5f),
            astro = HomeViewState.WeatherLoaded(testData).weather.forecast.forecastday.first().astro
        )
    }

}