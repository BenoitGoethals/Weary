package be.mil.weary

import android.util.Log
import com.google.gson.GsonBuilder
import java.io.IOException
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody

class NancyBftService {

    var url="http://192.168.0.166:1234"

    private val gabon = GsonBuilder().setPrettyPrinting().create()

    private var client = OkHttpClient()

    @Throws(IOException::class)
     fun postTracker(loc: Trackpoint): String {
        val json = gabon.toJson(loc)
        val body = RequestBody.create(JSON, json)
        val request = Request.Builder()
            .url("$url/TrackPoint")
            .put(body)
            .build()
        this.client.newCall(request).execute().use {

                response ->
            return response.body()!!.string()
        }
    }

    fun postSpotReport(spotReport:SpotReport) {
        Log.i("NancyBftService",spotReport.toString())
    }


    companion object {
        val JSON = MediaType.parse("application/json; charset=utf-8")

    }
}