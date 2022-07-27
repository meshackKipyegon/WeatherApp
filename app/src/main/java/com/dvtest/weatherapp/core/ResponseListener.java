package com.dvtest.weatherapp.core;

public interface ResponseListener {
    void onSuccess(Object jsonObject);

    void onError(String jsonObject);
}
