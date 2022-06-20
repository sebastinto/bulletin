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
 * A [Change]
 *
 * @param[changeType] can be used to show tags
 * @param[summary] human-readable summary modifications
 */
@Parcelize
data class Change(
    val changeType: ChangeType = ChangeType.DEFAULT,
    val summary: String? = null
) : Parcelable {

    private constructor(builder: Builder) : this(
        builder.changeType,
        builder.summary
    )

    companion object {
        inline fun change(
            block: Builder.() -> Unit
        ) = Builder().apply(block).build()
    }

    class Builder {
        var changeType: ChangeType = ChangeType.DEFAULT
        var summary: String? = null
        fun build() = Change(this)
    }
}