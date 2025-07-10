package com.example.system.presentation.common

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.system.R
import com.example.system.ui.theme.SystemTheme
import java.util.Locale

@Composable
fun Notification(
    text: String,
    verticalPadding: Dp = 0.dp,
    horizontalPadding: Dp = 0.dp
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = verticalPadding, horizontal = horizontalPadding),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(70.dp)
                .border(0.4.dp, Color.White),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .shadow(
                        elevation = 3.dp,
                        shape = CircleShape,
                        ambientColor = Color.White,
                        spotColor = Color.White
                    ),
                painter = painterResource(R.drawable.ic_exclcmark),
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.padding(horizontal = 8.dp))
        Box(
            modifier = Modifier
                .height(70.dp)
                .width(400.dp)
                .border(0.4.dp, Color.White),
            contentAlignment = Alignment.Center
        ) {
            GlowingText(
                text = text.uppercase(),
                color = Color.White,
                fontSize = MaterialTheme.typography.displayMedium.fontSize,
                fontWeight = MaterialTheme.typography.headlineSmall.fontWeight
            )

        }
    }
}

@Composable
fun NotificationNoIcon(
    text: String,
    verticalPadding: Dp = 0.dp,
    horizontalPadding: Dp = 0.dp
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = verticalPadding, horizontal = horizontalPadding),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .height(70.dp)
                .width(400.dp)
                .border(0.4.dp, Color.White),
            contentAlignment = Alignment.Center
        ) {
            GlowingText(
                text = text.uppercase(),
                color = Color.White,
                fontSize = MaterialTheme.typography.displayMedium.fontSize,
                fontWeight = MaterialTheme.typography.headlineSmall.fontWeight
            )

        }
    }
}
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun NotificationPreview() {
    SystemTheme {

    }
}