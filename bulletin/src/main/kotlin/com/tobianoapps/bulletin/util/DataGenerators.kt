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

import com.tobianoapps.bulletin.data.*
import cz.quanti.quase.loremkotlinum.Lorem
import java.util.concurrent.TimeUnit

fun generateRandomBulletin(size: Int = 10): Bulletin {
    val releases = mutableListOf<Release>()
    repeat((1..size).count()) {
        releases.add(
            Release(
                time = getPastTime(),
                label = getRandomVersion(),
                changes = getRandomChanges()
            )
        )
    }

    return Bulletin(releases = releases)
}

internal fun getRandomVersion(): String {
    val major = (1..4).random()
    val minor = (0..34).random()
    val qualifierString = listOf("alpha", "beta", "").random()

    val qualifierNum = if (qualifierString != "") (1..7).random() else ""
    val qualifier = if (qualifierNum != "") "-$qualifierString$qualifierNum" else ""

    return "$major.$minor$qualifier"
}

internal fun getRandomChanges(size: Int = (2..15).random()): Changes {
    val changes = mutableListOf<Change>()
    repeat((1..size).count()) {
        changes.add(
            Change(
                changeType = getRandomChangeType(),
                summary = Lorem.sentence(10)
            )
        )
    }
    return changes.toList()
}

internal fun getRandomChangeType() = ChangeType.values().random()
internal fun getPastTime() =
    System.currentTimeMillis() - TimeUnit.DAYS.toMillis((1L..365L).random())

