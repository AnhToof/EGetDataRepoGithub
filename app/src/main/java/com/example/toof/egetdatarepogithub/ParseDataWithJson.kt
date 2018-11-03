package com.example.toof.egetdatarepogithub

import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.Reader
import java.net.HttpURLConnection
import java.net.URL

class ParseDataWithJson {
    private val TIME_OUT = 15000
    private val METHOD_GET = "GET"

    fun getJsonFromUrl(strUrl: String): String {
        val url = URL(strUrl)
        val connection = url.openConnection() as HttpURLConnection
        connection.readTimeout = TIME_OUT
        connection.connectTimeout = TIME_OUT
        connection.requestMethod = METHOD_GET
        connection.doOutput = true
        connection.connect()
        val bufferedReader = BufferedReader(InputStreamReader(url.openStream()) as Reader?)
        val stringBuilder = StringBuilder()
        var line: String?
        do {
            line = bufferedReader.readLine()
            if (line == null) break
            stringBuilder.append(line)
        } while (true)

        bufferedReader.close()
        connection.disconnect()
        return stringBuilder.toString()
    }

    fun parseJsonToData(jsonArray: JSONArray): ArrayList<User> {
        val list = ArrayList<User>()

        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val full_name = jsonObject.getString("full_name")
            val obj = jsonObject.getJSONObject("owner")
            val login = obj.getString("login")
            val avatar_url = obj.getString("avatar_url")
            list.add(User(full_name, Owner(login, avatar_url)))
        }
        return list
    }
}