package com.example.kotlinstudy.network

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.kotlinstudy.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_ok_http.*
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.xml.sax.InputSource
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.StringReader
import java.lang.Exception
import javax.xml.parsers.SAXParserFactory
import kotlin.concurrent.thread

class OkHttpActivity : AppCompatActivity() {
    private val TAG = "OkHttpActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ok_http)

        btn_send_http_request.setOnClickListener {
            sendRequestWithOkHttp()
        }

        btn_get_app_data.setOnClickListener {
            getAppData();
        }
    }

    private fun getAppData() {
        val appService = ServiceCreator.create(AppService::class.java)
        appService.getAppData().enqueue(object: Callback<List<App>>{
            override fun onResponse(call: Call<List<App>>, response: Response<List<App>>) {
                val list = response.body()
                if (list != null) {
                    for (app in list) {
                        Log.d(TAG, "id is ${app.id}")
                        Log.d(TAG, "name is ${app.name}")
                        Log.d(TAG, "version is ${app.version}")
                    }
                }
            }

            override fun onFailure(call: Call<List<App>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    private fun sendRequestWithOkHttp() {
        thread {
            try {
                val client = OkHttpClient()
                val request = Request.Builder()
                    .url("http://192.168.43.59:8000/get_data.xml")
                    .build()

                val response = client.newCall(request).execute()

                val responseData = response.body?.string()
                if (responseData != null) {
//                    showResponse(responseData)
//                    parseXMLWithPull(responseData)
                    parseXMLWithSAX(responseData)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun parseXMLWithSAX(responseData: String) {
        try {
            val factory = SAXParserFactory.newInstance()
            val xmlReader = factory.newSAXParser().xmlReader
            val handler = ContentHandler()
            xmlReader.contentHandler = handler
            xmlReader.parse(InputSource(StringReader(responseData)))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun parseXMLWithPull(xmlData: String) {
        try {
            val factory = XmlPullParserFactory.newInstance()
            val xmlPullParser = factory.newPullParser()
            xmlPullParser.setInput(StringReader(xmlData.replace("\r\n", "")))
            var eventType = xmlPullParser.eventType
            var id = ""
            var name = ""
            var version = ""
            while (eventType != XmlPullParser.END_DOCUMENT) {
                val nodeName = xmlPullParser.name
                when(eventType) {
                    XmlPullParser.START_TAG -> {
                        when (nodeName) {
                            "id" -> id = xmlPullParser.nextText()
                            "name" -> name = xmlPullParser.nextText()
                            "version" -> version = xmlPullParser.nextText()
                        }
                    }
                    XmlPullParser.END_TAG -> {
                        if ("app" == nodeName) {
                            Log.d("OkHttpActivity", "id is $id")
                            Log.d("OkHttpActivity", "name is $name")
                            Log.d("OkHttpActivity", "version is $version")
                        }
                    }
                }
                eventType = xmlPullParser.next()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun parseJSONWithJSONObject(jsonData: String) {
        try {
            val jsonArray = JSONArray(jsonData)
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val id = jsonObject.getString("id")
                val name = jsonObject.getString("name")
                val version = jsonObject.getString("version")
                Log.d(TAG, "id is ${id.toString()}")
                Log.d(TAG, "name is ${name.toString()}")
                Log.d(TAG, "version is ${version.toString()}")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun parseJSONWithGSON(jsonData: String) {
        val gson = Gson()
        val typeOf = object:TypeToken<List<App>>() {}.type
        val appList = gson.fromJson<List<App>>(jsonData, typeOf)
        for (app in appList) {
            Log.d(TAG, "id is ${app.id}")
            Log.d(TAG, "name is ${app.name}")
            Log.d(TAG, "version is ${app.version}")
        }
    }

    private fun showResponse(responseData: String) {
        runOnUiThread{
            tv_http_response.text = responseData
        }
    }
}