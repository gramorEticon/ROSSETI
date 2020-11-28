package com.eticon.rosseti

import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.eticon.rosseti.ForumFragments.ForumFragment
import com.eticon.rosseti.NotificationFragments.NotificationFragment
import com.eticon.rosseti.OrderFragments.OrderFragment
import com.eticon.rosseti.ProfileFragments.ProfileFragment
import com.eticon.rosseti.ReestrsFragments.ReestrFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class NavigatorActivity : AppCompatActivity() {
    lateinit var fl_content: FrameLayout
    lateinit var bottomNavigator: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigator)
        init()
        var spManager = supportFragmentManager
        val content = spManager.findFragmentById(R.id.fl_content)
        if (content == null) {
            spManager.beginTransaction().replace(R.id.fl_content, ForumFragment()).commit()
        }

        bottomNavigator.setOnNavigationItemSelectedListener OnNavigationItemSelectedListener@{ MenuItem ->
            when (MenuItem.itemId) {
                R.id.forum -> {
                    spManager.beginTransaction().replace(R.id.fl_content, ForumFragment()).commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.reestr -> {
                    spManager.beginTransaction().replace(R.id.fl_content, ReestrFragment()).commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.my_order -> {
                    spManager.beginTransaction().replace(R.id.fl_content, OrderFragment()).commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.notification-> {
                    spManager.beginTransaction().replace(R.id.fl_content, NotificationFragment()).commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.profile -> {
                    spManager.beginTransaction().replace(R.id.fl_content, ProfileFragment()).commit()
                    return@OnNavigationItemSelectedListener true
                }
                else -> return@OnNavigationItemSelectedListener false



            }


        }


    }

    fun init() {
        fl_content = findViewById(R.id.fl_content)
        bottomNavigator = findViewById(R.id.navigation)
    }
}