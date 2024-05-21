package com.aviva.loveislove.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun Piece(color: Color, modifier: Modifier = Modifier, delayMillis: Int, index: Int ) {
    val infiniteTransition = rememberInfiniteTransition()
    val height by infiniteTransition.animateFloat(
        initialValue = 10f,
        targetValue = when(index) {
            0,1,8 -> 30f
            2,3,6,7 -> 60f
            4,5 -> 90f
            else -> 10f
        },
        animationSpec = infiniteRepeatable(
            tween(
                durationMillis = 3200,
                delayMillis = delayMillis,
                easing = FastOutSlowInEasing
            ),
            RepeatMode.Reverse
        )
    )
    val animatedColor by animateColorAsState(targetValue = color)
    Box(
        modifier = modifier
            .size(width = 10.dp, height = height.dp)
            .background(color = animatedColor, shape = CircleShape)
    )
}


@Composable
fun Heart() {
    val colors = listOf(Color(0xFFEC2D73), Color(0xFFEB5324), Color(0xFFFDC800), Color(0xFF47B264), Color(0xFF1470BD), Color(0xFF76469A), Color(0xFFEC2D73), Color(0xFFEB5324), Color(0xFFFDC800))
    Column {
        Row(modifier = Modifier.size(138.dp)) {
            colors.forEachIndexed { index, color ->
                Piece(color = color, delayMillis = index * 150, index = index)
            }
        }
        Row(modifier = Modifier.size(138.dp)) {
            colors.reversed().forEachIndexed { index, color ->
                Piece(color = color, delayMillis = index * 150, index = index)
            }
        }
    }
}

@Composable
fun HeartAnimation() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Heart()
            Text(text = "love is love", color = Color.Magenta, fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Light, modifier = Modifier.padding(top = 100.dp))
        }
    }
}
