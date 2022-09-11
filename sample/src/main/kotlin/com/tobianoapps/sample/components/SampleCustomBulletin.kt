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
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tobianoapps.bulletin.components.BulletinScreen
import com.tobianoapps.bulletin.util.capitalized
import com.tobianoapps.bulletin.util.forceShowSnackbar
import com.tobianoapps.sample.util.generateRandomBulletin
import com.tobianoapps.sample.data.customBulletinConfig
import com.tobianoapps.sample.data.customShape
import kotlinx.coroutines.launch

@Composable
fun SampleCustomBulletin(modifier: Modifier = Modifier) {
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    val bulletin by rememberSaveable() {
        mutableStateOf(
            generateRandomBulletin()
        )
    }

    Scaffold(
        scaffoldState = scaffoldState,
        snackbarHost = {
            SnackbarHost(hostState = it) { data ->
                Snackbar(
                    snackbarData = data,
                    shape = customShape
                )
            }
        }
    ) { padding ->
        BulletinScreen(
            modifier = Modifier
                .padding(padding)
                .background(if (isSystemInDarkTheme()) Color(0xFF1F1B0F) else Color(0xFFC5AC5F)),
            bulletin = bulletin,
            bulletinConfig = customBulletinConfig,
            screenHeader = { SampleCustomScreenHeader() },
            screenFooter = { SampleCustomScreenFooter() },
            onChangeClick = { change ->
                coroutineScope.launch {
                    scaffoldState.snackbarHostState.forceShowSnackbar(
                        message = "Clicked on \"${change.summary?.capitalized}\"",
                        actionLabel = "OK",
                        logAction = true
                    )
                }
            },
            onReleaseHeaderClick = { release ->
                coroutineScope.launch {
                    scaffoldState.snackbarHostState.forceShowSnackbar(
                        message = "Version ${release.label} header clicked",
                        actionLabel = "OK",
                        logAction = true
                    )
                }
            }
        )
    }
}

@Composable
fun SampleCustomScreenHeader(modifier: Modifier = Modifier) {
    Spacer(modifier = modifier.height(16.dp))
    Text(
        modifier = modifier.fillMaxWidth(),
        text = "Custom Bulletin Screen",
        fontSize = 48.sp,
        textAlign = TextAlign.Center
    )
    Spacer(modifier = modifier.height(16.dp))
}

@Composable
fun SampleCustomScreenFooter(modifier: Modifier = Modifier) {
    Spacer(modifier = modifier.height(16.dp))
    Text(
        modifier = modifier.fillMaxWidth(),
        text = "Fin",
        fontSize = 48.sp,
        fontStyle = FontStyle.Italic,
        fontFamily = FontFamily.Cursive,
        textAlign = TextAlign.Center
    )
    Spacer(modifier = modifier.height(16.dp))
}


@Preview
@Composable
fun PreviewCustomBulletinStyle() {
    SampleCustomBulletin()
}