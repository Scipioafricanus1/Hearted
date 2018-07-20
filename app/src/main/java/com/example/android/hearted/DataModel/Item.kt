package com.example.android.hearted.DataModel

import com.google.firebase.database.DataSnapshot

class Item(snapshot: DataSnapshot) {
    lateinit var category: String
    lateinit var item: String
    lateinit var like: String
    init {
        try{
            val data: HashMap<String,Any> = snapshot.value as HashMap<String, Any>
            category = data["category"] as String
            item = data["item"] as String
            like = data["like"] as String
        } catch(e: Exception) {
            e.printStackTrace()
        }
    }
}