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

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tobianoapps.bulletin.data.ChangeType
import com.tobianoapps.bulletin.data.ChangeType.Companion.tagTheme
import com.tobianoapps.bulletin.data.config.BulletinConfig
import com.tobianoapps.bulletin.util.`if`
import com.tobianoapps.bulletin.util.allCaps
import com.tobianoapps.bulletin.util.hasTagBorder

@Composable
fun ChangeTypeTag(
    changeType: ChangeType,
    config: BulletinConfig,
    index: Int = -1
) {
    Box(
        modifier = Modifier
            .padding(
                top = if (index != 0) config.style.tagStyle.paddingTop else 0.dp,
                bottom = config.style.tagStyle.paddingBottom
            )
            .shadow(
                elevation = config.style.tagStyle.elevation,
                shape = config.style.tagStyle.shape
            )
            .`if`(config.hasTagBorder()) {
                border(
                    width = config.style.tagStyle.borderStroke?.width ?: 0.dp,
                    color = config.tagTheme(changeType = changeType).foregroundColor.copy(alpha = 0.7f),
                    shape = config.style.tagStyle.shape
                )
            }
            .clip(config.style.tagStyle.shape)
            .background(config.tagTheme(changeType = changeType).backgroundColor)
            .padding(
                horizontal = config.style.tagStyle.paddingHorizontal,
                vertical = config.style.tagStyle.paddingVertical
            )
    ) {
        Text(
            text = stringResource(id = changeType.label).allCaps,
            color = config.tagTheme(changeType = changeType).foregroundColor,
            fontWeight = FontWeight.Bold
        )
    }
}