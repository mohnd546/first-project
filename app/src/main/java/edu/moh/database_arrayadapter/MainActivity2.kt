package edu.moh.database_arrayadapter

import MydataBase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {

    lateinit var myDatabase: MydataBase
    lateinit var lb:LibraryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        printData()
    }
        fun findProduct(view: View){
            val name =editTextTextPersonName4.text.toString()
            myDatabase=MydataBase(this)
            val dbString=myDatabase.find(BookModel(name,null,null,null))
            if (dbString.isNotEmpty()){
                Toast.makeText(this,"القيمة مش موجودة ", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"القيمة مش موجودة ", Toast.LENGTH_SHORT).show()
                listview.adapter=LibraryAdapter(this,dbString)

            }

        }
        fun printData(){
            myDatabase=MydataBase   (this)
            listview.adapter = LibraryAdapter(this, myDatabase.show())

        }
    }
