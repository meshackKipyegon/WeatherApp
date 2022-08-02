# WeatherApp

[![Build Status](https://github.com/visron/WeatherApp/actions)](https://github.com/siliconapps/WeatherApp)


Sample Android App Written almost purely in kotlin

Used openWeatherApi to get data https://openweathermap.org
 

## How to install
 - Clone the repo
 - build and run the app from android studio.

## Installation
Clone this repository and import into **Android Studio**
```bash
git clone git@github.com:silicoapps/WeatherApp.git
```

## Configuration
### Android sdk versions:
`app/build.gradle` with the following info:
```gradle
 compileSdk 31
 minSdk 21
 targetSdk 31
```


## Features 
 - Fetch Current Weather Data from OpenWeatherMap Api 
 - Fetch Forecast Data from OpenWeatherMap Api

## Pattern
 - MVP 

## CI/CD
### Added github actions
  - push to <code>master</code> will trigger github actions.
  - uses java 11
  - The triggered action will
    1. Run defined test-cases.
    2. Build the app.
## TODO
  - Add Drawer navigation menu.
  - Add location menu that will allow search and save location using [google places api](https://developers.google.com/maps/documentation/places/android-sdk/config)
  - Add Google Maps to show markers of different locations saved by user.
  - Increase test coverage.

## Libraries

- Room Database
- Kotlin serialization
- Ktor as http client
- Gson
- google places library
