package com.example.system.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.system.R
import com.example.system.ui.theme.SystemTheme

@Composable
fun Parameter(
    painter: Painter,
    text: String,
    textFontSize: TextUnit,
    pointsFontSize: TextUnit,
    points: Int
) {

    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
        Image(
            modifier = Modifier.size(45.dp),
            painter = painter,
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(10.dp))
        GlowingText(text = "$text:  ", fontSize = textFontSize)
        GlowingText(text = "$points", fontSize = pointsFontSize)
    }

}

@Preview
@Composable
private fun ParameterPreview() {
    SystemTheme {
        Parameter(
            painter = painterResource(R.drawable.ic_exclcmark),
            text = "VIT",
            textFontSize = MaterialTheme.typography.titleSmall.fontSize,
            pointsFontSize = MaterialTheme.typography.titleLarge.fontSize,
            points = 10
        )
    }
}