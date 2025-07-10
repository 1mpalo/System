package com.example.system.presentation.onboarding

import android.content.res.Configuration
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.system.R
import com.example.system.presentation.common.Button
import com.example.system.presentation.common.GlowingText
import com.example.system.presentation.common.Notification
import com.example.system.ui.theme.SystemTheme

@Composable
fun OnBoardingScreen(
    onYesClicked: () -> Unit,
    onNoClicked: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize(),
            painter = painterResource(R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Notification(
            text = "NOTIFICATION",
            verticalPadding = 50.dp,
        )

        Spacer(modifier = Modifier.padding(vertical = 40.dp))
        Column(
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxHeight()
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(top = 140.dp),
                text = buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            Color.White,
                            shadow = Shadow(
                                color = Color.White,
                                offset = Offset(0f, 0f),
                                blurRadius = 20f
                            ),
                            fontSize = MaterialTheme.typography.titleLarge.fontSize
                        )
                    ) { append("Your heart will stop in") }


                    withStyle(
                        SpanStyle(
                            color = Color.Red,
                            shadow = Shadow(
                                color = Color.Red,
                                offset = Offset(0f, 0f),
                                blurRadius = 20f
                            ),
                            fontSize = MaterialTheme.typography.titleLarge.fontSize
                        )
                    ) { append(" 0.02 seconds") }
                }
            )

            Spacer(modifier = Modifier.padding(vertical = 8.dp))

            GlowingText(
                text = "if you choose not to accept.",
                color = Color.White,
                textAlign = TextAlign.Start,
                fontSize = MaterialTheme.typography.titleLarge.fontSize
            )

            Spacer(modifier = Modifier.padding(vertical = 8.dp))

            GlowingText(
                text = "Will you accept?",
                textAlign = TextAlign.Start,
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                color = Color.White
            )

            Spacer(modifier = Modifier.padding(vertical = 20.dp))

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 210.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    text = "Yes",
                    height = 40.dp,
                    width = 100.dp,
                    onClick = onYesClicked
                )

                Spacer(modifier = Modifier.padding(50.dp))

                Button(
                    text = "No",
                    height = 40.dp,
                    width = 100.dp,
                    onClick = onNoClicked
                )
            }
        }
    }
}


@Preview(
    device = Devices.AUTOMOTIVE_1024p
)
@Composable
private fun OnBoardingScreenPreview() {
    SystemTheme {
        OnBoardingScreen(onNoClicked = {}, onYesClicked = {})
    }
}

