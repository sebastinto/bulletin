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
import kotlinx.parcelize.Parcelize


/**
 * A [Bulletin] represents a changelog.
 *
 * @param[releases] List of [Release] commonly mapped to release versions.
 */
@Parcelize
data class Bulletin(val releases: List<Release> = emptyList()) : Parcelable {

    private constructor(builder: Builder) : this (
        builder.releases
    )

    companion object {
        inline fun bulletin(
            block: Builder.() -> Unit
        ) = Builder().apply(block).build()
    }

    class Builder {
        var releases: List<Release> = emptyList()
        fun build() = Bulletin(this)
    }
}





