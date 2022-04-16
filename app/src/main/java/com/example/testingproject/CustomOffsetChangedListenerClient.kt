package com.example.testingproject

/**
 * Клиент слушателя на сворачивание/разворачивание шапки (CollapsingToolbarLayout) экрана условий при открытии вклада
 *
 * @author Evgeny Chumak
 */
interface CustomOffsetChangedListenerClient {

    /**
     * Установить отступ шапки (сколько проскроллили)
     *
     * @param offset величина отступа
     */
    fun setAppbarOffset(offset: Int)

    /**
     * Установить общий размер скроллируемой области (шапки)
     *
     * @param totalScrollRange общий размер
     */
    fun setTotalScrollRange(totalScrollRange: Int)
}
