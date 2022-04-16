package com.example.testingproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class FirstFragmentViewModel : ViewModel(), CustomOffsetChangedListenerClient {
    private var totalScrollRange: Int? = null
    var appbarCollapseBorder: Int? = null
    private val _appbarOffset: MutableLiveData<Int> = MutableLiveData()

    private val _isHeaderCollapsed: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        addSource(_appbarOffset) { offset ->
            this@apply.valueIfChanged = totalScrollRange?.let { range ->
                val border = appbarCollapseBorder ?: 0
                (-1) * offset > range - border
            } ?: false
        }
    }

    val isHeaderCollapsed: LiveData<Boolean> = _isHeaderCollapsed


    val imageScale: LiveData<Float> = Transformations.map(_appbarOffset) { offset ->
        val totalScrollRange = totalScrollRange ?: return@map DEFAULT_SCALE
        val total = totalScrollRange.toFloat()
        val normalizedOffset = -1f * offset.toFloat()
        val remainingPath = (total - normalizedOffset) / total
        if (remainingPath <= FASTEN_IMAGE_SCALE) {
            maxOf(remainingPath - 2 * (FASTEN_IMAGE_SCALE - remainingPath), 0f)
        } else {
            remainingPath
        }
    }



    override fun setAppbarOffset(offset: Int) {
        _appbarOffset.value = offset
    }

    override fun setTotalScrollRange(totalScrollRange: Int) {
        this.totalScrollRange = totalScrollRange
    }

    private var <T> MutableLiveData<T>.valueIfChanged: T?
        get() = this.value
        set(newValue: T?) {
            if (newValue != this.value) {
                this.value = newValue
            }
        }

    private companion object {
        val ONE_THIRD = 0.33f
        val FASTEN_IMAGE_SCALE = 0.6f
        val DEFAULT_SCALE = 1f
        val APPBAR_COLLAPSED_BORDER = 0.9f
    }
}