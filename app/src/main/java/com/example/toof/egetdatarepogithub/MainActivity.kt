package com.example.toof.egetdatarepogithub

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
    private lateinit var context: Context
    private val mList = ArrayList<User>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this
        LoadData().execute("https://api.github.com/users/google/repos")
    }

    inner class LoadData : AsyncTask<String, Void, String>() {
        override fun doInBackground(vararg params: String?): String {
            val result = ParseDataWithJson().getJsonFromUrl(params[0]!!)
            Log.e("AAA", result)
            return result
        }

        override fun onPostExecute(result: String?) {
            recycler_view.layoutManager = LinearLayoutManager(context)
            val jsonArray = JSONArray(result)
            mList.addAll(ParseDataWithJson().parseJsonToData(jsonArray))
            recycler_view.adapter = UserAdapter(context, mList)
        }

    }

}
