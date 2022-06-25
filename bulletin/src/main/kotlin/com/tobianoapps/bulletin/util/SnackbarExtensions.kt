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

package com.tobianoapps.bulletin.util

import android.util.Log
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.SnackbarResult

internal fun SnackbarResult.log() =
    when (this) {
        SnackbarResult.ActionPerformed -> Log.d("SampleDefaultBulletin", "Snackbar action performed")
        SnackbarResult.Dismissed -> Log.d("SampleDefaultBulletin", "Snackbar dismissed")
    }

suspend fun SnackbarHostState.forceShowSnackbar(
    message: String,
    actionLabel: String? = null,
    logAction: Boolean = false
) {
    this.currentSnackbarData?.dismiss()
    this.showSnackbar(
        message = message,
        actionLabel = actionLabel
    ).also {
        if (logAction) it.log()
    }
}