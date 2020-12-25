package com.xxyl.fragmenttest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.xxyl.fragmenttest.fragment.RightFragment
import com.xxyl.fragmenttest.fragment.RightFragmentTwo
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_left.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnFragLeft.setOnClickListener {
            replaceFragment(RightFragmentTwo())
        }
        replaceFragment(RightFragment())
    }

    /**
     * 1.创建待添加的Fragment实例
     * 2.获取FragmentManager，可直接通过getSupportFragmentManager获得
     * 3.开启一个事务， beginTransaction
     * 4.通过事务，向容器中添加一个或替换fragment，传入容器id和fragment实例
     * 5.提交事务，使用commit（）
     * @param fragment Fragment
     * @return Unit
     */
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.flRight, fragment)
        transaction.addToBackStack(null)//添加返回栈，在按下返回back按钮时，会回退上一步操作
        transaction.commitAllowingStateLoss()
    }
}
