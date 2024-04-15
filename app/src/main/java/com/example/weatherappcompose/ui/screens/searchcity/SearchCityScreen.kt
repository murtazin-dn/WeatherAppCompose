package com.example.weatherappcompose.ui.screens.searchcity

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weatherappcompose.R
import com.example.weatherappcompose.data.settings.model.Geo
import com.example.weatherappcompose.domain.geocodingweather.model.GeocodingWeather
import com.example.weatherappcompose.ui.screens.searchcity.model.SearchCityEvent
import com.example.weatherappcompose.ui.screens.searchcity.model.SearchCityViewState
import com.example.weatherappcompose.ui.screens.searchcity.views.SearchCityDisplay
import com.example.weatherappcompose.ui.theme.Linear3
import com.example.weatherappcompose.ui.theme.PrimaryDark
import com.example.weatherappcompose.ui.theme.TertiaryDark
import com.example.weatherappcompose.ui.theme.Typography
import com.example.weatherappcompose.ui.utils.ext.SearchView

@Composable
fun SearchCityScreen(
    navController: NavController,
    viewModel: SearchCityViewModel,
    onBack: (Geo) -> Unit
){
    val viewState = viewModel.searchCityState.observeAsState()
    val searchTextState = remember { mutableStateOf("") }
    LaunchedEffect(key1 = searchTextState.value){
        viewModel.obtainEvent(event = SearchCityEvent.SearchCity(searchTextState.value))
    }
    Column(
        modifier = Modifier.background(Linear3)
    ) {
        SearchCityHeader(searchTextState){
            navController.popBackStack()
        }

        when(val state = viewState.value){
            SearchCityViewState.SearchError -> SearchCityError{}
            is SearchCityViewState.SearchLoaded -> SearchCityDisplay(state = state){ geo ->
                onBack.invoke(
                    Geo(
                        long = geo.long,
                        lat = geo.lat
                    )
                )
            }
            is SearchCityViewState.CitySelected -> onBack.invoke(state.geo)
            SearchCityViewState.SearchLoading -> SearchCityLoading()
            SearchCityViewState.SearchNoData -> SearchCityNoData()
            null -> TODO()
        }
    }


}

@Composable
fun SearchCityNoData() {
    Box(modifier = Modifier.fillMaxSize()){
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "No data",
            style = Typography.titleLarge.copy(color = PrimaryDark, fontWeight = FontWeight.SemiBold)
        )
    }
}

@Composable
fun SearchCityError(reload: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.error),
            style = Typography.titleLarge.copy(color = PrimaryDark, fontWeight = FontWeight.SemiBold)
        )
        Button(onClick = reload) {
            Text(
                text = stringResource(id = R.string.reload),
                style = Typography.titleSmall.copy(color = PrimaryDark)
            )
        }
    }
}
@Composable
fun SearchCityLoading() {
    Box(modifier = Modifier.fillMaxSize()){
        CircularProgressIndicator(
            modifier = Modifier
                .padding(20.dp)
                .align(Alignment.Center)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchCityHeader(
    state: MutableState<String>,
    back: () -> Unit
){
    Column(
        modifier = Modifier.background(Linear3)
    ) {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.weather),
                    style = Typography.titleMedium.copy(color = PrimaryDark)
                )
            },
            navigationIcon = {
                IconButton(
                    onClick = back,
                    content = {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_next),
                            contentDescription = "",
                            modifier = Modifier
//                                .size(24.dp)
                                .padding(5.dp)
                                .rotate(180f),
                            tint = TertiaryDark
                        )
                    }
                )
            },
            windowInsets = WindowInsets.systemBars,
            colors = TopAppBarColors(
                containerColor = Color.Transparent,
                scrolledContainerColor = Color.Transparent,
                navigationIconContentColor = TertiaryDark,
                titleContentColor = PrimaryDark,
                actionIconContentColor = TertiaryDark
            )
        )
        SearchView(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 10.dp),
            state = state
        )
    }
}


@Composable
@Preview
fun SearchCityHeader_Preview(){
    SearchCityHeader(mutableStateOf("")){}
}
@Composable
@Preview
fun SearchCityError_Preview(){
    SearchCityError{}
}
@Composable
@Preview
fun SearchCityNoData_Preview(){
    SearchCityNoData()
}
@Composable
@Preview
fun SearchCityLoading_Preview(){
    SearchCityLoading()
}
@Composable
@Preview
fun SearchCityDisplay_Preview(){
    SearchCityDisplay(
        SearchCityViewState.SearchLoaded(
            listOf(
                GeocodingWeather(
                    name = "Orenburg",
                    country = "Russia",
                    lat = 0.0,
                    long = 0.0
                ),
                GeocodingWeather(
                    name = "Orenburgskaya oblast",
                    country = "Russia",
                    lat = 0.0,
                    long = 0.0
                ),
                GeocodingWeather(
                    name = "Orsk",
                    country = "Russia",
                    lat = 0.0,
                    long = 0.0
                ),
                GeocodingWeather(
                    name = "Saracktash",
                    country = "Russia",
                    lat = 0.0,
                    long = 0.0
                ),
                GeocodingWeather(
                    name = "Novotroick",
                    country = "Russia",
                    lat = 0.0,
                    long = 0.0
                )
            )
        )
    ){}
}
