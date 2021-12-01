package com.example.android.emojicompat

import android.content.Context
import androidx.appcompat.widget.AppCompatTextView
import android.text.InputFilter
import android.util.AttributeSet
import androidx.emoji2.viewsintegration.EmojiTextViewHelper

open class CustomTextView : AppCompatTextView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
        super(context, attrs, defStyleAttr)

    private var _emojiTextViewHelper: EmojiTextViewHelper? = null
    private val emojiTextViewHelper: EmojiTextViewHelper
        get() = _emojiTextViewHelper ?: EmojiTextViewHelper(this).also { _emojiTextViewHelper = it }

    init {
        emojiTextViewHelper.updateTransformationMethod()
    }

    override fun setFilters(filters: Array<InputFilter>) {
        super.setFilters(emojiTextViewHelper.getFilters(filters))
    }

    override fun setAllCaps(allCaps: Boolean) {
        super.setAllCaps(allCaps)
        emojiTextViewHelper.setAllCaps(allCaps)
    }
}