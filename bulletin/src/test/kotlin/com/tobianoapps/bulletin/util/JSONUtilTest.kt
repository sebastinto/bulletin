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
import com.tobianoapps.bulletin.INVALID_JSON_STRING
import com.tobianoapps.bulletin.VALID_JSON_STRING
import com.tobianoapps.bulletin.mockBulletin
import org.junit.Assert.assertThrows
import org.junit.Test

class JSONUtilTest {

    @Test
    fun `parseJSON with valid JSON string return correct Bulletin`() {
        assertThat(VALID_JSON_STRING.parseJSON()).isEqualTo(mockBulletin)
    }

    @Test
    fun `parseJSON with invalid JSON string throws`() {
        assertThrows(Exception::class.java) { INVALID_JSON_STRING.parseJSON() }
    }


}