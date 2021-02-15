package com.github.fabegalo.appcovidkotlin

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import okhttp3.*
import java.io.IOException

class SimpleRunnable(): Runnable {
    private val client = OkHttpClient()
    var list: List<Note> = listOf()

    public fun getDados(): List<Note> {
        return list
    }

    public override fun run() {
        getListNote()
        while (list.size < 5){
            println("${Thread.currentThread()} has run.")
        }
        println("CHEGOU NO FINAL !!!!!!!!!!!_----------------------")


        //val recyclerView: RecyclerView = findViewById<RecyclerView>(R.id.lista)

        //recyclerView.layoutManager = LinearLayoutManager(this)

        //recyclerView.adapter = NoteListAdapter(list, this)
    }

    fun getListNote() {
        val request = Request.Builder()
                .url("https://fabegalo.000webhostapp.com/Projects/API%20-%20COVID/")
                .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) {
                val json = response.body()?.string()
                //println(json)

                val gsonBuilder = GsonBuilder().serializeNulls()
                gsonBuilder.registerTypeAdapter(Note::class.java, NoteDeserializer())
                val gson = gsonBuilder.create()
                list = gson.fromJson(json, Array<Note>::class.java).toList()
                println("TROUXE OS DADOS !!!!!!!!!!!!!")

            }
        })
    }
}