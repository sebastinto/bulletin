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

package com.tobianoapps.bulletin

import com.tobianoapps.bulletin.data.Bulletin
import com.tobianoapps.bulletin.data.Change
import com.tobianoapps.bulletin.data.ChangeType
import com.tobianoapps.bulletin.data.Release

const val VALID_JSON_STRING =
    "[{\"time\":1652229320349,\"label\":\"1.0.0-RC2\",\"changes\":[{\"changeType\":\"NEW\",\"summary\":\"Bulletin is easy!\"},{\"changeType\":\"DEFAULT\",\"summary\":\"Kotlin DSL FTW!\"}]},{\"time\":0,\"label\":\"0.0.0\",\"changes\":[{\"changeType\":\"DEFAULT\",\"summary\":\"Example DEFAULT.\"},{\"changeType\":\"IMPROVED\",\"summary\":\"Example IMPROVED.\"},{\"changeType\":\"FIXED\",\"summary\":\"Example FIXED.\"},{\"changeType\":\"NEW\",\"summary\":\"Example NEW.\"},{\"changeType\":\"REMOVED\",\"summary\":\"Example REMOVED.\"},{\"changeType\":\"DEPRECATED\",\"summary\":\"Example DEPRECATED.\"},{\"changeType\":\"SECURITY\",\"summary\":\"Example SECURITY.\"}]}]"

const val INVALID_JSON_STRING =
    "[{\"date\":1652229320349,\"version\":\"1.0.0-RC2\",\"changes\":[{\"changeType\":\"NEW\",\"summary\":\"Bulletin is easy!\"},{\"changeType\":\"DEFAULT\",\"summary\":\"Kotlin DSL FTW!\"}]},{\"time\":0,\"label\":\"0.0.0\",\"changes\":[{\"changeType\":\"DEFAULT\",\"summary\":\"Example DEFAULT.\"},{\"changeType\":\"IMPROVED\",\"summary\":\"Example IMPROVED.\"},{\"changeType\":\"FIXED\",\"summary\":\"Example FIXED.\"},{\"changeType\":\"NEW\",\"summary\":\"Example NEW.\"},{\"changeType\":\"REMOVED\",\"summary\":\"Example REMOVED.\"},{\"changeType\":\"DEPRECATED\",\"summary\":\"Example DEPRECATED.\"},{\"changeType\":\"SECURITY\",\"summary\":\"Example SECURITY.\"}]}]"

val mockBulletin = Bulletin.bulletin {
    releases = listOf(
        Release(
            time = 1652229320349,
            label = "1.0.0-RC2",
            changes = listOf(
                Change.change {
                    changeType = ChangeType.NEW
                    summary = "Bulletin is easy!"
                },
                Change.change {
                    changeType = ChangeType.DEFAULT
                    summary = "Kotlin DSL FTW!"
                }
            )
        ),
        Release(
            time = 0,
            label = "0.0.0",
            changes = listOf(
                Change.change {
                    changeType = ChangeType.DEFAULT
                    summary = "Example DEFAULT."
                },
                Change.change {
                    changeType = ChangeType.IMPROVED
                    summary = "Example IMPROVED."
                },
                Change.change {
                    changeType = ChangeType.FIXED
                    summary = "Example FIXED."
                },
                Change.change {
                    changeType = ChangeType.NEW
                    summary = "Example NEW."
                },
                Change.change {
                    changeType = ChangeType.REMOVED
                    summary = "Example REMOVED."
                },
                Change.change {
                    changeType = ChangeType.DEPRECATED
                    summary = "Example DEPRECATED."
                },
                Change.change {
                    changeType = ChangeType.SECURITY
                    summary = "Example SECURITY."
                }
            )
        )
    )
}
