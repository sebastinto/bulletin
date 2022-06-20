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

package com.tobianoapps.bulletin.data.config.style

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tobianoapps.bulletin.data.Release

/**
 * [BulletinCardStyle] represents styling properties for [Release] cards
 *
 * @param[shape] shape for a [Release] [Card]
 * @param[elevation] elevation for a [Release] [Card]
 * @param[paddingHorizontal] horizontal padding for a [Release] [Card]
 * @param[verticalSpacing] vertical spacing for a [Release] [Card]
 * @param[cardDateStyle] [TextStyle] for the [Release] [Card] date
 * @param[borderStroke] [BorderStroke]  for a [Release] [Card]
 */
class BulletinCardStyle(
    val shape: Shape = RoundedCornerShape(4.dp),
    val elevation: Dp = 4.dp,
    val paddingHorizontal: Dp = 16.dp,
    val verticalSpacing: Dp = 16.dp,
    val headerDividerPaddingHorizontal: Dp = 16.dp,
    val cardDateStyle: @Composable (() -> TextStyle)? = null,
    val cardTitleStyle: @Composable (() -> TextStyle)? = null,
    val borderStroke: BorderStroke? = null
) {

    private constructor(builder: Builder) : this(
        builder.shape,
        builder.elevation,
        builder.paddingHorizontal,
        builder.verticalSpacing,
        builder.headerDividerPaddingHorizontal,
        builder.cardDateStyle,
        builder.cardTitleStyle,
        builder.borderStroke
    )

    companion object {
        inline fun bulletinCardStyle(
            block: Builder.() -> Unit
        ) = Builder().apply(block).build()
    }


    class Builder {
        var shape: Shape = RoundedCornerShape(4.dp)
        var elevation: Dp = 1.dp
        var paddingHorizontal: Dp = 16.dp
        var verticalSpacing: Dp = 16.dp
        var headerDividerPaddingHorizontal: Dp = 16.dp
        var cardDateStyle: @Composable (() -> TextStyle)? = null
        var cardTitleStyle: @Composable (() -> TextStyle)? = null
        var borderStroke: BorderStroke? = null
        fun build() = BulletinCardStyle(this)
    }
}