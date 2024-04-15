package com.example.weatherappcompose.ui.screens.home.views.cards

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherappcompose.R
import com.example.weatherappcompose.ui.theme.HourlyCardColorDisabled
import com.example.weatherappcompose.ui.theme.Linear3
import com.example.weatherappcompose.ui.theme.PrimaryDark
import com.example.weatherappcompose.ui.theme.SecondaryDark
import com.example.weatherappcompose.ui.theme.Typography
import com.example.weatherappcompose.ui.utils.ext.uvToString

@Composable
fun UVCard(modifier: Modifier, uv: Int){
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
            modifier = Modifier.padding(20.dp)
        ) {
            Row {
                Icon(
                    modifier = Modifier
                        .width(17.dp)
                        .height(17.dp),
                    painter = painterResource(id = R.drawable.ic_sun),
                    contentDescription = null,
                    tint = SecondaryDark
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    style = Typography.bodySmall.copy(color = SecondaryDark),
                    text = stringResource(R.string.uv_index_header)
                )
            }

            Text(
                modifier = Modifier.padding(top = 15.dp),
                style = Typography.titleLarge.copy(color = PrimaryDark),
                text = uv.toString()
            )
            Text(
                style = Typography.titleSmall.copy(color = PrimaryDark),
                text = stringResource(id = uvToString(uv))
            )
            DrawUVLine(uv)
        }
    }
}


@Composable
fun DrawUVLine(uv: Int){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp)
            .height(height = 6.dp)
            .background(
                brush = Brush.horizontalGradient(listOf(Color(0xFF3759B1), Color(0xFFE64393))),
                shape = RoundedCornerShape(size = 5.dp)
            )
            .drawWithContent {
                val radiusCircle = 3.dp.toPx()
                val radiusBorder = 4.dp.toPx()
                val offsetCircle = Offset(((size.width - radiusBorder) / 13) * uv, radiusCircle)
                drawContent()
                drawCircle(
                    color = Color.White,
                    radius = radiusCircle,
                    center = offsetCircle
                )
                drawCircle(
                    color = HourlyCardColorDisabled,
                    radius = radiusBorder,
                    style = Stroke(width = 1.dp.toPx()),
                    center = offsetCircle
                )
            }
    )
}

@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun UVCard_Preview() {
    Row(modifier = Modifier.fillMaxSize()) {
        UVCard(
            modifier = Modifier.fillMaxWidth(0.5f),
            uv = 3
        )
    }
}