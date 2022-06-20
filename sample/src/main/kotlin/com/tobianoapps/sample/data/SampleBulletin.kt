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

package com.tobianoapps.sample.data

import com.tobianoapps.bulletin.data.Bulletin.Companion.bulletin
import com.tobianoapps.bulletin.data.Change
import com.tobianoapps.bulletin.data.Change.Companion.change
import com.tobianoapps.bulletin.data.ChangeType
import com.tobianoapps.bulletin.data.ChangeType.*
import com.tobianoapps.bulletin.data.Release


val sampleBulletin = bulletin {
    releases = listOf(
        Release(
            time = 1652229320349,
            label = "1.0.0-RC2",
            changes = listOf(
                change {
                    changeType = NEW
                    summary = "Bulletin is easy!"
                },
                change {
                    changeType = DEFAULT
                    summary = "Kotlin DSL FTW!"
                }
            )
        ),
        Release(
            time = 0,
            label = "0.0.0",
            changes = getDemoChanges()
        )
    )
}

/**
 * Iterate over all [ChangeType] to demonstrate them in the sample app.
 */
private fun getDemoChanges(): List<Change> {
    val mutableList = mutableListOf<Change>()
    ChangeType.values().toList().forEach{ type ->
        mutableList.add(
            change {
                changeType = type
                summary = "Example ${type.name}"
            }
        )
    }
    return mutableList.toList()
}