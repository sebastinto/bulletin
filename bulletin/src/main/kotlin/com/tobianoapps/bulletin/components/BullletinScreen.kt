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
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.tobianoapps.bulletin.data.Bulletin
import com.tobianoapps.bulletin.data.Change
import com.tobianoapps.bulletin.data.Release
import com.tobianoapps.bulletin.data.config.BulletinConfig
import com.tobianoapps.bulletin.util.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


/**
 * Screen that displays a provided [Bulletin].
 *
 * Each [Card] in this [BulletinScreen] holds a list of [Change] for a [Release].
 *
 * @param[modifier] [Modifier] to be applied to the layout of the card.
 * @param[bulletin] list of [Release]
 * @param[filename] file name of json formatted data placed in `res/assets` folder
 * @param[url] url of json formatted data
 * @param[httpTimeout] timeout duration in milliseconds of the http request for a given [url]
 * @param[bulletinConfig] Configuration options
 * @param[urlLoadingView] Optional [Composable] displayed while url data is loading
 * @param[screenHeader] Optional [Composable] displayed at the top of the [BulletinScreen]
 * @param[screenFooter] Optional [Composable] displayed at the bottom of the [BulletinScreen]
 * @param[onReleaseHeaderClick] Callback invoked when a [Card]'s header text is clicked
 * @param[onChangeClick] Callback invoked when a [Change] is clicked
 */
@Composable
fun BulletinScreen(
    modifier: Modifier = Modifier,
    bulletin: Bulletin? = null,
    filename: String? = null,
    url: String? = null,
    httpTimeout: Long = DEFAULT_HTTP_TIMEOUT,
    bulletinConfig: BulletinConfig = BulletinConfig(),
    urlLoadingView: @Composable (() -> Unit)? = { CircularProgressIndicator() },
    screenHeader: @Composable (() -> Unit)? = null,
    screenFooter: @Composable (() -> Unit)? = null,
    onReleaseHeaderClick: ((Release) -> Unit)? = null,
    onChangeClick: ((Change) -> Unit)? = null,
    onError: ((Throwable) -> Unit)? = null
) {
    val context = LocalContext.current

    var remoteBulletin by rememberSaveable(inputs = arrayOf(url)) {
        mutableStateOf<Bulletin?>(null)
    }

    var isLoading by remember {
        mutableStateOf(false)
    }

    if (isLoading) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            urlLoadingView?.let {
                it()
            } ?: CircularProgressIndicator()
        }
    }

    if (bulletin != null) {
        BulletinScreenImpl(
            modifier = modifier,
            bulletin = bulletin,
            bulletinConfig = bulletinConfig,
            screenHeader = screenHeader,
            screenFooter = screenFooter,
            onReleaseHeaderClick = onReleaseHeaderClick,
            onChangeClick = onChangeClick
        )
    } else if (filename != null) {
        with(context.readJsonFromAssets(filename)) {
            onSuccess {
                BulletinScreenImpl(
                    modifier = modifier,
                    bulletin = it,
                    bulletinConfig = bulletinConfig,
                    screenHeader = screenHeader,
                    screenFooter = screenFooter,
                    onReleaseHeaderClick = onReleaseHeaderClick,
                    onChangeClick = onChangeClick
                )
            }
            onFailure {
                onError?.invoke(it)
            }
        }
    } else if (url != null) {
        LaunchedEffect(key1 = url) {
            if (remoteBulletin == null) {
                isLoading = true
                withContext(Dispatchers.IO) {
                    httpGet(url = url, timeout = httpTimeout).also { result ->
                        with(result) {
                            onSuccess { remoteBulletin = it }
                            onFailure { onError?.invoke(it) }
                        }
                    }
                    isLoading = false
                    return@withContext
                }
            }
        }.also {
            remoteBulletin?.let {
                BulletinScreenImpl(
                    modifier = modifier,
                    bulletin = it,
                    bulletinConfig = bulletinConfig,
                    screenHeader = screenHeader,
                    screenFooter = screenFooter,
                    onReleaseHeaderClick = onReleaseHeaderClick,
                    onChangeClick = onChangeClick
                )
            }
        }
    }
}

@Composable
fun BulletinScreenImpl(
    modifier: Modifier = Modifier,
    bulletin: Bulletin,
    bulletinConfig: BulletinConfig = BulletinConfig(),
    screenHeader: @Composable (() -> Unit)? = null,
    screenFooter: @Composable (() -> Unit)? = null,
    onReleaseHeaderClick: ((Release) -> Unit)? = null,
    onChangeClick: ((Change) -> Unit)? = null
) {
    val releases by remember(bulletin, bulletinConfig.showLatestOnly) {
        mutableStateOf(bulletin.sortedByMostRecent(bulletinConfig.showLatestOnly))
    }

    val config by remember {
        mutableStateOf(bulletinConfig)
    }

    val listState = rememberLazyListState()

    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        state = listState,
        contentPadding = PaddingValues(bulletinConfig.style.cardStyle.paddingHorizontal),
        verticalArrangement = Arrangement.spacedBy(config.style.cardStyle.verticalSpacing)
    ) {
        screenHeader?.let {
            item { it() }
        }

        items(items = releases, key = { it.id }) { release ->
            /* Release */
            Card(
                elevation = bulletinConfig.style.cardStyle.elevation,
                shape = config.style.cardStyle.shape,
                backgroundColor = bulletinConfig.bulletinColors.card.backgroundColor,
                border = bulletinConfig.style.cardStyle.borderStroke
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    /* Header */
                    Column(
                        modifier = Modifier
                            .clickable(
                                enabled = onReleaseHeaderClick != null,
                                onClick = {
                                    onReleaseHeaderClick?.invoke(release)
                                }
                            )
                            .padding(
                                top = config.style.cardStyle.verticalSpacing,
                                bottom = config.style.cardStyle.verticalSpacing,
                                start = config.style.cardStyle.paddingHorizontal,
                                end = config.style.cardStyle.paddingHorizontal
                            ),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        ReleaseHeader(
                            release = release,
                            config = bulletinConfig
                        )
                    }

                    if (config.showHeaderDivider) {
                        Divider(
                            modifier = Modifier
                                .padding(
                                    bottom = 0.dp,
                                    start = config.style.cardStyle.headerDividerPaddingHorizontal,
                                    end = config.style.cardStyle.headerDividerPaddingHorizontal
                                )
                        )
                    }
                    /* Changes */
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = if (config.showHeaderDivider) config.style.cardStyle.verticalSpacing else 0.dp,
                                bottom = config.style.cardStyle.verticalSpacing,
                                start = config.style.cardStyle.paddingHorizontal,
                                end = config.style.cardStyle.paddingHorizontal
                            )
                    ) {
                        if (config.groupByChangeType) {
                            release
                                .changes
                                .groupedByChangeType()
                                .forEach { (type, changes) ->
                                    ChangeTypeTag(changeType = type, config = config)
                                    changes.forEach { change ->
                                        ChangeText(
                                            change = change,
                                            showBulletPoint = changes.size > 1,
                                            config = config,
                                            onChangeClickEnabled = onChangeClick != null,
                                            onChangeClick = {
                                                onChangeClick?.invoke(change)
                                            }
                                        )
                                    }
                                }
                        } else {
                            release
                                .changes
                                .forEachIndexed { index, change ->
                                    /* Label */
                                    ChangeTypeTag(
                                        changeType = change.changeType,
                                        config = config,
                                        index = index
                                    )
                                    ChangeText(
                                        change = change,
                                        config = config,
                                        onChangeClickEnabled = onChangeClick != null,
                                        onChangeClick = {
                                            onChangeClick?.invoke(change)
                                        }
                                    )
                                }
                        }
                    }
                }
            }
        }

        screenFooter?.let {
            item { it() }
        }
    }
}