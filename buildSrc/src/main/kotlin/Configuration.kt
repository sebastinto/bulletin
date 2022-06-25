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

object Configuration {
    const val compileSdk = 31
    const val targetSdk = 31
    const val minSdk = 21
    private const val majorVersion = 1
    private const val minorVersion = 0
    private const val patchVersion = 1
    private const val qualifier = ""
    const val versionName = "$majorVersion.$minorVersion.$patchVersion$qualifier"
    const val sampleVersionCode = 2
    const val sampleVersionName = "$majorVersion.$minorVersion.$patchVersion"
    const val groupId = "com.tobianoapps"
    const val artifactId = "bulletin"
}