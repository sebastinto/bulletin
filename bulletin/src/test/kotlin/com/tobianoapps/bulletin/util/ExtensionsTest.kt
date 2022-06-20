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

import com.google.common.truth.Truth
import com.tobianoapps.bulletin.data.Bulletin.Companion.bulletin
import com.tobianoapps.bulletin.data.Change
import com.tobianoapps.bulletin.data.ChangeType.*
import com.tobianoapps.bulletin.data.Release
import org.junit.Test
import java.time.ZoneId
import java.util.*

class ExtensionsTest {

    private val epochMillis = 1646113134534L

    private val new = Change(changeType = NEW, summary = "new")
    private val fixed = Change(changeType = FIXED, summary = "fixed")
    private val alsoNew = Change(changeType = NEW, summary = "also new")
    private val removed = Change(changeType = REMOVED, summary = "removed")
    private val alsoRemoved = Change(changeType = REMOVED, summary = "also removed")
    private val springCleaning = Change(changeType = REMOVED, summary = "spring cleaning")
    private val nullNew = Change(changeType = NEW, summary = null)

    private val bulletin = bulletin {
        releases = listOf(
            Release(
                time = 0,
                label = "zero",
                changes = listOf(
                    new,
                    fixed,
                    alsoNew
                )
            ),
            Release(
                time = 1,
                label = "one",
                changes = listOf(
                    removed,
                    alsoRemoved,
                    springCleaning
                )
            ),
            Release(
                time = 2,
                label = "two",
                changes = listOf(nullNew)
            )
        )
    }

    private val bulletinSortedByMostRecent = listOf(
        Release(
            time = 2,
            label = "two",
            changes = listOf(nullNew)
        ),
        Release(
            time = 1,
            label = "one",
            changes = listOf(
                removed,
                alsoRemoved,
                springCleaning
            )
        ),
        Release(
            time = 0,
            label = "zero",
            changes = listOf(
                new,
                fixed,
                alsoNew
            )
        )
    )

    private val groupedChanges = mapOf(
            Pair(
                first = NEW,
                second = listOf(
                    new,
                    alsoNew
                )
            ),
            Pair(
                first = FIXED,
                second = listOf(
                    fixed
                )
            )
        )

    @Test
    fun `localDate extensions without param return correct LocalDate`() {
        Truth
            .assertThat(epochMillis.localDate())
            .isEqualTo("Feb 28, 2022")
    }

    @Test
    fun `localDate extensions with zoneId param return correct LocalDate`() {
        Truth
            .assertThat(epochMillis.localDate(ZoneId.of("Europe/Paris")))
            .isEqualTo("Mar 1, 2022")
    }

    @Test
    fun `localDate with locale param return correct string`() {
        Truth
            .assertThat(
                epochMillis.localDate(
                    zoneId = ZoneId.of("Europe/Paris"),
                    locale = Locale.FRANCE
                ),
            )
            .isEqualTo("1 mars 2022")
    }

    @Test
    fun `bulletin list sortedByMostRecent return correctly sorted list`() {
        Truth
            .assertThat(bulletin.sortedByMostRecent())
            .isEqualTo(bulletinSortedByMostRecent)
    }

    @Test
    fun `bulletin list groupedByChangeType return correct map`() {
        Truth
            .assertThat(bulletin.releases[0].changes.groupedByChangeType())
            .isEqualTo(groupedChanges)
    }
}