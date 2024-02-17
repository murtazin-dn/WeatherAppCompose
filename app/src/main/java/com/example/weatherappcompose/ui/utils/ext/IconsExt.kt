package com.example.weatherappcompose.ui.utils.ext

import com.example.weatherappcompose.R

fun getIconFromCode(icon: String, isDay: Int): Int{
    val code = icon.substringAfterLast("/").dropLast(4).toInt()
    if (isDay == 1){
        return when(code){
            113 -> R.drawable.day_113
            116 -> R.drawable.day_116
            119 -> R.drawable.day_119
            122 -> R.drawable.day_122
            143 -> R.drawable.day_143
            176 -> R.drawable.day_176
            179 -> R.drawable.day_179
            182 -> R.drawable.day_182
            185 -> R.drawable.day_185
            200 -> R.drawable.day_200
            227 -> R.drawable.day_227
            230 -> R.drawable.day_230
            248 -> R.drawable.day_248
            260 -> R.drawable.day_260
            263 -> R.drawable.day_263
            266 -> R.drawable.day_266
            281 -> R.drawable.day_281
            284 -> R.drawable.day_284
            293 -> R.drawable.day_293
            296 -> R.drawable.day_296
            299 -> R.drawable.day_299
            302 -> R.drawable.day_302
            305 -> R.drawable.day_305
            308 -> R.drawable.day_308
            311 -> R.drawable.day_311
            314 -> R.drawable.day_314
            317 -> R.drawable.day_317
            320 -> R.drawable.day_320
            323 -> R.drawable.day_323
            326 -> R.drawable.day_326
            329 -> R.drawable.day_329
            332 -> R.drawable.day_332
            335 -> R.drawable.day_335
            338 -> R.drawable.day_338
            350 -> R.drawable.day_350
            353 -> R.drawable.day_353
            356 -> R.drawable.day_356
            359 -> R.drawable.day_359
            362 -> R.drawable.day_362
            365 -> R.drawable.day_365
            368 -> R.drawable.day_368
            371 -> R.drawable.day_371
            374 -> R.drawable.day_374
            377 -> R.drawable.day_377
            386 -> R.drawable.day_386
            389 -> R.drawable.day_389
            392 -> R.drawable.day_392
            395 -> R.drawable.day_395
            else -> R.drawable.day_113
        }
    } else{
        return when(code){
            113 -> R.drawable.night_113
            116 -> R.drawable.night_116
            119 -> R.drawable.night_119
            122 -> R.drawable.night_122
            143 -> R.drawable.night_143
            176 -> R.drawable.night_176
            179 -> R.drawable.night_179
            182 -> R.drawable.night_182
            185 -> R.drawable.night_185
            200 -> R.drawable.night_200
            227 -> R.drawable.night_227
            230 -> R.drawable.night_230
            248 -> R.drawable.night_248
            260 -> R.drawable.night_260
            263 -> R.drawable.night_263
            266 -> R.drawable.night_266
            281 -> R.drawable.night_281
            284 -> R.drawable.night_284
            293 -> R.drawable.night_293
            296 -> R.drawable.night_296
            299 -> R.drawable.night_299
            302 -> R.drawable.night_302
            305 -> R.drawable.night_305
            308 -> R.drawable.night_308
            311 -> R.drawable.night_311
            314 -> R.drawable.night_314
            317 -> R.drawable.night_317
            320 -> R.drawable.night_320
            323 -> R.drawable.night_323
            326 -> R.drawable.night_326
            329 -> R.drawable.night_329
            332 -> R.drawable.night_332
            335 -> R.drawable.night_335
            338 -> R.drawable.night_338
            350 -> R.drawable.night_350
            353 -> R.drawable.night_353
            356 -> R.drawable.night_356
            359 -> R.drawable.night_359
            362 -> R.drawable.night_362
            365 -> R.drawable.night_365
            368 -> R.drawable.night_368
            371 -> R.drawable.night_371
            374 -> R.drawable.night_374
            377 -> R.drawable.night_377
            386 -> R.drawable.night_386
            389 -> R.drawable.night_389
            392 -> R.drawable.night_392
            395 -> R.drawable.night_395
            else -> R.drawable.night_113
        }
    }
}