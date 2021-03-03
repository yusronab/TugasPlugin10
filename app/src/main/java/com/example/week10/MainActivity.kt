package com.example.week10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.week10.databinding.ActivityMainBinding
import com.example.week10.model.user
import com.example.week10.model.userAdapter
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var recyclerView: RecyclerView
    lateinit var userAdapter: userAdapter
    lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        addData()
    }
    fun initView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        userAdapter = userAdapter(mutableListOf(), object : userAdapter.clickAdapter{
            override fun onClick(user: user) {
                startActivity(Intent(this@MainActivity, UpdateActivity::class.java)
                        .putExtra("nama", user.getNama())
                        .putExtra("email", user.getEmail())
                        .putExtra("id", user.getId())
                )
                finish()
            }
        })
        binding.recyclerView.adapter = userAdapter
        realm = Realm.getDefaultInstance()
        realm.where(user::class.java).findAll().let {
            userAdapter.setUser(it)
        }
    }
    fun addData(){
        btnAdd.setOnClickListener {
            startActivity(Intent(this, editactivity::class.java))
            finish()
        }
    }
}