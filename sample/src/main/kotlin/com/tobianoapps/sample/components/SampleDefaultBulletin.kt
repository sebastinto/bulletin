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
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.tobianoapps.bulletin.components.BulletinScreen
import com.tobianoapps.bulletin.data.Bulletin
import com.tobianoapps.bulletin.util.capitalized
import com.tobianoapps.bulletin.util.forceShowSnackbar
import com.tobianoapps.sample.data.sampleBulletin
import kotlinx.coroutines.launch

const val DEFAULT_HTTP_TIMEOUT = 5000L

@Composable
fun SampleLocalJsonBulletin(
    modifier: Modifier = Modifier,
    filename: String? = null
) {
    SampleDefaultBulletin(modifier = modifier, filename = filename)
}

@Composable
fun SampleRemoteJsonBulletin(
    modifier: Modifier = Modifier,
    url: String? = null,
    httpTimeout: Long = DEFAULT_HTTP_TIMEOUT,
    urlLoadingView: @Composable (() -> Unit)? = null
) {
    SampleDefaultBulletin(
        modifier = modifier,
        url = url,
        httpTimeout = httpTimeout,
        urlLoadingView = urlLoadingView
    )
}

@Composable
fun SampleKotlinJsonBulletin(
    modifier: Modifier = Modifier,
    bulletin: Bulletin? = null
) {
    SampleDefaultBulletin(modifier = modifier, bulletin = bulletin)
}

@Composable
fun SampleDefaultBulletin(
    modifier: Modifier = Modifier,
    bulletin: Bulletin? = null,
    filename: String? = null,
    url: String? = null,
    httpTimeout: Long = DEFAULT_HTTP_TIMEOUT,
    urlLoadingView: @Composable (() -> Unit)? = null
) {
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        modifier = modifier.background(
            if (isSystemInDarkTheme()) Color(0xFF121212) else Color(
                0xFFFAFAFA
            )
        ),
        scaffoldState = scaffoldState,
    ) { padding ->
        BulletinScreen(
            modifier = Modifier.padding(padding),
            bulletin = bulletin,
            filename = filename,
            url = url,
            httpTimeout = httpTimeout,
            urlLoadingView = urlLoadingView,
            onReleaseHeaderClick = { release ->
                coroutineScope.launch {
                    scaffoldState.snackbarHostState.forceShowSnackbar(
                        message = "Release ${release.label} header clicked",
                        actionLabel = "OK",
                        logAction = true
                    )
                }
            },
            onChangeClick = { change ->
                coroutineScope.launch {
                    scaffoldState.snackbarHostState.forceShowSnackbar(
                        message = "Clicked on \"${change.summary?.capitalized}\"",
                        actionLabel = "OK",
                        logAction = true
                    )
                }
            }
        ) {
            coroutineScope.launch {
                scaffoldState.snackbarHostState.forceShowSnackbar(
                    message = "$it",
                    actionLabel = "OK",
                    logAction = true
                )
            }
        }
    }
}


@Preview
@Composable
fun PreviewDefaultBulletinStyle() {
    SampleDefaultBulletin(bulletin = sampleBulletin)
}