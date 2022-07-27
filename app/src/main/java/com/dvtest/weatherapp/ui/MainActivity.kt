package com.dvtest.weatherapp.ui

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.dvtest.weatherapp.R
import com.dvtest.weatherapp.core.BaseActivity
import com.dvtest.weatherapp.model.dbmodels.CurrentWeatherEntity
import com.dvtest.weatherapp.model.dbmodels.ForecastEntity
import com.dvtest.weatherapp.ui.model.WeatherModel
import com.dvtest.weatherapp.ui.presenter.WeatherPresenter
import com.dvtest.weatherapp.ui.view.WeatherContract
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : BaseActivity(), WeatherContract.view, LocationListener {
    var presenter = WeatherPresenter(this, WeatherModel())
    var image = R.drawable.forest_cloudy
    var mainBgColor = R.color.cloudy
    var lastLocation = Location("")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var linearLayoutManager = LinearLayoutManager(this)
        rvForecast.layoutManager = linearLayoutManager
        getLocation()
    }

    fun getLocation() {
        var locationProvide = LocationServices.getFusedLocationProviderClient(this)
        var locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            //n
            ActivityCompat.requestPermissions(
                this,
                listOf<String>(
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ).toTypedArray(),
                1
            )
            return
        } else {

            var a = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER)
            a
        }
        locationProvide.lastLocation.addOnSuccessListener {
        }
        if (lastLocation != null) {

            if (!lastLocation.latitude.isNaN() || !lastLocation.longitude.isNaN()) {
                CoroutineScope(Dispatchers.IO).launch {
                    presenter.requestWeather(
                        lastLocation.latitude.toString(),
                        lastLocation.longitude.toString()
                    )
                }
            }
            else {
                locationManager.requestLocationUpdates(
                    LocationManager.PASSIVE_PROVIDER,
                    400,
                    1f,
                    this
                )
            }
        }
    }


    override fun onWeatherLoaded(currentWeatherEntity: CurrentWeatherEntity) {
        if (currentWeatherEntity != null) {

            image = when (currentWeatherEntity.weather.main.trim().toLowerCase()) {
                "rain" -> {
                    R.drawable.forest_rainy
                }
                "clouds" -> {
                    R.drawable.forest_cloudy
                }
                else -> {
                    R.drawable.forest_sunny
                }
            }
            mainBgColor = when (currentWeatherEntity.weather.main.trim().toLowerCase()) {
                "rain" -> {
                    R.color.rainy
                }
                "clouds" -> {
                    R.color.cloudy
                }
                else -> {
                    R.color.sunny
                }
            }
            runOnUiThread {
                ivMainImage.setImageDrawable(baseContext.resources.getDrawable(image))
                llMain.setBackgroundColor(baseContext.getColor(mainBgColor))
                tvCurrentTempHeader.text = currentWeatherEntity.main.temp.toString() + " \u00B0"
                tvCurrentDescription.text = currentWeatherEntity.weather.description
                tvCurrentTemp.text = currentWeatherEntity.main.temp.toString() + " \u00B0 \ncurrent"
                tvMaxTemp.text = currentWeatherEntity.main.tempMax.toString() + " \u00B0 \nmax"
                tvMinTemp.text = currentWeatherEntity.main.tempMin.toString() + " \u00B0 \nmin"
            }
            presenter.requestForecast(
                lastLocation.latitude.toString(),
                lastLocation.longitude.toString()
            )
        }
    }

    override fun onForecastLoaded(forecastEntity: List<ForecastEntity>) {
        runOnUiThread {
            rvForecast.adapter =
                ForecastAdapter(forecastEntity, this@MainActivity)
        }
    }

    override fun onLocationChanged(location: Location) {

        var lastLocation = location
        var locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        locationManager!!.removeUpdates(this)
    }

    override fun onProviderDisabled(provider: String) {
        super.onProviderDisabled(provider)
        onError("Please Enable location on device")

    }

    override fun onProviderEnabled(provider: String) {
        super.onProviderEnabled(provider)

    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        super.onStatusChanged(provider, status, extras)
    }
}