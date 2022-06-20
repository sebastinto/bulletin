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

import android.content.Context
import android.util.Log
import com.tobianoapps.bulletin.data.Bulletin
import com.tobianoapps.bulletin.data.Change
import com.tobianoapps.bulletin.data.ChangeType
import com.tobianoapps.bulletin.data.Release
import org.json.JSONArray
import org.json.JSONTokener
import kotlin.Result.Companion.failure
import kotlin.Result.Companion.success

@Throws(Exception::class)
internal fun String.parseJSON(): Bulletin {
    val releases = mutableListOf<Release>()
    val jsonArray = JSONTokener(this).nextValue() as JSONArray

    for (i in 0 until jsonArray.length()) {
        val changeList = mutableListOf<Change>()

        val time = jsonArray.getJSONObject(i).getLong("time")
        val label = jsonArray.getJSONObject(i).getString("label")
        val changes = jsonArray.getJSONObject(i).getJSONArray("changes")

        for (x in 0 until changes.length()) {
            val changeType = changes.getJSONObject(x).getString("changeType")
            val summary = changes.getJSONObject(x).getString("summary")
            changeList.add(
                Change(
                    changeType = ChangeType.fromLabel(changeType),
                    summary = summary
                )
            )
        }

        releases.add(
            Release(
                time = time,
                label = label,
                changes = changeList.toList()
            )
        )
    }
    return Bulletin(releases = releases.toList())
}

internal fun Context.readJsonFromAssets(fileName: String): Result<Bulletin> =
    try {
        success(
            this.assets
                .open(fileName)
                .bufferedReader()
                .use {
                    it.readText().parseJSON()
                }
        )
    } catch (e: Exception) {
        failure(e)
    }

internal fun Bulletin?.debugLog(): String {
    var string = ""
    this?.releases?.forEach { release ->
        string += "label: ${release.label}\n"
        string += "time: ${release.time}\n"
        string += "changes:\n"
        release.changes.forEach {
            string += "\tchangeType: ${it.changeType}\n"
            string += "\tsummary: ${it.summary}\n"
        }
    }
    Log.d("bulletin:", string)
    return string
}