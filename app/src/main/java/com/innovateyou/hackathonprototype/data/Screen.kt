package com.innovateyou.hackathonprototype.data

sealed class Screen (val route: String){
    object LoginPage: Screen("login_route")
    object HomeScreen: Screen("home_route")
    object SeeAllRequests: Screen("see_all_requests_route")
    object EmergencyRequest: Screen("emergency_request_route")
    object RequestMedicineRequests: Screen("request_medicine_request_route")
}