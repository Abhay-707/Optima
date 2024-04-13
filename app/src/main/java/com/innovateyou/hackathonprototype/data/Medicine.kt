package com.innovateyou.hackathonprototype.data

data class Medicine (val id:String = "",
                     val name:String = "",
                     val quantity: Int = 0,
                     val manufacturer: String = "",
                     val minStockLimit: Int= 0,
                     val expiryDate: String = "",
                     val type:String = "",
                     val price:Float = 0.0f)