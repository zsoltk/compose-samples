/*
 * Copyright 2019 Google, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.jetnews.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.ui.core.setContent
import com.example.jetnews.data.getPostsWithImagesLoaded
import com.example.jetnews.data.posts
import com.github.zsoltk.compose.backpress.BackPressHandler
import com.github.zsoltk.compose.savedinstancestate.TimeCapsule

class MainActivity : AppCompatActivity() {
    private val backPressHandler = BackPressHandler()
    private val timeCapsule = TimeCapsule()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        posts = getPostsWithImagesLoaded(
            posts,
            resources
        )
        setContent {
            backPressHandler.Provider {
                timeCapsule.Provider(savedInstanceState) {
                    JetnewsApp()
                }
            }
        }
    }

    override fun onBackPressed() {
        if (!backPressHandler.handle()) {
            super.onBackPressed()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        timeCapsule.onSaveInstanceState(outState)
    }
}
