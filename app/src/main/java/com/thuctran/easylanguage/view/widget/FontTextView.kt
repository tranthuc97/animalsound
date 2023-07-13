package com.thuctran.easylanguage.view.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.thuctran.easylanguage.App

@SuppressLint("ViewConstructor")
class FontTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) :
    AppCompatTextView(context, attrs) {
        private fun initViews(){
            typeface = Typeface.createFromAsset(App.INSTANCE.assets,"đường dẫn thư mục, vd: font/...")
        }
}