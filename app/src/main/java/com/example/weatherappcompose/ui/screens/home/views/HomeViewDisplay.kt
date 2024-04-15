package com.example.weatherappcompose.ui.screens.home.views

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.example.weatherappcompose.R
import com.example.weatherappcompose.domain.weather.model.AirQuality
import com.example.weatherappcompose.domain.weather.model.Current
import com.example.weatherappcompose.domain.weather.model.HourlyDaily
import com.example.weatherappcompose.domain.weather.model.Precipitation
import com.example.weatherappcompose.domain.weather.model.Sun
import com.example.weatherappcompose.domain.weather.model.Weather
import com.example.weatherappcompose.domain.weather.model.Wind
import com.example.weatherappcompose.ui.screens.home.model.HomeViewState
import com.example.weatherappcompose.ui.screens.home.views.cards.AirQualityCard
import com.example.weatherappcompose.ui.screens.home.views.cards.FeelsLikeCard
import com.example.weatherappcompose.ui.screens.home.views.cards.HumidityCard
import com.example.weatherappcompose.ui.screens.home.views.cards.RainFallCard
import com.example.weatherappcompose.ui.screens.home.views.cards.SunRiseCard
import com.example.weatherappcompose.ui.screens.home.views.cards.UVCard
import com.example.weatherappcompose.ui.screens.home.views.cards.WindCard
import com.example.weatherappcompose.ui.screens.home.views.tabs.Forecast
import com.example.weatherappcompose.ui.screens.home.views.tabs.ForecastTabs
import com.example.weatherappcompose.ui.theme.Black30
import com.example.weatherappcompose.ui.theme.Linear2
import com.example.weatherappcompose.ui.theme.PrimaryDark
import com.example.weatherappcompose.ui.theme.SecondaryDark
import com.example.weatherappcompose.ui.theme.Typography
import com.example.weatherappcompose.ui.utils.ext.VerticalDivider
import com.example.weatherappcompose.ui.utils.ext.getCurrentFraction
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeViewDisplay(
    state: HomeViewState.WeatherLoaded,
    navigateToSearchCity: () -> Unit,
    navigateToSettings: () -> Unit
){
    BoxWithConstraints (
        modifier = Modifier.fillMaxSize()
    ) {
        val boxWithConstraintsScope = this
        val scaffoldState = rememberBottomSheetScaffoldState()
        val tabItems = listOf(
            stringResource(id = R.string.hourly_forecast),
            stringResource(id = R.string.weekly_forecast)
        )
        val pagerState = rememberPagerState(pageCount = { tabItems.size })



        BottomSheetScaffold(
            scaffoldState = scaffoldState,
            sheetContent = {
                HorizontalPager(
                    modifier = Modifier.fillMaxHeight(0.8f),
                    state = pagerState
                ) { page ->
                    when(page){
                        0 -> ForecastTab(state, ForecastTabs.HOURLY_FORECAST)
                        1 -> ForecastTab(state, ForecastTabs.WEEKLY_FORECAST)
                        else -> throw RuntimeException("Unknown page index $page")
                    }
                }
            },
            sheetContainerColor = Linear2,
//            sheetPeekHeight = 325.dp,
            sheetPeekHeight = boxWithConstraintsScope.maxHeight / 10 * 4,
            sheetShape = RoundedCornerShape(topStart = 44.dp, topEnd = 44.dp),
            sheetDragHandle = {
                DragHandle(pagerState, tabItems){

                }
            }
        ){
//            HomeTop(state)
            HomeTop(state, scaffoldState, boxWithConstraintsScope, navigateToSearchCity, navigateToSettings)
        }
    }
}

@OptIn(ExperimentalMotionApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun HomeTop(
    state: HomeViewState.WeatherLoaded,
    scaffoldState: BottomSheetScaffoldState,
    boxWithConstraintsScope: BoxWithConstraintsScope,
    navigateToSearchCity: () -> Unit,
    navigateToSettings: () -> Unit,
    ) {
    var progress by remember {
        mutableStateOf(0f)
    }
    var backgroundState by remember {
        mutableStateOf(Color.Transparent)
    }
    with(LocalDensity.current) {
        LaunchedEffect(scaffoldState.bottomSheetState.requireOffset()) {
            progress = scaffoldState.getCurrentFraction(boxWithConstraintsScope.maxHeight.roundToPx().toFloat())
        }
    }
    val context = LocalContext.current
    val motionScene = remember {
        context.resources.openRawResource(R.raw.motion_scene).readBytes().decodeToString()
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
        )
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 100.dp),
            painter = painterResource(id = R.drawable.background_house),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundState)
        )
        Column() {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 51.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MotionLayout(
                    motionScene = MotionScene(content = motionScene),
                    modifier = Modifier
                        .fillMaxSize(),
                    progress = progress,
//                    debug = EnumSet.of(MotionLayoutDebugFlags.SHOW_ALL)
                ) {
                    backgroundState = motionColor("box", "color")

                    Box(
                        modifier = Modifier
                            .layoutId("box")
                    )
                    Text(
                        modifier = Modifier
                            .layoutId("city_text")
                        ,
                        style = Typography.titleLarge.copy(color = PrimaryDark),
                        text = state.weather.city,
                    )
                    Text(
                        modifier = Modifier
                            .layoutId("temp_text")
                            .wrapContentSize(unbounded = true, align = Alignment.TopEnd),
                        style = Typography.titleLarge.copy(
                            color = PrimaryDark,
//                            fontWeight = lerp(FontWeight(100), FontWeight(600), progress),
                            fontWeight = FontWeight(motionInt("temp_text", "textWeight")),
                            fontSize = motionFontSize("temp_text", "textSize"),
                            lineHeight = 70.sp,
                            letterSpacing = 0.37.sp,
                        ),
                        text = "${state.weather.current.temp}°"
                    )
                    VerticalDivider(
                        modifier = Modifier
                            .height(20.dp)
                            .padding(top = 5.dp, start = 5.dp, end = 5.dp)
                            .layoutId("divider"),
                        thickness = 3.dp,
                        color = SecondaryDark
                    )
                    Text(
                        modifier = Modifier
                            .layoutId("label_text"),
                        style = Typography.titleSmall.copy(color = SecondaryDark),
                        text = stringResource(state.weather.current.weatherDescription)
                    )
                    Text(
                        modifier = Modifier.layoutId("hl_temp_text"),
                        style = Typography.titleSmall.copy(color = PrimaryDark),
                        text = "H:${state.weather.current.tempMax}°  " +
                                "L:${state.weather.current.tempMin}°"
                    )
                    Icon(
                        modifier = Modifier
                            .size(30.dp)
                            .layoutId("ic_search")
                            .clickable {
                                navigateToSearchCity.invoke()
                            },
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_plus),
                        contentDescription = null,
                        tint = Color.White
                    )
                    Icon(
                        modifier = Modifier
                            .size(30.dp)
                            .layoutId("ic_settings")
                            .clickable {
                                navigateToSettings.invoke()
                            },
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_settings),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        }
    }
}


@Composable
fun ForecastTab(
    state: HomeViewState.WeatherLoaded,
    forecastTabs: ForecastTabs
){
    LazyColumn {
        item {
            Forecast(state = state, forecastTabs = forecastTabs)
        }
        item {
            AirQualityCard(
                modifier = Modifier.fillMaxWidth(),
                airQuality = state.weather.current.airQuality)
        }
        item{
            Row (
                modifier = Modifier.fillMaxWidth()
            ) {
                UVCard(
                    modifier = Modifier.weight(1f),
                    uv = state.weather.current.uvIndex
                )
                SunRiseCard(
                    modifier = Modifier.weight(1f),
                    sun = state.weather.current.sun
                )
            }
        }
        item{
            Row (
                modifier = Modifier.fillMaxWidth()
            ) {
                WindCard(
                    modifier = Modifier.weight(1f),
                    wind = state.weather.current.wind
                )
                RainFallCard(
                    modifier = Modifier.weight(1f),
                    precipitation = state.weather.current.precipitation
                )
            }
        }
        item{
            Row (
                modifier = Modifier.fillMaxWidth()
            ) {
                FeelsLikeCard(
                    modifier = Modifier.weight(1f),
                    feelsLike = state.weather.current.feelsLike
                )
                HumidityCard(
                    modifier = Modifier.weight(1f),
                    humidity = state.weather.current.humidity,
                    dewPoint = state.weather.current.dewPoint
                )
            }
        }
    }
}



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DragHandle(
    pagerState: PagerState,
    tabItems: List<String>,
    onClick: () -> Unit
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier
                .padding(vertical = 8.dp),
            color = Black30,
            shape = MaterialTheme.shapes.extraLarge
        ) {
            Box(
                Modifier
                    .size(
                        width = 48.dp,
                        height = 5.dp
                    )
            )
        }
        Tabs(pagerState, tabItems)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Tabs(
    pagerState: PagerState,
    tabItems: List<String>
) {
    val scope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        containerColor = Color.Transparent,
        divider = {
            Divider(
                thickness = 1.dp,
                color = Color(0x4DFFFFFF)
            )
        },
        indicator = @Composable { tabPositions ->
            if (pagerState.currentPage < tabPositions.size) {
                TabRowIndicator(
                    Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage])
                )
            }
        }
    ) {
        tabItems.forEachIndexed { index, item ->
            Tab(
                modifier = Modifier.height(30.dp),
                selected = index == pagerState.currentPage,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                text = {
                    Text(
                        style = Typography.bodySmall.copy(color = SecondaryDark),
                        text = item
                    )
                }
            )
        }
    }
}

@Composable
fun TabRowIndicator(
    modifier: Modifier,
    height: Dp = 3.dp
){
    Box(
        modifier
            .fillMaxWidth()
            .height(height)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0x00FFFFFF),
                        Color(0x4DFFFFFF),
                        Color(0x00FFFFFF)
                    )
                )
            )
    )
}


@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomeViewDisplay_Preview() {
    HomeViewDisplay(
        state = HomeViewState.WeatherLoaded(
            Weather(
                city = "Orenburg",
                current = Current(
                    temp = 10,
                    tempMin = 5,
                    tempMax = 15,
                    weatherDescription = R.string.cloudy,
                    airQuality = AirQuality(
                        aqi = 42,
                        pm10 = 14.6,
                        pm2_5 = 9.0,
                        co = 201.0,
                        no2 = 4.4,
                        so2 = 1.8,
                        o3 = 85.0
                    ),
                    uvIndex = 4,
                    sun = Sun(
                        sunRise = "05:44 AM",
                        sunSet = "08:20 PM"
                    ),
                    wind = Wind(
                        speed = 9.1,
                        direction = 288,
                        speedUnit = "km/h"
                    ),
                    precipitation = Precipitation(
                        precipitation = 0.00,
                        precipitationSum = 0.80,
                        unit = "mm"
                    ),
                    humidity = 78,
                    dewPoint = 14,
                    feelsLike = 12,
                    icon = R.drawable.day_116
                ),
                hourly = listOf(
                    HourlyDaily(
                        time = "1 AM",
                        icon = R.drawable.day_116,
                        temp = 6
                    ),
                    HourlyDaily(
                        time = "2 AM",
                        icon = R.drawable.day_116,
                        temp = 7
                    ),
                    HourlyDaily(
                        time = "3 AM",
                        icon = R.drawable.day_116,
                        temp = 8
                    ),
                    HourlyDaily(
                        time = "4 AM",
                        icon = R.drawable.day_116,
                        temp = 9
                    ),
                    HourlyDaily(
                        time = "5 AM",
                        icon = R.drawable.day_116,
                        temp = 10
                    ),
                    HourlyDaily(
                        time = "6 AM",
                        icon = R.drawable.day_116,
                        temp = 9
                    ),
                    HourlyDaily(
                        time = "7 AM",
                        icon = R.drawable.day_116,
                        temp = 8
                    ),
                    HourlyDaily(
                        time = "8 AM",
                        icon = R.drawable.day_116,
                        temp = 6
                    )
                ),
                daily = listOf(
                    HourlyDaily(
                        time = "MON",
                        icon = R.drawable.day_116,
                        temp = 6
                    ),
                    HourlyDaily(
                        time = "TUE",
                        icon = R.drawable.day_116,
                        temp = 7
                    ),
                    HourlyDaily(
                        time = "WED",
                        icon = R.drawable.day_116,
                        temp = 8
                    ),
                    HourlyDaily(
                        time = "THU",
                        icon = R.drawable.day_116,
                        temp = 9
                    ),
                    HourlyDaily(
                        time = "FRI",
                        icon = R.drawable.day_116,
                        temp = 10
                    ),
                    HourlyDaily(
                        time = "SAT",
                        icon = R.drawable.day_116,
                        temp = 9
                    ),
                    HourlyDaily(
                        time = "SUN",
                        icon = R.drawable.day_116,
                        temp = 8
                    )
                )
            )
        ),
        navigateToSearchCity = {},
        navigateToSettings = {}
    )
}





