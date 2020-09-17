package com.example.picassonetworkdemo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Adapter
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.picassonetworkdemo.R
import com.example.picassonetworkdemo.adapters.AdapterPhotos
import com.example.picassonetworkdemo.models.PhotoItem
import com.example.picassonetworkdemo.models.PhotosResponse
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
    var mList: ArrayList<PhotoItem> = ArrayList()
    var adapterPhotos: AdapterPhotos? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init(){
        getData()
        adapterPhotos = AdapterPhotos(this, mList)
        recycler_view.layoutManager = GridLayoutManager(this, 3)
        recycler_view.adapter = adapterPhotos
    }

    private fun getData(){
        val url = "https://jsonplaceholder.typicode.com/photos"
        var requestQueue = Volley.newRequestQueue(this)
        var request = StringRequest(Request.Method.GET, url,
            {
                var jsonArray = JSONArray(it)
                for (i in 0 until jsonArray.length()) {
                    var jsonObj = jsonArray.getJSONObject(i)
                    var albumId = jsonObj.getInt("albumId")
                    var id = jsonObj.getInt("id")
                    var title = jsonObj.getString("title")
                    var url = jsonObj.getString("url")
                    var thumbnailUrl = jsonObj.getString("thumbnailUrl")

                    mList.add(PhotoItem(albumId, id, title, url, thumbnailUrl))
                    Log.d("abc", title)
                }
                adapterPhotos?.setData(mList)
            }, {
                it.message?.let { it1 -> Log.d("abc", it1) }
            })
        requestQueue.add(request)
    }
}