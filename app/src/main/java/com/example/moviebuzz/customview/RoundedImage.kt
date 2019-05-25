package com.example.moviebuzz.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import androidx.core.view.ViewCompat
import com.example.moviebuzz.R

class RoundedImage : ImageView {
    private var mMaskPath: Path? = null
    private val mMaskPaint = Paint(ANTI_ALIAS_FLAG)
    private var mCornerRadius: Float = 10f

    constructor(context: Context) : super(context) {

        init(context)
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {

        init(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {

        init(context)
    }

    private fun init(context: Context) {
        ViewCompat.setLayerType(this, View.LAYER_TYPE_SOFTWARE, null)
        mMaskPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        mMaskPaint.color = context.resources.getColor(R.color.white)
    }

    /**
     * Set the corner radius to use for the RoundedRectangle.
     */
    fun setCornerRadius(cornerRadius: Int) {
        mCornerRadius = cornerRadius.toFloat()
        generateMaskPath(width, height)
        invalidate()
    }

    override fun onSizeChanged(w: Int, h: Int, oldW: Int, oldH: Int) {
        super.onSizeChanged(w, h, oldW, oldH)

        if (w != oldW || h != oldH) {
            generateMaskPath(w, h)
        }
    }

    private fun generateMaskPath(w: Int, h: Int) {
        mMaskPath = Path()
        mMaskPath!!.addRoundRect(
            RectF(0f, 0f, w.toFloat(), h.toFloat()),
            mCornerRadius,
            mCornerRadius,
            Path.Direction.CW
        )
        mMaskPath!!.fillType = Path.FillType.INVERSE_WINDING
    }

    override fun onDraw(canvas: Canvas) {
        if (canvas.isOpaque) { // If canvas is opaque, make it transparent
            canvas.saveLayerAlpha(0f, 0f, width.toFloat(), height.toFloat(), 255, Canvas.ALL_SAVE_FLAG)
        }

        super.onDraw(canvas)

        if (mMaskPath != null) {
            canvas.drawPath(mMaskPath, mMaskPaint)
        }
    }
}