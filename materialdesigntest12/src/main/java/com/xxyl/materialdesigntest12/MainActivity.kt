package com.xxyl.materialdesigntest12

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.xxyl.materialdesigntest12.cardview.CardViewActivity
import com.xxyl.materialdesigntest12.drawlayout.DrawLayoutActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val TAG = this.javaClass.simpleName
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        btnDrawLayout.setOnClickListener {
            start<DrawLayoutActivity>(this){}
        }
        btnSnackBar.setOnClickListener {
            Snackbar.make(it,"data deleted", Snackbar.LENGTH_LONG)
                .setAction("Undo"){
                    Toast.makeText(this, "data restored", Toast.LENGTH_SHORT).show()
                }.show()
        }
        btnCardView.setOnClickListener {
            start<CardViewActivity>(this){}
        }

    }

    /**
     * 创建菜单步骤：
     * 1.在res中创建menu文件夹 ->menu.xml->添加item【id，icon，title，showAsAction】
     * @param menu Menu
     * @return Boolean
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.backup -> Toast.makeText(this, "backup", Toast.LENGTH_SHORT).show()
            R.id.delete -> Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show()
            R.id.setting -> Toast.makeText(this, "settings", Toast.LENGTH_SHORT).show()
            else -> {
            }
        }
        return true
    }
}

inline fun <reified T> start(context: Context, block: Intent.()->Unit){
    val intent = Intent(context, T::class.java)
    intent.block()
    context.startActivity(intent)
}
