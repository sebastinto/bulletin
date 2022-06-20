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

package com.tobianoapps.bulletin.data.config.color

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import com.tobianoapps.bulletin.data.config.color.BulletinColorPalette.Companion.defaultDarkPalette
import com.tobianoapps.bulletin.data.config.color.BulletinColorPalette.Companion.defaultLightPalette
import com.tobianoapps.bulletin.data.Bulletin


/**
 * This class represents colors used on a [Bulletin] cards and tags.
 *
 * @param[lightColors] Colors used when system theme is set to light mode
 * @param[darkColors] Colors used when system theme is set to dark mode
 */
class BulletinColors(
    private val lightColors: BulletinColorPalette = defaultLightPalette,
    private val darkColors: BulletinColorPalette = defaultDarkPalette
) {

    private constructor(builder: Builder) : this(
        builder.lightColors,
        builder.darkColors
    )

    companion object {
        inline fun bulletinColors(block: Builder.() -> Unit) =
            Builder().apply(block).build()
    }

    class Builder {
        var lightColors: BulletinColorPalette = defaultLightPalette
        var darkColors: BulletinColorPalette = defaultDarkPalette
        fun build() = BulletinColors(this)
    }

    val tagDefault: ChangeTagTheme
        @Composable
        get() =
            if (isSystemInDarkTheme()) darkColors.tagDefault ?: ChangeTagTheme.tagThemeDefaultDark
            else lightColors.tagDefault ?: ChangeTagTheme.tagThemeDefaultLight

    val tagFixed: ChangeTagTheme
        @Composable
        get() =
            if (isSystemInDarkTheme()) darkColors.tagFixed ?: ChangeTagTheme.tagThemeFixedDark
            else lightColors.tagFixed ?: ChangeTagTheme.tagThemeFixedLight

    val tagImproved: ChangeTagTheme
        @Composable
        get() =
            if (isSystemInDarkTheme()) darkColors.tagImproved ?: ChangeTagTheme.tagThemeImprovedDark
            else lightColors.tagImproved ?: ChangeTagTheme.tagThemeImprovedLight

    val tagMaintenance: ChangeTagTheme
        @Composable
        get() =
            if (isSystemInDarkTheme()) darkColors.tagMaintenance ?: ChangeTagTheme.tagThemeMaintenanceDark
            else lightColors.tagMaintenance ?: ChangeTagTheme.tagThemeMaintenanceLight

    val tagNew: ChangeTagTheme
        @Composable
        get() =
            if (isSystemInDarkTheme()) darkColors.tagNew ?: ChangeTagTheme.tagThemeNewDark
            else lightColors.tagNew ?: ChangeTagTheme.tagThemeNewLight

    val tagRemoved: ChangeTagTheme
        @Composable
        get() =
            if (isSystemInDarkTheme()) darkColors.tagRemoved ?: ChangeTagTheme.tagThemeRemovedDark
            else lightColors.tagRemoved ?: ChangeTagTheme.tagThemeRemovedLight

    val tagDeprecated: ChangeTagTheme
        @Composable
        get() =
            if (isSystemInDarkTheme()) darkColors.tagDeprecated ?: ChangeTagTheme.tagThemeDeprecatedDark
            else lightColors.tagDeprecated ?: ChangeTagTheme.tagThemeDeprecatedLight

    val tagSecurity: ChangeTagTheme
        @Composable
        get() =
            if (isSystemInDarkTheme()) darkColors.tagSecurity ?: ChangeTagTheme.tagThemeSecurityDark
            else lightColors.tagSecurity ?: ChangeTagTheme.tagThemeSecurityLight

    val card: BulletinCardColors
        @Composable
        get() = if (isSystemInDarkTheme()) darkColors.card else lightColors.card
}