package be.mil.weary

import java.util.*

data class LocationGps(val Id:String,internal val longitude:Double, internal val latitude:Double, val altitude:Double, val date:Date, val speed:Float, val accuracy: Float) {


    companion object {
        const val EXCHANGE_NAME = "bft-exchange"
    }




}