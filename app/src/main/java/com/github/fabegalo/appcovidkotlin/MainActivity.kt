package com.github.fabegalo.appcovidkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import kotlinx.coroutines.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //var result = GlobalScope.async { run() }

        val teste = SimpleRunnable()

        val threadWithRunnable = Thread(teste)
        threadWithRunnable.start()
        threadWithRunnable.join()
        val teste2 = teste.getDados()

        println("--------Dados de teste2 é igual a : $teste2")

        val recyclerView: RecyclerView = findViewById<RecyclerView>(R.id.lista)

        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = NoteListAdapter(teste2, this)




//        val textView: TextView = findViewById(R.id.textView3) as TextView
//        textView.setOnClickListener {
//            textView.text = "Pau no cu da banana!"
//        }
//
//        textView.text = "Banana"
//
//        val str: String = textView.text.toString()
//
//        println("the value is $str")


    }

//    private fun notes(): List<Note> {
//        return listOf(
//                Note("Estados Unidos",
//                        "Livro de Kotlin com Android"),
//                Note("Pesquisa",
//                        "Como posso melhorar o código dos meus projetos"),
//                Note("Estudo",
//                        "Como sincronizar meu App com um Web Service"))
//    }
}