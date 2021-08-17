package io.getstream.chat.android.ui.message.list.adapter.viewholder.decorator.internal

import androidx.core.view.isVisible
import com.getstream.sdk.chat.adapter.MessageListItem
import com.getstream.sdk.chat.utils.extensions.isBottomPosition
import io.getstream.chat.android.ui.avatar.AvatarView
import io.getstream.chat.android.ui.message.list.MessageListView
import io.getstream.chat.android.ui.message.list.adapter.viewholder.internal.GiphyViewHolder
import io.getstream.chat.android.ui.message.list.adapter.viewholder.internal.MessagePlainTextViewHolder
import io.getstream.chat.android.ui.message.list.adapter.viewholder.internal.TextAndAttachmentsViewHolder

internal class AvatarDecorator(
    private val showAvatarPredicate: MessageListView.ShowAvatarPredicate? = null,
) : BaseDecorator() {
    override fun decorateTextAndAttachmentsMessage(
        viewHolder: TextAndAttachmentsViewHolder,
        data: MessageListItem.MessageItem,
    ) {
        setupAvatar(getAvatarView(viewHolder, data), data)
    }

    override fun decoratePlainTextMessage(
        viewHolder: MessagePlainTextViewHolder,
        data: MessageListItem.MessageItem,
    ) {
        setupAvatar(getAvatarView(viewHolder, data), data)
    }

    override fun decorateGiphyMessage(
        viewHolder: GiphyViewHolder,
        data: MessageListItem.MessageItem,
    ) = Unit

    // override fun decorateDeletedMessage(viewHolder: MessageDeletedViewHolder, data: MessageListItem.MessageItem) {
    //     setupAvatar(getAvatarView(viewHolder, data), data)
    // }

    private fun setupAvatar(avatarView: AvatarView, data: MessageListItem.MessageItem) {
        if (showAvatarPredicate != null) {
            val shouldShow = showAvatarPredicate.shouldShow(data)
            avatarView.isVisible = shouldShow
            
            if (shouldShow) {
                avatarView.setUserData(data.message.user)
            }
        } else if (data.isTheirs && data.isTheirs && data.isBottomPosition()) {
            avatarView.setUserData(data.message.user)
            avatarView.isVisible = true
        } else {
            avatarView.isVisible = false
        }
    }

    private fun getAvatarView(holder: TextAndAttachmentsViewHolder, data: MessageListItem.MessageItem): AvatarView {
        return if (data.isMine) holder.binding.avatarMineView else holder.binding.avatarView
    }

    private fun getAvatarView(holder: MessagePlainTextViewHolder, data: MessageListItem.MessageItem): AvatarView {
        return if (data.isMine) holder.binding.avatarMineView else holder.binding.avatarView
    }

    // private fun getAvatarView(holder: MessageDeletedViewHolder, data: MessageListItem.MessageItem): AvatarView {
    //     return if (data.isMine) holder.binding.avatarMineView else holder.binding.avatarView
    // }
}
