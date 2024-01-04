package com.hegunhee.maplemfinder.core.designsystem.theme

import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke

val NormalDottedStroke = Stroke(width = 3f, pathEffect = PathEffect.dashPathEffect(intervals = floatArrayOf(20f, 10f), phase = 10f))
val NormalStroke = Stroke(width = 3f)
