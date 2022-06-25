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

package com.tobianoapps.bulletin.util

import com.tobianoapps.bulletin.data.Bulletin
import com.tobianoapps.bulletin.data.ChangeType
import com.tobianoapps.bulletin.data.Changes
import com.tobianoapps.bulletin.data.Release
import com.tobianoapps.bulletin.data.config.BulletinConfig

internal fun Bulletin.sortedByMostRecent(
    showLatestOnly: Boolean? = false
): List<Release> {
    return when (showLatestOnly) {
        true -> listOf(this.releases.sortedByDescending { it.time }.first())
        else -> this.releases.sortedByDescending { it.time }
    }
}

internal fun Changes.groupedByChangeType(): Map<ChangeType, Changes> = this.groupBy { it.changeType }


internal fun BulletinConfig.hasTagBorder(): Boolean = this.style.tagStyle.borderStroke != null
fun BulletinConfig.hasCardBorder(): Boolean = this.style.cardStyle.borderStroke != null
