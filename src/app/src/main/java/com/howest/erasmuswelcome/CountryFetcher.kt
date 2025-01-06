package com.howest.erasmuswelcome

import okhttp3.*
import org.json.JSONArray
import java.io.IOException
import java.util.logging.Level
import java.util.logging.Logger

class CountryFetcher {
    companion object {
        private val logger: Logger = Logger.getLogger(CountryFetcher::class.java.name)

        fun fetchCountries(callback: (List<String>?) -> Unit) {
            val client = OkHttpClient()

            val request = Request.Builder()
                .url("https://restcountries.com/v3.1/all?fields=name")
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        val responseBody = response.body?.string()
                        println(responseBody)
                        if (responseBody != null) {
                            try {
                                // Parse JSON response
                                val jsonArray = JSONArray(responseBody)
                                val countryList = mutableListOf<String>()

                                println(jsonArray)

                                for (i in 0 until jsonArray.length()) {
                                    val countryObject = jsonArray.getJSONObject(i)
                                    val countryName = countryObject.getJSONObject("name").getString("common")
                                    countryList.add(countryName)
                                }

                                // Return sorted list of countries
                                callback(countryList.sorted())
                            } catch (e: Exception) {
                                logger.log(Level.SEVERE, "Parsing error", e)
                                callback(null)
                            }
                        } else {
                            callback(null)
                        }
                    } else {
                        callback(null)
                    }
                }

                override fun onFailure(call: Call, e: IOException) {
                    logger.log(Level.SEVERE, "Request failed", e)
                    callback(null)
                }
            })
        }
    }
}
