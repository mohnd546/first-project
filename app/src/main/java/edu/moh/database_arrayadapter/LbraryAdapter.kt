package edu.moh.database_arrayadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.item.view.*

class LbraryAdapter(context: Context, private val objects: Array<Array< String>>) :
    ArrayAdapter<Array<String>>(context, R.layout.item, objects) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val l=LayoutInflater.from(context)
        val view=l.inflate(R.layout.item,parent,false)

        view.textView.text="Member Name: " + objects[position][0]
        view.textView2.text="Book Name: " + objects[position][1]
        view.textView3.text="Date: " + objects[position][2]
        view.textView4.text="ID: " + objects[position][3]

        return  view
    }
}