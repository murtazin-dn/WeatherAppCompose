package com.example.weatherappcompose.ui.screens.searchcity.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherappcompose.R
import com.example.weatherappcompose.domain.geocodingweather.model.GeocodingWeather
import com.example.weatherappcompose.ui.screens.searchcity.model.SearchCityViewState
import com.example.weatherappcompose.ui.theme.Linear2
import com.example.weatherappcompose.ui.theme.PrimaryDark
import com.example.weatherappcompose.ui.theme.Typography

@Composable
fun SearchCityDisplay(
    state: SearchCityViewState.SearchLoaded,
    onSelect: (GeocodingWeather) -> Unit
){

    LazyColumn(
        modifier = Modifier.background(Color.Transparent)
    ){
        items(state.search){geo ->
            SearchCityItem(
                Modifier.clickable {
                    onSelect.invoke(geo)
                },
                geo
            )
        }
    }
}

@Composable
fun SearchCityItem(
    modifier: Modifier = Modifier,
    geo: GeocodingWeather
){
    Card(
        modifier = modifier
            .padding(vertical = 5.dp, horizontal = 10.dp),
        shape = RoundedCornerShape(22.dp),
        border = BorderStroke(1.dp, Color(0x80FFFFFF)),
        colors = CardDefaults.cardColors(
            containerColor = Linear2,
            disabledContainerColor = Linear2
        ),
    ) {
        Row(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    style = Typography.titleLarge.copy(color = PrimaryDark),
                    text = geo.name,
                )
                Text(
                    style = Typography.titleSmall.copy(color = PrimaryDark),
                    text = geo.country
                )
            }
            Icon(
                modifier = Modifier
                    .size(20.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_plus),
                contentDescription = null,
                tint = PrimaryDark
            )

        }

    }
}

@Composable
@Preview
fun SearchCityItem_Preview(){
    SearchCityItem(
        geo = GeocodingWeather(
            name = "Orenburg",
            country = "Russia",
            lat = 0.432342,
            long = 0.37248
        )
    )
}
@Composable
@Preview
fun SearchCityDisplay_Preview(){

}