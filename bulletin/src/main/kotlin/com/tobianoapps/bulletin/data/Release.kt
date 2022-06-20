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

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

import java.util.*

/**
 * A [Release] represents a unique entry in a [Bulletin]
 *
 * @param[time] release time in millis.
 * @param[label]
 * @param[changes] list of [Change] for a [Release]
 */
@Parcelize
data class Release(
    val time: Long,
    val label: String,
    val changes: Changes
) : Parcelable {

    @IgnoredOnParcel
    val id: UUID = UUID.randomUUID()
}
