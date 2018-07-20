package com.example.android.hearted.Adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.android.hearted.DataModel.Item
import com.example.android.hearted.R


class FavoritesAdapter(context: Context, resource: Int, list: ArrayList<Item>): ArrayAdapter<Item>(context, resource, list) {

    private var mResource: Int = 0
    private var mList: ArrayList<Item>
    private var mLayoutInflater: LayoutInflater
    private var mContext: Context = context

    init {
        this.mResource = resource
        this.mList = list
        this.mLayoutInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        val returnView: View?
        if(convertView == null) {
            returnView = try {
                mLayoutInflater.inflate(mResource, null)
            } catch(e: Exception) {
                e.printStackTrace()
                View(context)
            }
            setUI(returnView, position)
            return returnView
        }
        setUI(convertView, position)
        return convertView
    }
    private fun setUI(view: View, position: Int) {
        val item: Item? = if(count > position) getItem(position) else null
        val itemCategory: TextView? = view.findViewById(R.id.tv_category)
        itemCategory?.text = item?.category ?: ""
        val itemName: TextView? = view.findViewById(R.id.tv_item)
        itemName?.text = item?.item ?: ""
        val itemLike: TextView? = view.findViewById(R.id.tv_like)
        itemLike?.text = item?.like ?: ""

    }
}