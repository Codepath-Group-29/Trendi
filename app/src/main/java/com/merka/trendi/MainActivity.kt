package com.merka.trendi

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {

    private lateinit var movieList: ArrayList<MovieClass>
    private lateinit var movierecyclerView: RecyclerView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        movierecyclerView = findViewById(R.id.recyclerView);
        movieList = ArrayList()
        getData()






    }

    private fun getData()  {
        val client = AsyncHttpClient()

        val params = RequestParams()
        params["limit"] = "10"
        params["page"] = "1"

        client["https://api.themoviedb.org/3/movie/top_rated?api_key=53159f7282c604ff13adfc4f01ce995c", params, object :
            JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                Log.d("akrem", "failed")
                val objs = json.jsonObject.getJSONArray("results")


                for(i in 0 until objs.length()){
                    val  jsnObj = objs.getJSONObject(i);

                    val model = MovieClass();

                    model.setId(jsnObj.getString("vote_average"))
                    model.setName(jsnObj.getString("title"))
                    val posterPath = jsnObj.getString("poster_path")
                    model.setImage("https://image.tmdb.org/t/p/w500/$posterPath")

                    movieList.add(model)


                }

                //Log.d("akrem", objs.getJSONObject(0).getString("title"))
                val adapetry = AdapterMovie(this@MainActivity, movieList)

                movierecyclerView.adapter = adapetry
                movierecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)




            }

            override fun onFailure(

                statusCode: Int,
                headers: Headers?,
                response: String,
                throwable: Throwable?
            ) {
            }
        }]

    }
}



private operator fun Any.get(s: String) {

}