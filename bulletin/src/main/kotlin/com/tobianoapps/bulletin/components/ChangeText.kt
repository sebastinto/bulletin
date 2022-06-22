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

package com.tobianoapps.bulletin.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tobianoapps.bulletin.data.Change
import com.tobianoapps.bulletin.data.config.BulletinConfig
import com.tobianoapps.bulletin.util.`if`
import com.tobianoapps.bulletin.util.capitalized


/**
 * [Composable] that displays a [Change.summary] and [Change.changeType]
 *
 * @param[modifier] [Modifier] to be applied to this layout.
 * @param[change] used to set text
 * @param[config] used to configure display options and style
 * @param[showBulletPoint] indicates that [Change.summary] should be prepended with a bullet point.
 * Used when changes are grouped and displayed as a bulleted list.
 * @param[onChangeClickEnabled] whether a [Modifier.clickable] should be applied to this [Composable]
 * @param[onChangeClick] callback when this [Composable]'s [Text] is tapped.
 */
@Composable
internal fun ChangeText(
    modifier: Modifier = Modifier,
    change: Change,
    config: BulletinConfig,
    showBulletPoint: Boolean = false,
    onChangeClickEnabled: Boolean = false,
    onChangeClick: ((Change) -> Unit)? = null
) {
    change.summary?.let { str ->
        Row(modifier = modifier) {
            Text(
                modifier = Modifier
                    .`if`(onChangeClickEnabled) {
                        clickable {
                            onChangeClick?.invoke(change)
                        }
                    }
                    .padding(4.dp),
                text = "${if (showBulletPoint) "• " else ""}${str.capitalized}",
                color = config.bulletinColors.card.foregroundColor
            )
        }
    }
}