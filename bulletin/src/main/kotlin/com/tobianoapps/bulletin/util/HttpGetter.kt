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

import com.tobianoapps.bulletin.data.Bulletin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.internal.closeQuietly
import okio.IOException
import java.io.BufferedInputStream
import java.net.HttpURLConnection
import java.util.concurrent.TimeUnit
import kotlin.Result.Companion.failure
import kotlin.Result.Companion.success

internal const val DEFAULT_HTTP_TIMEOUT = 5000L

/**
 * Make a network request for [url]
 *
 * @param[url] The url for the request
 * @param[timeout] Http request timeout duration in milliseconds.
 */
internal suspend fun httpGet(
    url: String,
    timeout: Long = DEFAULT_HTTP_TIMEOUT
) = coroutineScope {
    val client = OkHttpClient.Builder().callTimeout(timeout, TimeUnit.MILLISECONDS).build()
    HttpGetter(url = url, callFactory = lazyOf(client)).get()
}


internal class HttpGetter(
    private val url: String,
    private var callFactory: Lazy<Call.Factory>
) {

    private fun okhttpRequest() = Request.Builder().url(url).build()

    suspend fun get(): Result<Bulletin?> = withContext(Dispatchers.IO) {
        val bulletin: Bulletin?
        var response: Response? = null

        try {
            response = executeNetworkRequest(okhttpRequest())

            if (response.isSuccessful && response.code == HttpURLConnection.HTTP_OK) {
                bulletin = BufferedInputStream(response.body?.byteStream())
                    .bufferedReader()
                    .readText()
                    .parseJSON()
            } else {
                throw Exception("${response.code} - ${response.message}")
            }

            success(bulletin)
        } catch (e: Exception) {
            failure(e)
        } finally {
            response?.closeQuietly()
        }
    }

    @Throws(IOException::class)
    suspend fun executeNetworkRequest(request: Request): Response = withContext(Dispatchers.IO) {
        callFactory.value
            .newCall(request)
            .execute()
    }
}







