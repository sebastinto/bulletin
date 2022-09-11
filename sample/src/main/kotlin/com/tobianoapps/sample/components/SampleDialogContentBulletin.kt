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

package com.tobianoapps.sample.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.tobianoapps.bulletin.components.BulletinScreen
import com.tobianoapps.bulletin.data.config.BulletinConfig
import com.tobianoapps.bulletin.util.allCaps
import com.tobianoapps.sample.util.generateRandomBulletin

@Composable
fun SampleDialogContentBulletin(
    modifier: Modifier = Modifier,
    bulletinConfig: BulletinConfig,
    onButtonClick: () -> Unit
) {
    val bulletin by rememberSaveable {
        mutableStateOf(generateRandomBulletin())
    }

    Column(
        modifier = modifier
            .padding(32.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(bulletinConfig.bulletinColors.card.backgroundColor)
    ) {
        Text(
            text = "Changelog",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 16.dp , bottom = 5.dp)
                .fillMaxWidth(),
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.onSurface,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        Column(
            modifier = Modifier.weight(1f, false)
        ) {
            BulletinScreen(
                bulletin = bulletin,
                bulletinConfig = bulletinConfig
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Button(onClick = { onButtonClick.invoke() }) {
                Text(
                    text = "ok".allCaps,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colors.onError,
                )
            }
        }
    }
}