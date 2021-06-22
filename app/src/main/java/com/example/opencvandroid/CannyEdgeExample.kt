package com.example.opencvandroid

import android.content.Context
import android.graphics.Bitmap
import android.media.Image
import android.view.View
import android.widget.ImageView
import org.opencv.android.Utils
import org.opencv.core.Mat
import org.opencv.imgproc.Imgproc
import java.io.IOException

object CannyEdgeExample {

    fun displayT(context: Context?, v: View) {
        var img: Mat? = null

        try {
            img = Utils.loadResource(context, R.drawable.my_picture)

        } catch (e: IOException) {
            e.printStackTrace()
        }

        Imgproc.cvtColor(img, img, Imgproc.COLOR_RGB2BGRA)

        val img_result : Mat = img!!.clone()
        Imgproc.Canny(img, img_result, 80.0, 90.0)
        val img_bitmap : Bitmap = Bitmap.createBitmap(img_result.cols(), img_result.rows(), Bitmap.Config.ARGB_8888)
        Utils.matToBitmap(img_result, img_bitmap)
        val img_view = v.findViewById<ImageView>(R.id.img)
        img_view.setImageBitmap(img_bitmap)
        
    }
}