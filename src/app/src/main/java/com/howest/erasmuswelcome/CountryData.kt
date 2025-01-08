package com.howest.erasmuswelcome

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.howest.erasmuswelcome.CountryFetcher.Companion.fetchCountries

class CountryData {

    var countryList:List<String> = emptyList()

    @Composable
    fun getCountryList():List<String>{
        if(countryList.isEmpty()){
            loadData()
        }
        return countryList
    }

    @Composable
    fun loadData(){
        var errorMessage:String
        LaunchedEffect(countryList) {
            fetchCountries { fetchedCountries ->
                if (fetchedCountries != null) {
                    countryList = fetchedCountries

                } else {
                    errorMessage = "Failed to load countries"

                }
            }
        }
    }
}