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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tobianoapps.bulletin.data.Change
import com.tobianoapps.bulletin.data.ChangeType

/**
 * [BulletinTagStyle] represents styling properties for [Change] tags
 *
 * @param[shape] shape for [ChangeType] badges
 * @param[elevation] elevation for [ChangeType] badges
 * @param[paddingTop] for [ChangeType] badges
 * @param[paddingBottom] for [ChangeType] badges
 * @param[paddingHorizontal] for [ChangeType] badges
 * @param[paddingVertical] for [ChangeType] badges
 */
data class BulletinTagStyle(
    val shape: Shape = RoundedCornerShape(4.dp),
    val elevation: Dp = 0.dp,
    val paddingTop: Dp = 16.dp,
    val paddingBottom: Dp = 8.dp,
    val paddingHorizontal: Dp = 8.dp,
    val paddingVertical: Dp = 0.dp,
    val borderStroke: BorderStroke? = null
) {

    private constructor(builder: Builder) : this(
        builder.shape,
        builder.elevation,
        builder.paddingTop,
        builder.paddingBottom,
        builder.paddingHorizontal,
        builder.paddingVertical,
        builder.borderStroke
    )

    companion object {
        inline fun bulletinTagStyle(
            block: Builder.() -> Unit
        ) = Builder().apply(block).build()
    }

    class Builder {
        var shape: Shape = RoundedCornerShape(4.dp)
        var elevation: Dp = 0.dp
        var paddingTop: Dp = 16.dp
        var paddingBottom: Dp = 8.dp
        var paddingHorizontal: Dp = 8.dp
        var paddingVertical: Dp = 0.dp
        val borderStroke: BorderStroke? = null
        fun build() = BulletinTagStyle(this)
    }
}