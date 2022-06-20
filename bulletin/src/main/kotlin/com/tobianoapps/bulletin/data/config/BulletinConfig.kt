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

package com.tobianoapps.bulletin.data.config

import androidx.compose.material.Card
import com.tobianoapps.bulletin.components.BulletinScreen
import com.tobianoapps.bulletin.data.*
import com.tobianoapps.bulletin.data.config.color.BulletinColors
import com.tobianoapps.bulletin.data.config.style.BulletinStyle
import java.time.ZoneId
import java.util.*

/**
 * [BulletinConfig] represents configuration options for rendering a [BulletinScreen]
 *
 * @param[bulletinColors] Set of light and dark colors for [BulletinScreen] tags
 * @param[style] Styling options for the main [BulletinScreen], tags and cards
 * @param[groupByChangeType] If `true`, [Change]s with similar [ChangeType]s be grouped together
 * rather than displayed each with a tag above it
 * @param[showReleaseDate] Whether to show time / date on each [Release] [Card] header
 * @param[showHeaderDivider] Optional divider displayed below [Release] date and [Changes]
 * @param[zoneId] Use this to override default to display [Bulletin] date
 * @param[locale] Use this to override default [Locale]
 */
class BulletinConfig(
    val bulletinColors: BulletinColors = BulletinColors(),
    val style: BulletinStyle = BulletinStyle(),
    val groupByChangeType: Boolean = false,
    val showHeaderDivider: Boolean = true,
    val showLatestOnly: Boolean = false,
    val showReleaseDate: Boolean = true,
    val zoneId: ZoneId = ZoneId.systemDefault(),
    val locale: Locale = Locale.getDefault()
) {

    private constructor(builder: Builder) : this(
        builder.bulletinColors,
        builder.style,
        builder.groupByChangeType,
        builder.showHeaderDivider,
        builder.showLatestOnly,
        builder.showReleaseDate,
        builder.zoneId,
        builder.locale
    )

    companion object {
        inline fun bulletinConfig(
            block: Builder.() -> Unit
        ) = Builder().apply(block).build()
    }

    class Builder {
        var bulletinColors: BulletinColors = BulletinColors()
        var style: BulletinStyle = BulletinStyle()
        var groupByChangeType: Boolean = false
        var showHeaderDivider: Boolean = true
        var showLatestOnly: Boolean = false
        var showReleaseDate: Boolean = true
        var zoneId: ZoneId = ZoneId.systemDefault()
        var locale: Locale = Locale.getDefault()
        fun build() = BulletinConfig(this)
    }
}