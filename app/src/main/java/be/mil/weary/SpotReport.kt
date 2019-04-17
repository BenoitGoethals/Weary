package be.mil.weary

import java.util.*

data class SpotReport(val id:String, val callSign:String, val BmsAssetId:String, val DateReported: Date, val locationBft:LocationGps,val Icident:String, val photo:ByteArray) {

    override fun toString(): String {
        return "SpotReport(id='$id', callSign='$callSign', BmsAssetId='$BmsAssetId', DateReported=$DateReported,  Icident='$Icident', photo=$photo.size))})"
    }
}