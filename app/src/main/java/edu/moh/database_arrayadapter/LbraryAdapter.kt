package edu.moh.database_arrayadapter

import MydataBase
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.item.view.*
class LibraryAdapter(context: Context,private val objects: MutableList<BookModel>)
    : ArrayAdapter<BookModel>(context,R.layout.item,objects) {
lateinit var mud:MydataBase
lateinit var bookm:BookModel
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view=convertView ?: LayoutInflater.from(context).inflate(R.layout.item, parent, false)
            val d:BookModel=getItem(position)!!

        view.textView.text="Member Name: " + objects[position]
        view.textView2.text="Book Name: " + objects[position]
        view.textView3.text="Date: " + objects[position]
        view.textView4.text="ID: " + objects[position]



        view.imageButton.setOnClickListener {
            mud.deleteProduct(d.id!!)
            objects.removeAt(position)
            notifyDataSetChanged()
        }

        return view
    }
}
