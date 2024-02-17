package com.example.weatherappcompose.ui.screens.home.views

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.example.weatherappcompose.R
import com.example.weatherappcompose.data.network.weather.model.response.testData
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
    state: HomeViewState.WeatherLoaded
){
    BoxWithConstraints (
        modifier = Modifier.fillMaxSize()
    ) {
        val boxWithConstraintsScope = this
        val scaffoldState = rememberBottomSheetScaffoldState()
        val tabItems = listOf("Hourly Forecast", "Weekly Forecast")
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
            HomeTop(state, scaffoldState, boxWithConstraintsScope, )
        }
    }
}

@OptIn(ExperimentalMotionApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun HomeTop(
    state: HomeViewState.WeatherLoaded,
    scaffoldState: BottomSheetScaffoldState,
    boxWithConstraintsScope: BoxWithConstraintsScope
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
                        modifier = Modifier.layoutId("city_text"),
                        style = Typography.titleLarge.copy(color = PrimaryDark),
                        text = state.weather.location.region,
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
                        text = "${state.weather.current.temp_c.toInt()}°"
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
                        text = state.weather.current.condition.text
                    )
                    Text(
                        modifier = Modifier.layoutId("hl_temp_text"),
                        style = Typography.titleSmall.copy(color = PrimaryDark),
                        text = "H:${state.weather.forecast.forecastday.first().day.maxtemp_c.toInt()}°  " +
                                "L:${state.weather.forecast.forecastday.first().day.mintemp_c.toInt()}°"
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
                airQuality = state.weather.current.air_quality)
        }
        item{
            Row (
                modifier = Modifier.fillMaxWidth()
            ) {
                UVCard(
                    modifier = Modifier.weight(1f),
                    uv = state.weather.current.uv
                )
                SunRiseCard(
                    modifier = Modifier.weight(1f),
                    astro = state.weather.forecast.forecastday.first().astro
                )
            }
        }
        item{
            Row (
                modifier = Modifier.fillMaxWidth()
            ) {
                WindCard(
                    modifier = Modifier.weight(1f),
                    windKph = state.weather.current.wind_mph,
                    windDegree = state.weather.current.wind_degree,
                )
                RainFallCard(
                    modifier = Modifier.weight(1f),
                    precip = state.weather.current.precip_mm,
                    totalPrecip = state.weather.forecast.forecastday.first().day.totalprecip_mm
                )
            }
        }
        item{
            Row (
                modifier = Modifier.fillMaxWidth()
            ) {
                FeelsLikeCard(
                    modifier = Modifier.weight(1f),
                    feelsLike = state.weather.current.feelslike_c
                )
                HumidityCard(
                    modifier = Modifier.weight(1f),
                    humidity = state.weather.current.humidity,
                    temp = state.weather.current.temp_c
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
        state = HomeViewState.WeatherLoaded(testData)
    )
}





