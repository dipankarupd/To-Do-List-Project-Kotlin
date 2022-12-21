package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import java.io.File

class MainActivity : AppCompatActivity() {

    lateinit var item: EditText
    lateinit var button: Button
    lateinit var listView: ListView

    var workList = ArrayList<String>()  // stores all the to do activities

    // creating an obj of fileHelper class:
    var fileHelper = FileHelper()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        item = findViewById(R.id.AddToDo)
        button = findViewById(R.id.addButton)
        listView = findViewById(R.id.listView)


        workList = fileHelper.readData(this)

        // creating an array adapter:

        /*

      ArrayAdapter(context: Context, resource: Int,textViewResourceId: Int,objects: Array<T>)

       */
        var arrayAdapter = ArrayAdapter(
                                this,
                                android.R.layout.simple_list_item_1,
                                android.R.id.text1,
                                workList
        )

        listView.adapter = arrayAdapter

        button.setOnClickListener {

            var itemName : String = item.text.toString()
            workList.add(itemName)
            // make the edit text same as before:
            item.setText("")
            // write the data of the arraylist to the file:
            fileHelper.writeData(workList,applicationContext)
            arrayAdapter.notifyDataSetChanged()
        }
    }

}