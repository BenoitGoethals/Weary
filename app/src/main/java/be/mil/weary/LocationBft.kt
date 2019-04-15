package be.mil.weary

data class LocationBft(internal val longitude:String, internal val latitude:String, val altitude:String, val date:String, val id:String, val BmsAssetId:String, val accuracy: Float) {


    companion object {
        const val EXCHANGE_NAME = "bft-exchange"
    }

    override fun toString(): String {
        return "longitude='${longitude.substring(0,5)}',   latitude='${latitude.substring(0,5)}',   alt='$altitude',   date='$date')"
    }


}