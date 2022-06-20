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
 * Colors for a [Bulletin] card.
 *
 * @param[backgroundColor] Color of the card's background
 * @param[foregroundColor] Color used for elements laid over the background (text, border)
 */
class BulletinCardColors(
    val backgroundColor: Color,
    val foregroundColor: Color
) {

    private constructor(builder: Builder) : this(
        builder.backgroundColor,
        builder.foregroundColor
    )


    class Builder {
        var backgroundColor: Color = cardThemeDefaultLight.backgroundColor
        var foregroundColor: Color = cardThemeDefaultLight.foregroundColor
        fun build() = BulletinCardColors(this)
    }


    companion object {

        inline fun bulletinCardTheme(block: Builder.() -> Unit) =
            Builder().apply(block).build()

        val cardThemeDefaultLight =
            BulletinCardColors(backgroundColor = Color.White, foregroundColor = Color.Black)

        val cardThemeDefaultDark =
            BulletinCardColors(
                backgroundColor = Color(0xFF1E1E1E),
                foregroundColor = Color(0xFF_FAFAFA)
            )
    }
}