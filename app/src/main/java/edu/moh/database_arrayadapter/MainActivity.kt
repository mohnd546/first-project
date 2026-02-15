package edu.moh.database_arrayadapter

import MydataBase
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item.view.*

class MainActivity : AppCompatActivity() {

    lateinit var my:MydataBase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        my =MydataBase(this)
        button2.setOnClickListener {
            val intent=Intent(this,MainActivity2::class.java)
            startActivity(intent)
        }
    }
    fun add(view: View){
        var member=editTextTextPersonName.text.toString()
        var nameb=editTextTextPersonName2.text.toString()
        var date=editTextTextPersonName3.text.toString()
        my.addProduct(member,nameb,date)
        if (member.isEmpty() || nameb.isEmpty() || date.isEmpty()) {
            Toast.makeText(this, "من فضلك ادخل جميع القيم", Toast.LENGTH_SHORT).show()
            return
        }else{
            Toast.makeText(this,"تمت الاضافة", Toast.LENGTH_SHORT).show()
        }
        editTextTextPersonName2.text.clear()
        editTextTextPersonName3.text.clear()
        editTextTextPersonName.text.clear()
    }

    fun delete(view: View) {
        var nameproduct = editTextTextPersonName2.text.toString()

        if (nameproduct.isEmpty()) {
            Toast.makeText(this, "الرجاء ادخال اسم المنتج", Toast.LENGTH_SHORT).show()
            return
        }


        my.deleteProduct(nameproduct)

    }
}