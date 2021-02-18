package com.itri.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: MovieAdapter

    companion object{
        val TAG = MainActivity::class.java.simpleName
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler.setHasFixedSize(true)
        recycler.layoutManager = LinearLayoutManager(this)

        CoroutineScope(Dispatchers.IO).launch {
            val data = URL("https://api.themoviedb.org/3/movie/popular?api_key=4fedb2ef58193ab153d81c2d1da71717&language=zh-TW&page=1")
                    .readText()
            Log.d(TAG, "onCreate: ${data}")
            val result = Gson().fromJson(data, MovieResult::class.java)
            result.results.forEach {
                Log.d(TAG, "result: ${it.title}")
            }
            adapter = MovieAdapter(result.results)
            runOnUiThread {
                recycler.adapter = adapter
                adapter.notifyDataSetChanged()
            }
        }
    }
}
