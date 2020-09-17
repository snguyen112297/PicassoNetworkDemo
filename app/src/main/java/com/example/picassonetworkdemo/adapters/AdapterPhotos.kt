package com.example.picassonetworkdemo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.picassonetworkdemo.R
import com.example.picassonetworkdemo.models.PhotoItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.grid_photos_adapter.view.*

class AdapterPhotos(var context: Context, var mList: ArrayList<PhotoItem>): RecyclerView.Adapter<AdapterPhotos.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.grid_photos_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: AdapterPhotos.ViewHolder, position: Int) {
        var photo = mList[position]
        holder.bind(photo)
    }

    fun setData(l: ArrayList<PhotoItem>){
        mList = l
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(photos: PhotoItem){
            Picasso.get().load(photos.url)
                .resize(320, 320)
                .centerCrop().placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(itemView.image_view)
        }
    }
}