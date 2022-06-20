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

import com.tobianoapps.bulletin.data.config.color.BulletinCardColors.Companion.cardThemeDefaultDark
import com.tobianoapps.bulletin.data.config.color.BulletinCardColors.Companion.cardThemeDefaultLight
import com.tobianoapps.bulletin.data.config.color.ChangeTagTheme.Companion.tagThemeDefaultDark
import com.tobianoapps.bulletin.data.config.color.ChangeTagTheme.Companion.tagThemeDefaultLight
import com.tobianoapps.bulletin.data.config.color.ChangeTagTheme.Companion.tagThemeDeprecatedDark
import com.tobianoapps.bulletin.data.config.color.ChangeTagTheme.Companion.tagThemeDeprecatedLight
import com.tobianoapps.bulletin.data.config.color.ChangeTagTheme.Companion.tagThemeFixedDark
import com.tobianoapps.bulletin.data.config.color.ChangeTagTheme.Companion.tagThemeFixedLight
import com.tobianoapps.bulletin.data.config.color.ChangeTagTheme.Companion.tagThemeImprovedDark
import com.tobianoapps.bulletin.data.config.color.ChangeTagTheme.Companion.tagThemeImprovedLight
import com.tobianoapps.bulletin.data.config.color.ChangeTagTheme.Companion.tagThemeMaintenanceDark
import com.tobianoapps.bulletin.data.config.color.ChangeTagTheme.Companion.tagThemeMaintenanceLight
import com.tobianoapps.bulletin.data.config.color.ChangeTagTheme.Companion.tagThemeNewDark
import com.tobianoapps.bulletin.data.config.color.ChangeTagTheme.Companion.tagThemeNewLight
import com.tobianoapps.bulletin.data.config.color.ChangeTagTheme.Companion.tagThemeRemovedDark
import com.tobianoapps.bulletin.data.config.color.ChangeTagTheme.Companion.tagThemeRemovedLight
import com.tobianoapps.bulletin.data.config.color.ChangeTagTheme.Companion.tagThemeSecurityDark
import com.tobianoapps.bulletin.data.config.color.ChangeTagTheme.Companion.tagThemeSecurityLight
import com.tobianoapps.bulletin.data.ChangeType
import com.tobianoapps.bulletin.components.BulletinScreen


/**
 * This class represents colors for a [BulletinScreen] cards and [ChangeType] tags.
 *
 * @param[card] [BulletinCardColors] for a [BulletinScreen] cards
 * @param[tagDefault] [ChangeTagTheme] for the [ChangeType.DEFAULT] tags
 * @param[tagFixed] [ChangeTagTheme] for the [ChangeType.FIXED] tags
 * @param[tagImproved] [ChangeTagTheme] for the [ChangeType.IMPROVED] tags
 * @param[tagMaintenance] [ChangeTagTheme] for the [ChangeType.MAINTENANCE] tags
 * @param[tagNew] [ChangeTagTheme] for the [ChangeType.NEW] tags
 * @param[tagRemoved] [ChangeTagTheme] for the [ChangeType.REMOVED] tags
 * @param[tagSecurity] [ChangeTagTheme] for the [ChangeType.SECURITY] tags
 */
class BulletinColorPalette(
    val card: BulletinCardColors,
    val tagDefault: ChangeTagTheme? = null,
    val tagFixed: ChangeTagTheme? = null,
    val tagImproved: ChangeTagTheme? = null,
    val tagMaintenance: ChangeTagTheme? = null,
    val tagNew: ChangeTagTheme? = null,
    val tagRemoved: ChangeTagTheme? = null,
    val tagDeprecated: ChangeTagTheme? = null,
    val tagSecurity: ChangeTagTheme? = null
) {
    private constructor(builder: Builder) : this(
        builder.card,
        builder.tagDefault,
        builder.tagFixed,
        builder.tagImproved,
        builder.tagMaintenance,
        builder.tagNew,
        builder.tagRemoved,
        builder.tagDeprecated,
        builder.tagSecurity
    )

    companion object {
        inline fun bulletinColorPalette(block: Builder.() -> Unit) =
            Builder().apply(block).build()

        val defaultLightPalette = BulletinColorPalette(
            card = cardThemeDefaultLight,
            tagDefault = tagThemeDefaultLight,
            tagFixed = tagThemeFixedLight,
            tagImproved = tagThemeImprovedLight,
            tagMaintenance = tagThemeMaintenanceLight,
            tagNew = tagThemeNewLight,
            tagRemoved = tagThemeRemovedLight,
            tagDeprecated = tagThemeDeprecatedLight,
            tagSecurity = tagThemeSecurityLight
        )

        val defaultDarkPalette = BulletinColorPalette(
            card = cardThemeDefaultDark,
            tagDefault = tagThemeDefaultDark,
            tagFixed = tagThemeFixedDark,
            tagImproved = tagThemeImprovedDark,
            tagMaintenance = tagThemeMaintenanceDark,
            tagNew = tagThemeNewDark,
            tagRemoved = tagThemeRemovedDark,
            tagDeprecated = tagThemeDeprecatedDark,
            tagSecurity = tagThemeSecurityDark
        )
    }

    class Builder {
        var card: BulletinCardColors = defaultLightPalette.card
        var tagDefault: ChangeTagTheme? = null
        var tagFixed: ChangeTagTheme? = null
        var tagImproved: ChangeTagTheme? = null
        var tagMaintenance: ChangeTagTheme? = null
        var tagNew: ChangeTagTheme? = null
        var tagRemoved: ChangeTagTheme? = null
        var tagDeprecated: ChangeTagTheme? = null
        var tagSecurity: ChangeTagTheme? = null
        fun build() = BulletinColorPalette(this)
    }
}