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

package com.tobianoapps.bulletin.data

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import com.tobianoapps.bulletin.R
import com.tobianoapps.bulletin.data.config.BulletinConfig
import com.tobianoapps.bulletin.data.config.color.ChangeTagTheme

/**
 * This class represents a set of possible values for a [Change] tag.
 *
 * @param[label] String res for the tag label
 */
enum class ChangeType(@StringRes val label: Int) {

    DEFAULT(label = R.string.change_type_default),
    IMPROVED(label = R.string.change_type_improved),
    FIXED(label = R.string.change_type_fixed),
    MAINTENANCE(label = R.string.change_type_maintenance),
    NEW(label = R.string.change_type_new),
    REMOVED(label = R.string.change_type_removed),
    DEPRECATED(label = R.string.change_type_deprecated),
    SECURITY(label = R.string.change_type_security);

    companion object {
        @Composable
        fun BulletinConfig.tagTheme(changeType: ChangeType): ChangeTagTheme =
            when (changeType) {
                DEFAULT -> this.bulletinColors.tagDefault
                IMPROVED -> this.bulletinColors.tagImproved
                FIXED -> this.bulletinColors.tagFixed
                MAINTENANCE -> this.bulletinColors.tagMaintenance
                NEW -> this.bulletinColors.tagNew
                REMOVED -> this.bulletinColors.tagRemoved
                DEPRECATED -> this.bulletinColors.tagDeprecated
                SECURITY -> this.bulletinColors.tagSecurity
            }

        fun fromLabel(label: String?) = values().find { it.name == label } ?: DEFAULT
    }
}