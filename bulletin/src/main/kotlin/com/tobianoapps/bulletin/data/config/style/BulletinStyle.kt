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

package com.tobianoapps.bulletin.data.config.style

import com.tobianoapps.bulletin.components.BulletinScreen
import com.tobianoapps.bulletin.data.Release
import com.tobianoapps.bulletin.data.Change

/**
 * [BulletinStyle] represents styling options for rendering a [BulletinScreen]
 *
 * @param[cardStyle] styling properties for [Release] cards
 * @param[tagStyle] styling properties for [Change] tags
 */
class BulletinStyle(
    val cardStyle: BulletinCardStyle = BulletinCardStyle(),
    val tagStyle: BulletinTagStyle = BulletinTagStyle()
) {
    private constructor(builder: Builder) : this(
        builder.cardStyle,
        builder.tagStyle
    )

    companion object {
        inline fun bulletinStyle(block: Builder.() -> Unit) = Builder().apply(block).build()
    }

    class Builder {
        var cardStyle: BulletinCardStyle = BulletinCardStyle()
        var tagStyle: BulletinTagStyle = BulletinTagStyle()
        fun build() = BulletinStyle(this)
    }
}
