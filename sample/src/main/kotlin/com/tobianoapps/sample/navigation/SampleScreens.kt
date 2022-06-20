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

sealed class SampleScreens(val route: String) {
    object SampleCustomBulletin : SampleScreens(route= "sample_custom_bulletin")
    object SampleDefaultBulletin : SampleScreens(route= "sample_default_bulletin")
    object SampleLocalJsonBulletin : SampleScreens(route= "sample_local_json_bulletin")
    object SampleRemoteJsonBulletin : SampleScreens(route= "sample_remote_json_bulletin")
    object SampleCustomLoadingView : SampleScreens(route= "sample_custom_loading_view")
    object SampleHome : SampleScreens(route= "sample_home")
}