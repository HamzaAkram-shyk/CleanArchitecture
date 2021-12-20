package com.example.extentions

import android.text.Spannable
import android.text.SpannableStringBuilder
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

fun TextView.addImage(atText: String, @DrawableRes imgSrc: Int, imgWidth: Int, imgHeight: Int) {
    val ssb = SpannableStringBuilder(this.text)

    val drawable = ContextCompat.getDrawable(this.context, imgSrc) ?: return
    drawable.mutate()
    drawable.setBounds(
        0, 0,
        imgWidth,
        imgHeight
    )
    val start = text.indexOf(atText)
    ssb.setSpan(
        VerticalImageSpan(drawable),
        start,
        start + atText.length,
        Spannable.SPAN_INCLUSIVE_EXCLUSIVE
    )
    this.setText(ssb, TextView.BufferType.SPANNABLE)
}


@BindingAdapter("loadImage")
fun ImageView.loadImage(@DrawableRes src: Int) {
    Glide.with(this)
        .load(src)
        .into(this)
}