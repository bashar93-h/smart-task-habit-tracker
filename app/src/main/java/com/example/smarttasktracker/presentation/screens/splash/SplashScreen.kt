package com.example.smarttasktracker.presentation.screens.splash

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.smarttasktracker.R
import com.example.smarttasktracker.presentation.theme.*
import kotlinx.coroutines.delay


private data class Quadruple<A, B, C, D>(val a: A, val b: B, val c: C, val d: D)

private val bubbles = listOf(
    Quadruple(0.10f, 0.08f, 36f, Color(0xFFF7C8E0)),
    Quadruple(0.85f, 0.12f, 24f, Color(0xFFB8D8F8)),
    Quadruple(0.78f, 0.42f, 18f, Color(0xFFC8F0DC)),
    Quadruple(0.06f, 0.55f, 28f, Color(0xFFFADCA8)),
    Quadruple(0.88f, 0.72f, 32f, Color(0xFFE0C8F8)),
    Quadruple(0.15f, 0.82f, 20f, Color(0xFFF7C8E0)),
    Quadruple(0.50f, 0.90f, 16f, Color(0xFFB8D8F8)),
)


private val enter = fadeIn(tween(500)) + slideInVertically(tween(500)) { 30 }


@Composable
fun SplashScreen(navController: NavController? = null) {

    var showMascot by remember { mutableStateOf(false) }
    var showTitle by remember { mutableStateOf(false) }
    var showTagline by remember { mutableStateOf(false) }
    var showBadges by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(200); showMascot = true
        delay(300); showTitle = true
        delay(200); showTagline = true
        delay(200); showBadges = true
        delay(1800)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .drawBehind() {
                // Draw all bubbles once — static, no animation
                bubbles.forEach { (xFrac, yFrac, radiusDp, color) ->
                    drawCircle(
                        color = color.copy(alpha = 0.55f),
                        radius = radiusDp.dp.toPx(),
                        center = Offset(
                            x = size.width * xFrac,
                            y = size.height * yFrac
                        )
                    )
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.size(140.dp), contentAlignment = Alignment.Center) {
                androidx.compose.animation.AnimatedVisibility(visible = showMascot, enter = enter) {
                    Image(
                        painter = painterResource(R.drawable.app_logo),
                        contentDescription = "Trackify mascot",
                        modifier = Modifier
                            .size(140.dp)
                            .clip(RoundedCornerShape(20.dp))
                    )
                }
            }

            Spacer(Modifier.height(24.dp))
            Box(modifier = Modifier.height(56.dp), contentAlignment = Alignment.Center) {
                androidx.compose.animation.AnimatedVisibility(visible = showTitle, enter = enter) {
                    Text(
                        text = "Trackify",
                        fontSize = 44.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = PrimaryPink,
                        letterSpacing = (-1).sp
                    )
                }
            }
            Box(modifier = Modifier.height(28.dp), contentAlignment = Alignment.Center) {
                androidx.compose.animation.AnimatedVisibility(
                    visible = showTagline,
                    enter = enter
                ) {
                    Text(
                        text = "your cozy habit companion",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        color = TextMuted
                    )
                }
            }
            Spacer(Modifier.height(32.dp))
            Box(
                modifier = Modifier
                    .width(250.dp).height(40.dp),
                contentAlignment = Alignment.Center
            ) {
                androidx.compose.animation.AnimatedVisibility(visible = showBadges, enter = enter) {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Badge("Habits", BadgePinkBg, BadgePinkTx)
                        Badge("Tasks", BadgeBlueBg, BadgeBlueTx)
                        Badge("Reminders", BadgeGreenBg, BadgeGreenTx)
                    }
                }
            }
        }
    }
}

@Composable
fun Badge(label: String, bg: Color, textColor: Color) {
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(999.dp))
            .background(bg)
            .padding(horizontal = 14.dp, vertical = 7.dp)
    ) {
        Text(text = label, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = textColor)
    }
}


@Preview
@Composable
fun SplashScreenPreview() = SplashScreen()
