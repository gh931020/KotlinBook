package com.zzj.uiwidgettest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.zzj.uiwidgettest.util.toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        main_btn.setOnClickListener {
//            "I'm a Button".toast()
            if (main_et.text.isNullOrEmpty()) "type something first".toast() else main_et.text.toast()
        }
        main_iv.setOnClickListener {
            main_iv.setImageResource(R.drawable.img_2)
        }
        main_btn_alertDialog.setOnClickListener {
            AlertDialog.Builder(this).apply {
                setTitle("this is alertDialog")
                setMessage("Something is important...")
                setCancelable(false)
                setPositiveButton("OK"){dialog, which->

                }
                setNegativeButton("CANCLE"){dialog, which->

                }
                show()
            }
        }
    }
}
