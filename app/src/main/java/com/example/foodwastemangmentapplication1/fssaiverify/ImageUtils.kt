package com.example.foodwastemangmentapplication1.fssaiverify


import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import java.io.InputStream

object ImageUtils {
    fun loadBitmapFromUri(context: Context, uri: Uri, maxDimension: Int = 1200): Bitmap? {
        val resolver: ContentResolver = context.contentResolver
        var input: InputStream? = null
        try {
            input = resolver.openInputStream(uri)
            val opts = BitmapFactory.Options().apply { inJustDecodeBounds = true }
            BitmapFactory.decodeStream(input, null, opts)
            input?.close()

            val (originalWidth, originalHeight) = opts.outWidth to opts.outHeight
            var sampleSize = 1
            var max = maxOf(originalWidth, originalHeight)
            while (max / sampleSize > maxDimension) {
                sampleSize *= 2
            }

            val opts2 = BitmapFactory.Options().apply { inSampleSize = sampleSize }
            input = resolver.openInputStream(uri)
            return BitmapFactory.decodeStream(input, null, opts2)
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        } finally {
            input?.close()
        }
    }
}
