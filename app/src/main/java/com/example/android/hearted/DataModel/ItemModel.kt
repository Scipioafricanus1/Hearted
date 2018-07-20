package com.example.android.hearted.DataModel

import android.util.Log
import com.google.firebase.database.*
import java.util.*
import kotlin.collections.ArrayList
//any UI page notices the changes here.
object ItemModel: Observable() {

    private var mValueDataListener: ValueEventListener? = null
    private var mItemList: ArrayList<Item>? = ArrayList()

    private fun getDatabaseRef(): DatabaseReference? {
        return FirebaseDatabase.getInstance().reference.child("Allergies")
    }

    init {
        if(mValueDataListener != null) {
            getDatabaseRef()?.removeEventListener(mValueDataListener)
        }
        mValueDataListener = null
        Log.i("ItemModel", "Data init line 24")
        mValueDataListener = object: ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot?) {
                try {
                    Log.i("ItemModel", "data updated line 26")
                    val data: ArrayList<Item> = ArrayList()
                    if(dataSnapshot != null) {
                        for (snapshot: DataSnapshot in dataSnapshot.children) {
                            try {
                                data.add(Item(snapshot))
                            } catch(e: Exception) {
                                e.printStackTrace()
                            }
                        }
                        mItemList = data
                        Log.i("ItemModel", "data updated, there are ${mItemList!!.size} entrees in the cache.")
                        setChanged()
                        notifyObservers()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun onCancelled(p0: DatabaseError?) {
                if(p0 != null) {
                    Log.i( "itemModel", "line 48 data update cancelled, err = ${p0.message}")
                }
            }
        }
        getDatabaseRef()?.addValueEventListener(mValueDataListener)
    }

    fun getData(): ArrayList<Item>? {
        return mItemList
    }
}