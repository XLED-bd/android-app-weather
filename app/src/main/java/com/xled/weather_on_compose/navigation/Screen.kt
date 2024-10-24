package com.xled.weather_on_compose.navigation

import okhttp3.Route

sealed class Screen(val route: String) {
    object MainPage: Screen("mainPage")

    object SecondPage: Screen("secondPage")
}