package com.github.fabegalo.appcovidkotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.*
import java.lang.reflect.Type

data class Note(
    var name: String? = null,
    var total: String? = null,
    var totaldead: String? = null,
    var totallive: String? = null,
    var update: String? = null,
    val areas: MutableList<Area>? = ArrayList()
)

//class NoteDeserializer : JsonDeserializer<Note> {
//
//    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Note {
//        json as JsonArray
//
//        println(json)
//        //val addressJson = json.get(2).asString
//        //val address = if (addressJson.isJsonObject) addressJson.asJsonObject.toString() else addressJson.asString
//        return Note("name", "addressJson")
//    }
//}

class NoteDeserializer : JsonDeserializer<Note> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Note? {
        val jsonObj = json as JsonObject

        val covid = Note()
        //val wind = WindObject()

        val JsonPaises = jsonObj

        //println("-------------VALOR:::::::: $JsonPaises")
        var name = JsonPaises.get("name")
        var total = JsonPaises.get("total")
        var totaldead = JsonPaises.get("totaldead")
        var totallive = JsonPaises.get("totallive")
        var update = JsonPaises.get("update")

        covid.name = name.toString()
        covid.total = total.toString()
        covid.totaldead = totaldead.toString()
        covid.totallive = totallive.toString()
        covid.update = update.toString()

        var teste = JsonPaises.get("areas")
        val jsonAreas = teste as JsonArray

        for (i in 0 until jsonAreas.size()){
            var indice = jsonAreas[i] as JsonObject
            val name = indice.get("name").toString()
            val total = indice.get("total").toString()
            val totaldead = indice.get("totaldead").toString()
            val totallive = indice.get("totallive").toString()
            covid.areas?.add(Area(name, total, totaldead, totallive))
        }


        //println("Pais: $name")
        //println("Total: $total")
        //println("Mortos: $totaldead")
        //println("Vivos: $totallive")
        //println("Last Update: $update")
        //val jsonMainObj = jsonObj.getAsJsonObject("main")
        //val jsonWindObj = jsonObj.getAsJsonObject("wind")

        //wheather.name = jsonWeatherArray.asJsonObject.get("name").asString
        //wheather.total = jsonWeatherArray.asJsonObject.get("total").asString

        //println("---name: ${wheather.name}")
        //println("---total: ${wheather.total}")
        //wheather.temp = jsonMainObj.get("temp").asFloat
        //wheather.tempMax = jsonMainObj.get("temp_max").asFloat
        //wheather.tempMin = jsonMainObj.get("temp_min").asFloat
        //wheather.humidity = jsonMainObj.get("humidity").asInt
        //wind.speed = jsonWindObj.get("speed").asFloat
        //wind.deg = jsonWindObj.get("deg").asFloat
        //wheather.wind = wind

        return covid

    }
}

class Area(
    var name: String,
    var total: String,
    var totaldead: String,
    var totallive: String
)

class NoteListAdapter(private val notes: List<Note>, private val context: Context) : RecyclerView.Adapter<NoteListAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = notes[position]
        holder?.let {
            it.bindView(note)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.note_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(note: Note) {
            val title =  itemView.findViewById<TextView>(R.id.note_item_title)
            val mortes = itemView.findViewById<TextView>(R.id.note_item_mortes)
            val infectados = itemView.findViewById<TextView>(R.id.text_view_infectados)
            val update = itemView.findViewById<TextView>(R.id.text_view_update)

            title.text = note.name
            mortes.text = note.totaldead
            infectados.text = note.total
            update.text = note.update
        }

    }
}
