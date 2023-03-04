package io.getstream.chat.android.ui.gallery

import android.view.View
import androidx.viewpager2.widget.ViewPager2

internal class ViewPagerDepthTransformer : ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            page.alpha = 0f
            page.translationZ = 1f
        } else if (position <= 0) { // [-1,0]
            page.translationZ = 1f
            page.alpha = 1f
            page.translationX = 0f
            page.scaleX = 1f
            page.scaleY = 1f
        } else if (position <= 1) { // (0,1]
            page.translationZ = 0f
            page.translationX = -position * page.width
            page.alpha = 1 - Math.abs(position)
            page.scaleX = 1 - Math.abs(position * .3f)
            page.scaleY = 1 - Math.abs(position * .3f)
        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            page.alpha = 0f
            page.translationZ = 0f
        }
    }
}
