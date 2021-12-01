package com.example.android.emojicompat

import android.content.Context
import android.text.method.KeyListener
import android.util.AttributeSet
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection
import androidx.appcompat.widget.AppCompatEditText
import androidx.emoji2.viewsintegration.EmojiEditTextHelper

class CustomEditText : AppCompatEditText {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
        super(context, attrs, defStyleAttr)

    private var _emojiEditTextHelper: EmojiEditTextHelper? = null

    private val emojiEditTextHelper: EmojiEditTextHelper
        get() = _emojiEditTextHelper ?: EmojiEditTextHelper(this).also { _emojiEditTextHelper = it }

    init {
        keyListener = super.getKeyListener()
    }

    override fun setKeyListener(keyListener: KeyListener?) {
        val listener =
            if (keyListener != null) emojiEditTextHelper.getKeyListener(keyListener) else keyListener
        super.setKeyListener(listener)
    }

    override fun onCreateInputConnection(outAttrs: EditorInfo): InputConnection? {
        val inputConnection = super.onCreateInputConnection(outAttrs)
        return emojiEditTextHelper.onCreateInputConnection(inputConnection, outAttrs)
    }
}