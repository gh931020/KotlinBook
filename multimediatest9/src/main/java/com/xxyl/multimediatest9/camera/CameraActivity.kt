package com.xxyl.multimediatest9.camera

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.core.content.FileProvider
import com.xxyl.multimediatest9.R
import kotlinx.android.synthetic.main.activity_camera.*
import java.io.File
import kotlin.system.exitProcess

class CameraActivity : AppCompatActivity() {

    companion object{
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, CameraActivity::class.java)
            context.startActivity(starter)
        }
    }
    private val takePhoto = 1
    private val fromAlbum = 2
    //因为照片是安卓系统通过content provider对外提供的，因此需要照片的uri地址进行获取
    lateinit var imageUri: Uri
    lateinit var outPutImage: File
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        btnCamera.setOnClickListener {
            //创建file对象，用于存储拍照后的图片
            outPutImage = File(externalCacheDir, "output_image.jpg")
            if (outPutImage.exists()) outPutImage.delete()
            outPutImage.createNewFile()
            imageUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                FileProvider.getUriForFile(this,"com.xxyl.multimediatest9.fileprovider",outPutImage)
            }else{
                Uri.fromFile(outPutImage)
            }
            //启动相机程序
            val intent= Intent("android.media.action.IMAGE_CAPTURE")
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
            startActivityForResult(intent, takePhoto)
        }
        btnAlbum.setOnClickListener {
            //打开文件选择器
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            //指定只显示图片
            intent.type = "image/*"
            startActivityForResult(intent,fromAlbum)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            takePhoto ->{
                if (resultCode == Activity.RESULT_OK){
                    //将拍摄的照片显示出来
                    val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(imageUri))
                    ivPhoto.setImageBitmap(rotateIfRequired(bitmap))
                }
            }
            fromAlbum ->{
                if (resultCode == Activity.RESULT_OK && data != null){
                    data.data?.let { uri ->
                        //将选择的图片显示出来,此处可能会因为选择的图片过大造成内存溢出oom，需要根据项目需求对图片进行压缩操作
                        val bitmap = getBitmapFromUri(uri)
                        ivPhoto.setImageBitmap(bitmap)
                    }
                }
            }
        }
    }
    //use会自动关闭调用的资源
    private fun getBitmapFromUri(uri: Uri) = contentResolver.openFileDescriptor(uri, "r")?.use {
        BitmapFactory.decodeFileDescriptor(it.fileDescriptor)
    }

    private fun rotateIfRequired(bitmap: Bitmap): Bitmap {
        val exif = ExifInterface(outPutImage.path)
        //拍照的时候相机会因为手持产生旋转角度，在这里对照片进行旋转
        val orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)
        return when(orientation){
            ExifInterface.ORIENTATION_ROTATE_90 -> rotateBitmap(bitmap, 90)
            ExifInterface.ORIENTATION_ROTATE_180 -> rotateBitmap(bitmap, 180)
            ExifInterface.ORIENTATION_ROTATE_270 -> rotateBitmap(bitmap, 270)
            else -> bitmap
        }
    }

    private fun rotateBitmap(bitmap: Bitmap, degree: Int): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(degree.toFloat())
        val ratatedBitmap = Bitmap.createBitmap(bitmap, 0,0,bitmap.width,bitmap.height, matrix, true)
        bitmap.recycle()//将不再需要的bitmap对象回收
        return ratatedBitmap
    }

}