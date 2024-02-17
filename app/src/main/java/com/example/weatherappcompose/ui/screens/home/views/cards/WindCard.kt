package com.example.weatherappcompose.ui.screens.home.views.cards
import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherappcompose.R
import com.example.weatherappcompose.data.network.weather.model.response.testData
import com.example.weatherappcompose.ui.screens.home.model.HomeViewState
import com.example.weatherappcompose.ui.theme.Linear3
import com.example.weatherappcompose.ui.theme.SecondaryDark
import com.example.weatherappcompose.ui.theme.Typography
import kotlin.math.PI

@Composable
fun WindCard(modifier: Modifier, windKph: Double, windDegree: Int){
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
                    painter = painterResource(id = R.drawable.ic_wind),
                    contentDescription = null,
                    tint = SecondaryDark
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    style = Typography.bodySmall.copy(color = SecondaryDark),
                    text = "WIND"
                )
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 5.dp)
            ) {
                DrawWindCycle(windKph, windDegree)
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow),
                    contentDescription = null,
                    modifier = Modifier.graphicsLayer {
                        rotationZ = windDegree.toFloat()
                    }
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = windKph.toString(), style = Typography.titleSmall.copy(Color.White))
                    Text(text = "km/h", style = Typography.labelSmall.copy(Color.White))
                }
            }
        }
    }
}


@Composable
private fun DrawWindCycle(windKph: Double, windDegree: Int){
    val tmN = rememberTextMeasurer()
    val tmS = rememberTextMeasurer()
    val tmE = rememberTextMeasurer()
    val tmW = rememberTextMeasurer()
    Canvas(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 12.dp)){
        val width = size.width
        val height = size.height
        drawDivisions()
        drawCardinalPoints(tmN, tmS, tmW, tmE)
    }
}

private fun DrawScope.drawCardinalPoints(
    tmN: TextMeasurer, tmS: TextMeasurer, tmW: TextMeasurer, tmE: TextMeasurer
){
    val width = size.width
    val height = size.height

    val sizeN = tmN.measure(text = AnnotatedString("N"),
        style = Typography.titleSmall.copy(color = Color.White)).size
    val sizeS = tmS.measure(text = AnnotatedString("S"),
        style = Typography.titleSmall.copy(color = Color.White)).size
    val sizeW = tmW.measure(text = AnnotatedString("W"),
        style = Typography.titleSmall.copy(color = Color.White)).size
    val sizeE = tmE.measure(text = AnnotatedString("E"),
        style = Typography.titleSmall.copy(color = Color.White)).size

    drawText(
        textMeasurer = tmN,
        style = Typography.titleSmall.copy(color = Color.White),
        text = "N",
        topLeft = Offset(((width-sizeN.width)/2), 15f)
    )
    drawText(
        textMeasurer = tmS,
        style = Typography.titleSmall.copy(color = Color.White),
        text = "S",
        topLeft = Offset(((width-sizeS.width)/2), height - 15f - sizeS.height)
    )
    drawText(
        textMeasurer = tmW,
        style = Typography.titleSmall.copy(color = Color.White),
        text = "W",
        topLeft = Offset(20f, (height/2) - (sizeW.height/2))
    )
    drawText(
        textMeasurer = tmE,
        style = Typography.titleSmall.copy(color = Color.White),
        text = "E",
        topLeft = Offset(width - sizeE.width - 20f, (height/2) - (sizeS.height/2))
    )
}

private fun DrawScope.drawDivisions(){
    val width = size.width
    val height = size.height

    val strokeWidth = 20f

    val interval = ((PI * width) / 742).toFloat()
    val offset = interval * 3

    val intervals = floatArrayOf(
        interval, 0f,
        interval, offset,
        interval, offset,
        interval, offset,
        interval, offset,
        interval, offset,
        interval, offset,
        interval, offset,
        interval, offset,
        interval, offset,
        interval, offset,
        interval, offset,
        interval, offset,
        interval, offset,
        interval, offset,
        interval, 0f
    )



    val stroke = Stroke(width = strokeWidth,
        pathEffect = PathEffect.dashPathEffect(intervals, 0f)
    )

    drawCircle(
        color = SecondaryDark,
        radius = (width / 2) - (strokeWidth / 2),
        center = center,
        style = stroke

    )
}

@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun WindCard_Preview() {
    val currentWeather = HomeViewState.WeatherLoaded(testData).weather.current
    Row(modifier = Modifier.fillMaxSize()) {
        WindCard(
            modifier = Modifier.fillMaxWidth(0.5f),
            windKph = currentWeather.wind_kph,
            windDegree = currentWeather.wind_degree
        )
    }
}