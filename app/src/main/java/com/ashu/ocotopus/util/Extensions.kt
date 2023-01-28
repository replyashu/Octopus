package com.ashu.ocotopus.util

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.util.Base64.*
import java.io.ByteArrayOutputStream

fun Double.toSinglePrecision(): Double {
    return String.format("%.1f", this).toDouble()
}

fun Bitmap.toByteArray(): ByteArray {
    val baos = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.JPEG, 100, baos)
    return baos.toByteArray()
}

fun Bitmap.toUri(context: Context): Uri {
    val bytes = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
    val path: String =
        MediaStore.Images.Media.insertImage(context.contentResolver, this, "Title", null)
    return Uri.parse(path)
}

fun Bitmap.toBase64(): String? {
    val baos = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.JPEG, 100, baos)
    val b = baos.toByteArray()
    return encodeToString(b, NO_WRAP)
}