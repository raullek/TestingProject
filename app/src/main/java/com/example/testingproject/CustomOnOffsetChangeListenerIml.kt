package com.example.testingproject

import com.google.android.material.appbar.AppBarLayout

/**
 * Слушатель на сворачивание/разворачивание шапки (CollapsingToolbarLayout) экрана условий при открытии вклада
 *
 * @param client клиент, получающий необходимую информацию о скролле шапки
 *
 * @author Evgeny Chumak
 */
class CustomOnOffsetChangeListenerIml(
    private val client: CustomOffsetChangedListenerClient,
) : AppBarLayout.OnOffsetChangedListener {
    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        appBarLayout ?: return
        val totalScrollRange = appBarLayout.totalScrollRange
        client.setTotalScrollRange(totalScrollRange)
        client.setAppbarOffset(verticalOffset)
    }
}
