package com.example.week10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.week10.model.user
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_update.*

class UpdateActivity : AppCompatActivity() {

    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        initView()
        getUser()
        updateData()
        deleteData()
        navBack()
    }
    fun initView(){
        realm = Realm.getDefaultInstance()
    }
    fun getUser(){
        etUpdateNama.setText(intent.getStringExtra("nama"))
        etUpdateEmail.setText(intent.getStringExtra("email"))
        etUpdateId.setText(intent.getStringExtra("id"))
    }
    fun updateData(){
        btnUpdate.setOnClickListener {
            realm.beginTransaction()
            realm.where(user::class.java).equalTo("email", etUpdateEmail.text.toString()).findFirst().let {
                it!!.setNama(etUpdateNama.text.toString())
            }
            realm.commitTransaction()
            Toast.makeText(this, "Data Updated", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
    fun deleteData(){
        btnDelete.setOnClickListener {
            realm.beginTransaction()
            realm.where(user::class.java).equalTo("email", etUpdateEmail.text.toString()).findFirst().let {
                it!!.deleteFromRealm()
            }
            realm.commitTransaction()
            Toast.makeText(this, "Data Deleted", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
    fun navBack(){
        btnBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}