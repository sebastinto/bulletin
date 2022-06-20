/*
 * Copyright (C) 2022 tobianoapps
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tobianoapps.sample.data

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tobianoapps.bulletin.data.config.color.BulletinColors.Companion.bulletinColors
import com.tobianoapps.bulletin.data.config.style.BulletinCardStyle.Companion.bulletinCardStyle
import com.tobianoapps.bulletin.data.config.color.BulletinCardColors.Companion.bulletinCardTheme
import com.tobianoapps.bulletin.data.config.BulletinConfig.Companion.bulletinConfig
import com.tobianoapps.bulletin.data.config.style.BulletinStyle.Companion.bulletinStyle
import com.tobianoapps.bulletin.data.config.color.BulletinColorPalette.Companion.bulletinColorPalette
import com.tobianoapps.bulletin.data.config.style.BulletinTagStyle.Companion.bulletinTagStyle
import com.tobianoapps.bulletin.data.config.color.ChangeTagTheme.Companion.bulletinTagTheme
import java.time.ZoneId
import java.util.*

val customShape = CutCornerShape(8.dp)
fun cardDateStyle(): TextStyle = TextStyle(
    color = Color.DarkGray,
    fontWeight = FontWeight.Thin,
    fontStyle = FontStyle.Italic
)

fun cardTitleStyle(): TextStyle = TextStyle(
    color = Color.Black,
    fontSize = 23.sp,
    fontWeight = FontWeight.Bold
)

val customCardStyle = bulletinCardStyle {
    shape = customShape
    elevation = 10.dp
    paddingHorizontal = 34.dp
    verticalSpacing = 32.dp
    cardDateStyle = {
        if (isSystemInDarkTheme())
            cardDateStyle().copy(color = Color.LightGray)
        else
            cardDateStyle()
    }
    cardTitleStyle = {
        if (isSystemInDarkTheme())
            cardTitleStyle().copy(color = Color.White)
        else
            cardTitleStyle()
    }
    borderStroke = BorderStroke(width = 3.dp, color = Color.DarkGray)
}

val customTagStyle = bulletinTagStyle {
    shape = CutCornerShape(4.dp)
    elevation = 2.dp
    paddingTop = 10.dp
    paddingBottom = 10.dp
    paddingHorizontal = 6.dp
    paddingVertical = 6.dp
}

val customBulletinStyle = bulletinStyle {
    cardStyle = customCardStyle
    tagStyle = customTagStyle
}

val customBulletinColors = bulletinColors {
    lightColors = bulletinColorPalette {
        card = bulletinCardTheme {
            backgroundColor = Color(0xFFE4DEAD)
            foregroundColor = Color.DarkGray
        }
        tagDefault = bulletinTagTheme {
            backgroundColor = Color.LightGray
            foregroundColor = Color.White
        }
        tagFixed = bulletinTagTheme {
            backgroundColor = Color(0xFFDD8888)
            foregroundColor = Color.White
        }
        tagImproved = bulletinTagTheme {
            backgroundColor = Color(0xFFEBE39D)
            foregroundColor = Color.White
        }
        tagMaintenance = bulletinTagTheme {
            backgroundColor = Color(0xFF3C9EFF)
            foregroundColor = Color.White
        }
        tagNew = bulletinTagTheme {
            backgroundColor = Color(0xFF5BECFF)
            foregroundColor = Color.White
        }
        tagRemoved = bulletinTagTheme {
            backgroundColor = Color(0xFF000000)
            foregroundColor = Color.White
        }
        tagSecurity = bulletinTagTheme {
            backgroundColor = Color(0xFFD000FF)
            foregroundColor = Color.White
        }
        tagDeprecated = bulletinTagTheme {
            backgroundColor = Color.DarkGray
            foregroundColor = Color.White
        }
    }
    darkColors = bulletinColorPalette {
        card = bulletinCardTheme {
            backgroundColor = Color(0xFF3A3931)
            foregroundColor = Color(0xFFF3F3F3)
        }
        tagDefault = bulletinTagTheme {
            backgroundColor = Color.DarkGray
            foregroundColor = Color.White
        }
        tagFixed = bulletinTagTheme {
            backgroundColor = Color(0xFFDD8888)
            foregroundColor = Color.White
        }
        tagImproved = bulletinTagTheme {
            backgroundColor = Color(0xFFAAA155)
            foregroundColor = Color.White
        }
        tagMaintenance = bulletinTagTheme {
            backgroundColor = Color(0xFF467CB1)
            foregroundColor = Color.White
        }
        tagNew = bulletinTagTheme {
            backgroundColor = Color(0xFF5D959C)
            foregroundColor = Color.White
        }
        tagRemoved = bulletinTagTheme {
            backgroundColor = Color(0xFF000000)
            foregroundColor = Color.White
        }
        tagSecurity = bulletinTagTheme {
            backgroundColor = Color(0xFF722D81)
            foregroundColor = Color.White
        }
        tagDeprecated = bulletinTagTheme {
            backgroundColor = Color.DarkGray
            foregroundColor = Color.White
        }
    }
}

val customBulletinConfig = bulletinConfig {
    bulletinColors = customBulletinColors
    style = customBulletinStyle
    groupByChangeType = true
    showHeaderDivider = false
    showReleaseDate = true
    zoneId = ZoneId.of("Europe/Paris")
    locale = Locale.FRANCE
}