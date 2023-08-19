package com.ashu.ocotopus.util

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.os.SystemClock
import android.provider.MediaStore
import android.util.Base64.*
import android.view.View
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

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

fun View.clickWithDebounce(debounceTime: Long = 600L, action: () -> Unit) {
    this.setOnClickListener(object : View.OnClickListener {
        private var lastClickTime: Long = 0

        override fun onClick(v: View) {
            if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) return
            else action()

            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}

fun Bitmap.toFile(): File? {
        val extStorageDirectory = Environment.getExternalStorageDirectory().toString();
        var outStream: OutputStream? = null;
        // String temp = null;
        var file: File? = File(extStorageDirectory, "temp.png");
        file?.let {
            if (file!!.exists()) {
                file!!.delete();
                file = File(extStorageDirectory, "temp.png");
            }
        }
        try {
            outStream = FileOutputStream(file);
            this.compress(Bitmap.CompressFormat.PNG, 100, outStream);
            outStream.flush();
            outStream.close();

        } catch (e: Exception) {
            e.printStackTrace();
            file = null;
        }
        return file;
}