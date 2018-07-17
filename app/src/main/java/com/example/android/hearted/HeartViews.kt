package com.example.android.hearted

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_heart_views.*

class HeartViews : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heart_views)
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("listExample")
        myRef.setValue("Hey")

        rv_profile.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)

        val profiles = ArrayList<Profile>()

        profiles.add(Profile("Joe Mama", "This is A Picture"))
        profiles.add(Profile("Joe Mama1", "This is A Picture"))
        profiles.add(Profile("Joe Mama2", "This is A Picture"))
        profiles.add(Profile("Joe Mama3", "This is A Picture"))
        profiles.add(Profile("Joe Mama4", "This is A Picture"))
        profiles.add(Profile("Joe Mama5", "This is A Picture"))
        profiles.add(Profile("Joe Mama6", "This is A Picture"))
        profiles.add(Profile("Joe Mama7", "This is A Picture"))
        profiles.add(Profile("Joe Mama8", "This is A Picture"))
        profiles.add(Profile("Joe Mama9", "This is A Picture"))
        val adapter = RecyclerViewAdapter(profiles)
        rv_profile.adapter = adapter

        //TODO("Add picture support.")
    }
}
