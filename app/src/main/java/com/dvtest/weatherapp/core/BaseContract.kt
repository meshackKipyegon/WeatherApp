package com.dvtest.weatherapp.core

interface BaseContract {
    interface view {
        fun onShowLoading()
        fun onHideLoading()
        fun onError(error: String)
    }

    interface presenter {
        fun onDestroy()
    }

    interface model
}