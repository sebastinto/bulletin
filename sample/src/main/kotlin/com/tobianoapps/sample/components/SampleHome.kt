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

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tobianoapps.sample.R
import com.tobianoapps.sample.data.dialogBulletinConfig
import com.tobianoapps.sample.navigation.SampleScreens

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SampleHome(
    modifier: Modifier = Modifier,
    navController: NavController
) {

    var showDialog by rememberSaveable() { mutableStateOf(false) }
    val listState = rememberLazyListState()

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        state = listState,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        item {
            Spacer(modifier = Modifier.height(32.dp))
        }

        item {
            HomeButton(
                text = "Default",
                vectorIcon = Icons.Filled.Star
            ) {
                navController.navigate(SampleScreens.SampleDefaultBulletin.route)
            }
        }

        item {
            HomeButton(
                text = "Custom",
                vectorIcon = Icons.Filled.Build
            ) {
                navController.navigate(SampleScreens.SampleCustomBulletin.route)
            }
        }

        item {
            HomeButton(
                text = "Dialog",
                iconResId = R.drawable.ic_baseline_open_in_new_24
            ) {
                showDialog = true
            }
        }

        item {
            HomeButton(
                text = "Local JSON",
                iconResId = R.drawable.ic_baseline_android_24
            ) {
                navController.navigate(SampleScreens.SampleLocalJsonBulletin.route)
            }
        }

        item {
            HomeButton(
                text = "Remote JSON",
                iconResId = R.drawable.ic_baseline_cloud_download_24
            ) {
                navController.navigate(route = SampleScreens.SampleRemoteJsonBulletin.route)
            }
        }

        item {
            HomeButton(
                text = "Custom Loading View",
                iconResId = R.drawable.loading,
                addBottomSpacer = false
            ) {
                navController.navigate(route = SampleScreens.SampleCustomLoadingView.route)
            }
        }

        item {
            Spacer(modifier = Modifier.height(32.dp))
        }
    }

    if (showDialog) {
        Dialog(
            properties = DialogProperties(usePlatformDefaultWidth = false),
            onDismissRequest = { showDialog = false }
        ) {
            SampleDialogContentBulletin(
                bulletinConfig = dialogBulletinConfig
            ) { showDialog = false }
        }
    }
}

/**
 * Re-usable [Composable] button for the [SampleHome] screen.
 *
 * @param[text] Text shown on the button
 * @param[vectorIcon] Shown in front of text - use this with [Icons]
 * @param[iconResId] Shown in front of text - use this with @DrawableRes
 * @param[onClick] Action triggered on button click
 */
@Composable
fun HomeButton(
    modifier: Modifier = Modifier,
    text: String,
    vectorIcon: ImageVector? = null,
    iconResId: Int? = null,
    addBottomSpacer: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick.invoke() },
        contentPadding = PaddingValues(
            start = 20.dp,
            top = 12.dp,
            end = 20.dp,
            bottom = 12.dp
        )
    ) {
        when {
            vectorIcon != null -> {
                Icon(
                    imageVector = vectorIcon,
                    contentDescription = text,
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
            }
            iconResId != null -> {
                Icon(
                    painter = painterResource(id = iconResId),
                    contentDescription = text,
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
            }
        }

        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
        Text(text = text)
    }

    if (addBottomSpacer) Spacer(modifier = Modifier.size(48.dp))
}

@Preview
@Composable
fun PreviewSampleHome() {
    val navController = rememberNavController()
    SampleHome(navController = navController)
}
