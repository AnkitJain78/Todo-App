package com.example.todolist

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    lateinit var listView : ListView
    lateinit var button: Button
    lateinit var editText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val itemList : ArrayList<String>
        listView = findViewById(R.id.listView)
        button = findViewById(R.id.button)
        editText = findViewById(R.id.editText)

        itemList = File().readData(this)
        val arrayAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,android.R.id.text1,itemList)
        listView.adapter = arrayAdapter
        button.setOnClickListener {
            val input = editText.text.toString()
            itemList.add(input)
            File().writeData(this,itemList)
            editText.setText("")
            arrayAdapter.notifyDataSetChanged()
        }
        listView.setOnItemClickListener { adapterView, view, position, l ->
            val alert = AlertDialog.Builder(this)
            alert.setTitle("Delete")
            alert.setMessage("Do you want to delete this Task")
            alert.setCancelable(false)
            alert.setPositiveButton("Yes",DialogInterface.OnClickListener { dialogInterface, i ->
                itemList.removeAt(position)
                arrayAdapter.notifyDataSetChanged()
                File().writeData(this,itemList)
            })
            alert.setNegativeButton("No",DialogInterface.OnClickListener { dialogInterface, i ->
                dialogInterface.cancel()
            })
            alert.create().show()
        }
    }
}