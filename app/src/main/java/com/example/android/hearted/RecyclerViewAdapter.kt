package com.example.android.hearted

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import de.hdodenhof.circleimageview.CircleImageView

class RecyclerViewAdapter( val profileList: ArrayList<Profile>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> () {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textViewName = itemView.findViewById<TextView>(R.id.tv_profile)
        val circleImageProfile = itemView.findViewById<CircleImageView>(R.id.civ_profilePic)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val profile: Profile = profileList[position]
        holder.textViewName.text = profile.profileName
        holder.circleImageProfile.setImageResource(R.drawable.ic_launcher_background)
        //TODO("add image pulled from firebase database into profile image slot")
    }

    override fun getItemCount(): Int {
        return profileList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedLayout = LayoutInflater.from(parent.context).inflate(R.layout.rec_view_item, parent, false)
        return ViewHolder(inflatedLayout)
    }
}