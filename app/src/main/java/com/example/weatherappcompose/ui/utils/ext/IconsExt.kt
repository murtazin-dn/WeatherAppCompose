package com.example.weatherappcompose.ui.utils.ext

import com.example.weatherappcompose.R

fun getIconFromCode(code: Int, isDay: Int): Int{
    if (isDay == 1){
        return when(code){
            0 -> R.drawable.day_113
            1 -> R.drawable.day_116
            2 -> R.drawable.day_119
            3 -> R.drawable.day_122
            45 -> R.drawable.day_248
            48 -> R.drawable.day_260
            51 -> R.drawable.day_266
            53 -> R.drawable.day_266
            55 -> R.drawable.day_266
            56 -> R.drawable.day_281
            57 -> R.drawable.day_284
            61 -> R.drawable.day_296
            63 -> R.drawable.day_302
            65 -> R.drawable.day_308
            66 -> R.drawable.day_311
            67 -> R.drawable.day_314
            71 -> R.drawable.day_338
            73 -> R.drawable.day_338
            75 -> R.drawable.day_338
            77 -> R.drawable.day_332
            80 -> R.drawable.day_296
            81 -> R.drawable.day_302
            82 -> R.drawable.day_308
            85 -> R.drawable.day_332
            86 -> R.drawable.day_338
            95 -> R.drawable.day_389
            96 -> R.drawable.day_395
            99 -> R.drawable.day_395
            else -> R.drawable.day_113
        }
    } else{
        return when(code){
            0 -> R.drawable.night_113
            1 -> R.drawable.night_116
            2 -> R.drawable.night_119
            3 -> R.drawable.night_122
            45 -> R.drawable.night_248
            48 -> R.drawable.night_260
            51 -> R.drawable.night_266
            53 -> R.drawable.night_266
            55 -> R.drawable.night_266
            56 -> R.drawable.night_281
            57 -> R.drawable.night_284
            61 -> R.drawable.night_296
            63 -> R.drawable.night_302
            65 -> R.drawable.night_308
            66 -> R.drawable.night_311
            67 -> R.drawable.night_314
            71 -> R.drawable.night_338
            73 -> R.drawable.night_338
            75 -> R.drawable.night_338
            77 -> R.drawable.night_332
            80 -> R.drawable.night_296
            81 -> R.drawable.night_302
            82 -> R.drawable.night_308
            85 -> R.drawable.night_332
            86 -> R.drawable.night_338
            95 -> R.drawable.night_389
            96 -> R.drawable.night_395
            99 -> R.drawable.night_395
            else -> R.drawable.night_113
        }
    }
}