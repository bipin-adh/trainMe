package com.bpn.trainme.presentation.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

// Color schemes
private val SpotifyPulseLight = lightColorScheme(
    primary = SpotifyGreen,
    secondary = NeonPink,
    background = SpotifyBlack,
    surface = SpotifyBlack,
    onPrimary = White,
    onSecondary = White,
    onBackground = White,
    onSurface = White
)

private val SpotifyPulseDark = darkColorScheme(
    primary = SpotifyGreen,
    secondary = NeonPink,
    background = SpotifyBlack,
    surface = SpotifyBlack,
    onPrimary = White,
    onSecondary = White,
    onBackground = White,
    onSurface = White
)

private val NightSprintLight = lightColorScheme(
    primary = DeepIndigo,
    secondary = NeonOrange,
    background = MidnightBlack,
    surface = MidnightBlack,
    onPrimary = White,
    onSecondary = White,
    onBackground = White,
    onSurface = White
)

private val NightSprintDark = darkColorScheme(
    primary = DeepIndigo,
    secondary = NeonOrange,
    background = MidnightBlack,
    surface = MidnightBlack,
    onPrimary = White,
    onSecondary = White,
    onBackground = White,
    onSurface = White
)

private val PowerForgeLight = lightColorScheme(
    primary = FieryRed,
    secondary = SteelGray,
    background = DarkGray,
    surface = DarkGray,
    onPrimary = White,
    onSecondary = White,
    onBackground = White,
    onSurface = White
)

private val PowerForgeDark = darkColorScheme(
    primary = FieryRed,
    secondary = SteelGray,
    background = DarkGray,
    surface = DarkGray,
    onPrimary = White,
    onSecondary = White,
    onBackground = White,
    onSurface = White
)

// Shapes (shared across themes)
private val Shapes = androidx.compose.material3.Shapes(
    small = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
    medium = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
    large = androidx.compose.foundation.shape.RoundedCornerShape(16.dp)
)

enum class FitnessTheme {
    SpotifyPulse, NightSprint, PowerForge, Dynamic
}


@Composable
fun TrainMeTheme(
    theme: FitnessTheme = FitnessTheme.SpotifyPulse,
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
//    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current

    val colorScheme = when(theme){
        FitnessTheme.SpotifyPulse -> if (darkTheme) SpotifyPulseDark else SpotifyPulseLight
        FitnessTheme.NightSprint -> if (darkTheme) NightSprintDark else NightSprintLight
        FitnessTheme.PowerForge -> if (darkTheme) PowerForgeDark else PowerForgeLight
        FitnessTheme.Dynamic -> when{
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.S ->{
                if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
            }
            else -> if (darkTheme) SpotifyPulseDark else SpotifyPulseLight // fallback to SpotifyPulse
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
        shapes = Shapes
    )
}