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

package com.tobianoapps.bulletin.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.tobianoapps.bulletin.data.config.BulletinConfig
import com.tobianoapps.bulletin.data.Release
import com.tobianoapps.bulletin.util.capitalized
import com.tobianoapps.bulletin.util.localDate

/**
 * [Composable] that displays a [Release] title and optional date.
 *
 * @param[release] used to determine title and date
 * @param[config] used to configure display options and style
 */
@Composable
internal fun ReleaseHeader(
    release: Release,
    config: BulletinConfig = BulletinConfig()
) {

    val showDate by remember(config) {
        mutableStateOf(config.showReleaseDate)
    }
    ReleaseTitle(
        release = release,
        config = config
    )

    if (showDate) {
        ReleaseDate(
            release = release,
            config = config
        )
    }
}

/**
 * [Composable] that displays a [Release] title date.
 *
 * @param[release] used to determine title and date
 * @param[config] used to configure display options and style
 */
@Composable
fun ReleaseTitle(
    release: Release,
    config: BulletinConfig = BulletinConfig()
) {
    Text(
        text = release.label.capitalized,
        style = config.style.cardStyle.cardTitleStyle?.invoke() ?: TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
            letterSpacing = 0.sp,
                    color = config.bulletinColors.card.foregroundColor
        )
    )
}

/**
 * [Composable] that displays a [Release] date.
 *
 * @param[release] used to determine title and date
 * @param[config] used to configure display options and style
 */
@Composable
fun ReleaseDate(
    release: Release,
    config: BulletinConfig = BulletinConfig()
) {

    Text(
        text = release.time.localDate(
            zoneId = config.zoneId,
            locale = config.locale
        ),
        style = config.style.cardStyle.cardDateStyle?.invoke() ?: TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = config.bulletinColors.card.foregroundColor.copy(alpha = 0.4f),
            letterSpacing = 0.sp
        )
    )
}