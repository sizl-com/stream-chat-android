/*
 * Copyright (c) 2014-2023 Stream.io Inc. All rights reserved.
 *
 * Licensed under the Stream License;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    https://github.com/GetStream/stream-chat-android/blob/main/LICENSE
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.getstream.chat.android.offline.plugin.listener.internal

import io.getstream.chat.android.client.models.User
import io.getstream.chat.android.client.plugin.listeners.FetchCurrentUserListener
import io.getstream.chat.android.client.utils.Result

internal class FetchCurrentUserListenerComposite(
    private val fetchCurrentUserListeners: List<FetchCurrentUserListener>,
) : FetchCurrentUserListener {

    override suspend fun onFetchCurrentUserResult(result: Result<User>) {
        fetchCurrentUserListeners.forEach {
            it.onFetchCurrentUserResult(result)
        }
    }
}
