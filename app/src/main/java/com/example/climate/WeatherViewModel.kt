package com.example.climate
import android.net.Network
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.climate.api.Constant.apikey
import com.example.climate.api.NetworkResponse
import com.example.climate.api.RetrofitInstance
import com.example.climate.api.WeatherModel
import kotlinx.coroutines.launch

class WeatherViewModel:ViewModel() {
    private val weatherApi = RetrofitInstance.weatherApi
    private val _weatherResult = MutableLiveData<NetworkResponse<WeatherModel>>()
    val weatherResult : LiveData<NetworkResponse<WeatherModel>> = _weatherResult
    fun getData(city : String) {
        _weatherResult.value = NetworkResponse.Loading
        viewModelScope.launch {
            try{
                val response = weatherApi.getWeather(apikey,city)
                if(response.isSuccessful){
                    response.body()?.let{
                        _weatherResult.value = NetworkResponse.Success(it)
                    }
                }else{
                    _weatherResult.value = NetworkResponse.Error("Failed to retrieve the data")
                }
            }
            catch(e:Exception){
                _weatherResult.value = NetworkResponse.Error("Failed to retrieve the data")
            }

        }
    }
}