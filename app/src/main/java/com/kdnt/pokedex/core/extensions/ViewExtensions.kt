package com.kdnt.pokedex.core.extensions

import android.content.res.Resources.getSystem
import android.graphics.drawable.Drawable
import android.graphics.drawable.TransitionDrawable
import android.view.View
import android.view.ViewAnimationUtils
import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.core.graphics.drawable.DrawableCompat
import kotlin.math.hypot

/** makes visible a view. */
fun View.visible() {
    visibility = View.VISIBLE
}
/** makes visible a view. */
fun View.hide() {
    visibility = View.GONE
}


/** circular revealed at right-end of a view. */
fun View.circularRevealedAtCenter(@ColorInt color: Int) {
    val cx = 0
    val cy = left
    val finalRadius = hypot(width.toDouble(), height.toDouble())
    if (isAttachedToWindow) {
        ViewAnimationUtils.createCircularReveal(this, cx, cy, 0f, finalRadius.toFloat()).apply {
            DrawableCompat.setTint(background, color)
            visible()
            duration = 550
            start()
        }
    }
}

fun ImageView.setImageDrawableWithAnimation(drawable: Drawable, duration: Int = 300) {
    val currentDrawable = getDrawable()
    if (currentDrawable == null) {
        setImageDrawable(drawable)
        return
    }

    val transitionDrawable = TransitionDrawable(arrayOf(
        currentDrawable,
        drawable
    ))
    setImageDrawable(transitionDrawable)
    transitionDrawable.startTransition(duration)
}

val Int.dp: Int get() = (this / getSystem().displayMetrics.density).toInt()

val Int.px: Int get() = (this * getSystem().displayMetrics.density).toInt()
