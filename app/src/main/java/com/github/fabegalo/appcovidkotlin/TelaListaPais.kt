package com.github.fabegalo.appcovidkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken

class TelaListaPais : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela_info_pais)
        // Get the Intent that started this activity and extract the string
        val note = intent.getStringExtra("data")
        val title = intent.getStringExtra("title")

        this.setTitle(title)

        val gsonBuilder = GsonBuilder().serializeNulls()
        val gson = gsonBuilder.create()
        val list = gson.fromJson(note, Array<Note>::class.java).toList()

        println("CONTEUDO É $list")

        val recyclerView: RecyclerView = findViewById<RecyclerView>(R.id.lista)

        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = NoteListAdapter(list, this)
    }
}