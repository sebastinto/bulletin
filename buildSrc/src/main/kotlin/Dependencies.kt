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

import Versions.activityComposeVersion
import Versions.appcompatVersion
import Versions.composeNavigationVersion
import Versions.composeVersion
import Versions.constraintLayoutVersion
import Versions.materialVersion
import Versions.okhttpVersion

object Versions {
    internal const val activityComposeVersion = "1.2.0-alpha08"
    internal const val composeNavigationVersion = "2.5.0-beta01"
    const val composeVersion = "1.2.0-beta02"
    internal const val okhttpVersion = "4.9.3"

    const val materialVersion = "1.7.0-alpha01"
    const val appcompatVersion = "1.4.1"
    const val constraintLayoutVersion = "2.1.3"
}

object Dependencies {
    /* Compose */
    const val composeMaterial = "androidx.compose.material:material:$composeVersion"
    const val composeFoundation  = "androidx.compose.foundation:foundation:$composeVersion"
    const val composeRuntime = "androidx.compose.runtime:runtime:$composeVersion"
    const val composeTooling = "androidx.compose.ui:ui-tooling:$composeVersion"
    const val composeToolingPreview = "androidx.compose.ui:ui-tooling-preview:$composeVersion"

    const val coreKtx =  "androidx.core:core-ktx:1.7.0"

    /* OkHttp */
    const val okhttpBom = "com.squareup.okhttp3:okhttp-bom:$okhttpVersion"
    const val okhttp = "com.squareup.okhttp3:okhttp"

    /* Sample */
    const val material = "com.google.android.material:material:$materialVersion"
    const val appcompat = "androidx.appcompat:appcompat:$appcompatVersion"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:$constraintLayoutVersion"
    const val activityCompose = "androidx.activity:activity-compose:$activityComposeVersion"
    const val navigationCompose = "androidx.navigation:navigation-compose:$composeNavigationVersion"

    /* Text Generator */
    const val loremKotlinum = "com.github.Qase:LoremKotlinum:1.0"

    /* JAVA 8+ APIS ANDROID SDK < 26 */
    const val desugar = "com.android.tools:desugar_jdk_libs:1.1.5"

    /*** TEST ***/
    const val testJunit = "junit:junit:4.13.2"
    const val testTruth = "com.google.truth:truth:1.1.3"
    const val testJson = "org.json:json:20220320"
    const val testCoroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.2"
    const val mockitoCore = "org.mockito:mockito-core:4.6.0"
    const val mockitoKotlin = "com.nhaarman:mockito-kotlin:1.6.0"
    const val mockWebServer = "com.squareup.okhttp3:mockwebserver:4.9.3"
}
