package com.example.opencvandroid

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import org.opencv.android.OpenCVLoader
import org.opencv.android.Utils
import org.opencv.core.Mat
import org.opencv.imgproc.Imgproc


open class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        OpenCVLoader.initDebug()
    }

    fun displayToast(v: View) {

        var img: Mat? = null

        try {
            img = Utils.loadResource(applicationContext, R.drawable.my_picture)

        } catch(e: Exception){
            e.printStackTrace()
        }

        Imgproc.cvtColor(img, img, Imgproc.COLOR_RGB2BGRA)

        val img_result: Mat = img!!.clone()
        Imgproc.Canny(img, img_result, 80.0, 90.0)

        val img_bitmap: Bitmap = Bitmap.createBitmap(img_result.cols(), img_result.rows(),
            Bitmap.Config.ARGB_8888)
        Utils.matToBitmap(img_result, img_bitmap)
        val image_view = findViewById<ImageView>(R.id.img)
        image_view.setImageBitmap(img_bitmap)

    }
}