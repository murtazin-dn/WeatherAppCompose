package com.example.weatherappcompose.ui.utils.ext

import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue

@OptIn(ExperimentalMaterial3Api::class)
fun BottomSheetScaffoldState.getCurrentFraction(mh: Float): Float{
    val targetValue = bottomSheetState.targetValue
    val currentValue = bottomSheetState.currentValue

    val offset = (bottomSheetState.requireOffset() - (mh * 0.2f)) / (mh * 0.4f)


    val fraction = when {
        currentValue == SheetValue.PartiallyExpanded && targetValue == SheetValue.PartiallyExpanded -> 0f
        currentValue == SheetValue.Expanded && targetValue == SheetValue.Expanded -> 1f
        currentValue == SheetValue.PartiallyExpanded && targetValue == SheetValue.Expanded -> 1f - offset
        else -> 1f - offset
    }

    return when{
        fraction < 0f -> 0f
        fraction > 1f -> 1f
        else -> fraction
    }

    }