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

import com.google.common.truth.Truth.assertThat
import com.tobianoapps.bulletin.VALID_JSON_STRING
import com.tobianoapps.bulletin.mockBulletin
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.util.concurrent.TimeUnit
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

class HttpGetterTest {

    private lateinit var server: MockWebServer
    private val mockSuccessResponse = MockResponse().apply {
        setBody(VALID_JSON_STRING)
        setResponseCode(200)
    }

    private val mockFailureResponse = MockResponse().apply {
        setResponseCode(404)
    }

    private val validUrl = "https://bulletin-api.vercel.app/api/data/"
    private val nonExistentUrl = "https://bullletin-api.vercel.app/api/data/"
    private val malformedUrl = "bulletin-api.vercel.app/api/data/"

    @Before
    fun before() {
        server = MockWebServer()
    }

    @After
    fun after() {
        server.shutdown()
    }


    @Test
    fun `httpGet with success response return correct Bulletin`() = runTestAsync {
        server.enqueue(mockSuccessResponse)
        val result = httpGet(url = server.url(validUrl).toString())
        assertThat(result.getOrNull()).isEqualTo(mockBulletin)
    }

    @Test
    fun `httpGet with timeout returns timeout failure`() = runTestAsync {
        server.enqueue(mockSuccessResponse)
        val result = httpGet(url = server.url(validUrl).toString(), timeout = 1L)
        assertThat(result.exceptionOrNull()).isInstanceOf(java.io.InterruptedIOException::class.java)
    }

    @Test
    fun `httpGet with malformed url returns failure`() = runTestAsync {
        server.enqueue(mockFailureResponse)
        val result = httpGet(url = server.url(malformedUrl).toString())
        assertThat(result.isFailure).isTrue()
    }

    @Test
    fun `httpGet with error response returns failure`() = runTestAsync {
        server.enqueue(mockFailureResponse)
        val result = httpGet(url = server.url(nonExistentUrl).toString())
        assertThat(result.isFailure).isTrue()
    }
}


@OptIn(ExperimentalCoroutinesApi::class)
fun runTestAsync(
    context: CoroutineContext = EmptyCoroutineContext,
    block: suspend CoroutineScope.() -> Unit
) = runTest(context) {
    withContext(Dispatchers.IO, block)
}