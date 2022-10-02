package com.example.todolist

import android.content.Context
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.lang.Exception

class File {
    val FILE = "data.dat"
    fun writeData(context: Context,data : ArrayList<String>){
        val fos : FileOutputStream = context.openFileOutput(FILE,Context.MODE_PRIVATE)
        val oos : ObjectOutputStream = ObjectOutputStream(fos)
        oos.writeObject(data)
        oos.close()
    }
    fun readData(context: Context): ArrayList<String> {
        try {
            val fis: FileInputStream = context.openFileInput(FILE)
            val ois: ObjectInputStream = ObjectInputStream(fis)
            return ois.readObject() as ArrayList<String>
        }catch (e : Exception){
            return ArrayList<String>()
        }
    }
}