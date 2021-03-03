package com.example.week10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.week10.databinding.ActivityEditactivityBinding
import com.example.week10.model.user
import com.example.week10.model.userAdapter
import io.realm.Realm
import io.realm.exceptions.RealmException
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_editactivity.*

class editactivity : AppCompatActivity() {

    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editactivity)
        initView()
        saveData()
        cancelButton()
    }
    fun initView(){
        realm = Realm.getDefaultInstance()
    }
    fun saveData() {
        btnSave.setOnClickListener {
            realm.beginTransaction()
                try {
                    val currentIdNumber: Number? = realm.where(user::class.java).max("id")
                    val nextId: Int
                    nextId = if (currentIdNumber == null){
                        1
                    }else{
                        currentIdNumber.toInt()+1
                    }
                    val user = realm.createObject(user::class.java)
                    user.setEmail(etEmail.text.toString())
                    user.setNama(etNama.text.toString())
                    user.setId(nextId)
                    realm.commitTransaction()
                    Toast.makeText(this, "Data Added", Toast.LENGTH_SHORT).show()
                } catch (e: RealmException) {
                    Toast.makeText(this, "Failed $e", Toast.LENGTH_SHORT).show()
                }
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            }
        }
    fun cancelButton(){
        btnCancel.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}