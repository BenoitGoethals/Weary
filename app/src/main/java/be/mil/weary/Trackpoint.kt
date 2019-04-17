package be.mil.weary

import java.util.*

data class Trackpoint(val id:String, val callsign:String, val BmsAssetId:String, val DateReported: Date, val locationGps:LocationGps, val speed:Int ) {

    override fun toString(): String {
        return "Trackpoint(id='$id', callsign='$callsign', BmsAssetId='$BmsAssetId', DateReported='$DateReported', locationGps=$locationGps, speed=$speed)"
    }
}
