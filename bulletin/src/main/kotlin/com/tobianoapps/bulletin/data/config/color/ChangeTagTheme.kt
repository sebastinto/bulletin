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

import androidx.compose.ui.graphics.Color
import com.tobianoapps.bulletin.data.Bulletin

/**
 * Set of colors for [Bulletin] tags.
 *
 * @param[backgroundColor] [Color] for a tag background
 * @param[foregroundColor] [Color] used for elements laid over a tag background (text, border, etc.)
 */
class ChangeTagTheme(
    val backgroundColor: Color,
    val foregroundColor: Color
) {

    private constructor(builder: Builder) : this(
        builder.backgroundColor,
        builder.foregroundColor
    )


    class Builder {
        var backgroundColor: Color = tagThemeDefaultLight.backgroundColor
        var foregroundColor: Color = tagThemeDefaultLight.foregroundColor
        fun build() = ChangeTagTheme(this)
    }


    companion object {

        inline fun bulletinTagTheme(block: Builder.() -> Unit) =
            Builder().apply(block).build()

        /*** Default Light Colors ***/
        val tagThemeDefaultLight = ChangeTagTheme(
            backgroundColor = Color(0xFFE7E7E7), foregroundColor = Color(0xFF4F4F4F)
        )
        val tagThemeFixedLight = ChangeTagTheme(
            backgroundColor = Color(0xFFFFCFC3), foregroundColor = Color(0xFF820D00)
        )
        val tagThemeImprovedLight = ChangeTagTheme(
            backgroundColor = Color(0xFFC6E0FF), foregroundColor = Color(0xFF003D85)
        )
        val tagThemeMaintenanceLight = ChangeTagTheme(
            backgroundColor = Color(0xFFEBD8FE), foregroundColor = Color(0xFF20009E)
        )
        val tagThemeNewLight = ChangeTagTheme(
            backgroundColor = Color(0xFFA7FED4), foregroundColor = Color(0xFF00532C)
        )
        val tagThemeRemovedLight = ChangeTagTheme(
            backgroundColor = Color(0xFF2C2C2C), foregroundColor = Color(0xFFFFFFFF)
        )
        val tagThemeDeprecatedLight = ChangeTagTheme(
            backgroundColor = Color(0xFFFFC98C), foregroundColor = Color(0xFF5B3100)
        )
        val tagThemeSecurityLight = ChangeTagTheme(
            backgroundColor = Color(0xFFFFE9A0), foregroundColor = Color(0xFF4F3D00)
        )

        /*** Default dark colors ***/
        val tagThemeDefaultDark = ChangeTagTheme(
            backgroundColor = Color(0xFF676767), foregroundColor = Color(0xFF000000)
        )
        val tagThemeFixedDark = ChangeTagTheme(
            backgroundColor = Color(0xFF4B1A0E), foregroundColor = Color(0xFFCD574A)
        )
        val tagThemeImprovedDark = ChangeTagTheme(
            backgroundColor = Color(0xFF003E77), foregroundColor = Color(0xFF95CCFF)
        )
        val tagThemeMaintenanceDark = ChangeTagTheme(
            backgroundColor = Color(0xFF421570), foregroundColor = Color(0xFFD7C3F0)
        )
        val tagThemeNewDark = ChangeTagTheme(
            backgroundColor = Color(0xFF00481D), foregroundColor = Color(0xFF16D47B)
        )
        val tagThemeRemovedDark = ChangeTagTheme(
            backgroundColor = Color(0xFF303030), foregroundColor = Color(0xFFFAFAFA)
        )
        val tagThemeDeprecatedDark = ChangeTagTheme(
            backgroundColor = Color(0xFF563B1C), foregroundColor = Color(0xFFCC8128)
        )
        val tagThemeSecurityDark = ChangeTagTheme(
            backgroundColor = Color(0xFF695100), foregroundColor = Color(0xFFC99D00)
        )
    }
}