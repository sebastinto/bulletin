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
package com.tobianoapps.sample.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tobianoapps.sample.components.*
import com.tobianoapps.sample.data.sampleBulletin

@Composable
fun SampleNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = SampleScreens.SampleHome.route
    ) {
        composable(route = SampleScreens.SampleHome.route) {
            SampleHome(navController = navController)
        }
        composable(route = SampleScreens.SampleCustomBulletin.route) {
            SampleCustomBulletin()
        }
        composable(route = SampleScreens.SampleDefaultBulletin.route) {
            SampleKotlinJsonBulletin(bulletin = sampleBulletin)
        }
        composable(route = SampleScreens.SampleLocalJsonBulletin.route) {
            SampleLocalJsonBulletin(filename = "bulletin.json")
        }
        composable(route = SampleScreens.SampleRemoteJsonBulletin.route) {
            SampleRemoteJsonBulletin(
                url = "https://bulletin-api.vercel.app/api/data/",
                httpTimeout = 10000L
            )
        }
        composable(route = SampleScreens.SampleCustomLoadingView.route) {
            SampleRemoteJsonBulletin(
                url = "https://bulletin-api.vercel.app/api/data/",
                urlLoadingView = { SampleCustomLoadingView() }
            )
        }
    }
}