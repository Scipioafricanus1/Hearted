package com.example.android.hearted

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.example.android.hearted.Adapters.FavoritesAdapter
import com.example.android.hearted.Adapters.RecyclerViewAdapter
import com.example.android.hearted.DataModel.Item
import com.example.android.hearted.DataModel.ItemModel
import kotlinx.android.synthetic.main.activity_heart_views.*
import java.util.*
import kotlin.collections.ArrayList

class HeartViews : AppCompatActivity(), Observer {


    private var mItemListAdapter: FavoritesAdapter? = null
    private var mProfileAdapter: RecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heart_views)
        ItemModel

        ItemModel.addObserver(this)


        //set up layout manager for rec_views and put lists in them.
        rv_profile.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)

        //create and populate the list.
        val profiles = ArrayList<Profile>()
        val list = ArrayList<Item>()
        addProfiles(profiles)



        //pass the lists to the adapters.
        mProfileAdapter = RecyclerViewAdapter(profiles)
        mItemListAdapter = FavoritesAdapter(this, R.layout.list_item, list)

        //assign adapter to recycler view.
        rv_profile.adapter = mProfileAdapter
        rec_view_FavoritesList.adapter = mItemListAdapter

        //TODO("Add picture support.")
    }


    private fun addProfiles(profiles: ArrayList<Profile>) {
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

    }

    override fun update(p0: Observable?, p1: Any?) {
        mItemListAdapter?.clear()

        val data = ItemModel.getData()
        if(data != null) {
            mItemListAdapter?.clear()
            mItemListAdapter?.addAll(data)
            mItemListAdapter?.notifyDataSetChanged()

        }
    }

    override fun onResume() {
        super.onResume()
        ItemModel.addObserver(this)
    }

    override fun onPause() {
        super.onPause()
        ItemModel.deleteObserver(this)
    }

    override fun onStop() {
        super.onStop()
        ItemModel.deleteObserver(this)
    }

}
