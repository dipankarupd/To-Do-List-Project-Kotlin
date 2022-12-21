package com.example.todolist

import android.content.Context
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class FileHelper {

    val FILENAME = "listinfo.dat"   // file is made

    // to write the data:

    fun writeData(item: ArrayList<String> , context: Context) {

        // save the data in the file:

        /*

        A file output stream is an output stream for writing data to a File

         */

        var fos : FileOutputStream = context.openFileOutput(FILENAME , Context.MODE_PRIVATE)

        // this method will create in the file memory and open it

        var oos = ObjectOutputStream(fos);
        oos.writeObject(item);

        /*

        An ObjectOutputStream writes primitive data types and graphs of Java objects to an OutputStream.
         */

        oos.close()

        /*
        things I did in this method:
        1. created a file
        2. opened the file and wrote the data in the file
        3. closed the file
         */
    }

    fun readData(context: Context) : ArrayList<String> {

        // when the application is first started then no any file is created.
        // so error will occur which will be handled by try catch block:

        // we try to add the arraylist in the file at try block and use a catch block to return
        // empty arraylist



        var itemList : ArrayList<String>

        try {
            var fis : FileInputStream = context.openFileInput(FILENAME)
            var ois = ObjectInputStream(fis)
            itemList = ois.readObject() as ArrayList<String>
        }
        catch (e : FileNotFoundException) {
            itemList = ArrayList()
        }


        return itemList
    }
}