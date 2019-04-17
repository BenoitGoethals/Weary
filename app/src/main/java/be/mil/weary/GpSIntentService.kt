package be.mil.weary

import android.Manifest
import android.annotation.SuppressLint
import android.app.IntentService
import android.content.Intent
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import java.lang.Exception
import java.util.*

// TODO: Rename actions, choose action names that describe tasks that this
// IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
private const val ACTION_FOO = "be.mil.weary.action.FOO"
private const val ACTION_BAZ = "be.mil.weary.action.BAZ"

// TODO: Rename parameters
private const val EXTRA_PARAM1 = "be.mil.weary.extra.PARAM1"
private const val EXTRA_PARAM2 = "be.mil.weary.extra.PARAM2"

/**
 * An [IntentService] subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
class GpsIntentService : IntentService("GpsIntentService") {
    private val TAG = "GpSIntentService"
    var locationPos: LocationGps? = null

    private lateinit var nancy:NancyBftService
    private var permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
    private lateinit var locationManager: LocationManager
    override fun onHandleIntent(intent: Intent?) {
        nancy= NancyBftService()
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        when (intent?.action) {
            ACTION_FOO -> {
                val param1 = intent.getStringExtra(EXTRA_PARAM1)
                val param2 = intent.getStringExtra(EXTRA_PARAM2)
                handleActionFoo(param1, param2)
            }
            ACTION_BAZ -> {
                val param1 = intent.getStringExtra(EXTRA_PARAM1)
                val param2 = intent.getStringExtra(EXTRA_PARAM2)
                handleActionBaz(param1, param2)
            }

        }

        do {
            Thread.sleep(5000)


            try {
                val trackpoint:Trackpoint = Trackpoint("1","1,1","1",Date(),getLocation()!!,0 )
                nancy.postTracker(trackpoint)
                Log.i(TAG,"stared "+trackpoint)
            }
            catch (e:Exception){
                Log.e(TAG,e.localizedMessage)
            }
           ;


        }while (true)


    }





    @SuppressLint("MissingPermission")
    private fun getPosition(provider:String): LocationGps? {

        val localGpsLocation = locationManager.getLastKnownLocation(provider)
        if (localGpsLocation != null)
            return LocationGps("1",localGpsLocation?.longitude,localGpsLocation?.latitude,localGpsLocation?.altitude,
                Date(),localGpsLocation?.speed,localGpsLocation.accuracy)
        else
            return locationPos
    }


    @SuppressLint("MissingPermission")
    private fun getLocation(): LocationGps? {
        var locationGps: LocationGps? = null
        var locationNetwork: LocationGps? = null
        var location: LocationGps?=null
        var   hasGps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        var  hasNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

        if (hasGps) {
            locationGps=getPosition(LocationManager.GPS_PROVIDER)
            location=locationGps
        }
        if (hasNetwork) {
            locationNetwork=getPosition(LocationManager.NETWORK_PROVIDER)
            location=locationNetwork
        }

        if(locationGps!= null && locationNetwork!= null){
            if(locationGps!!.accuracy > locationNetwork!!.accuracy){

                location=locationNetwork
            }else{

                location=locationGps
            }

        }

        return location

    }

    private fun getDeviceName(): String {
        return Settings.Secure.getString(contentResolver,
            Settings.Secure.ANDROID_ID)

    }


    private fun capitalize(s: String?): String {
        if (s == null || s.length == 0) {
            return ""
        }
        val first = s[0]
        return if (Character.isUpperCase(first)) {
            s
        } else {
            Character.toUpperCase(first) + s.substring(1)
        }
    }

    override fun onDestroy() {
        super.onDestroy()


    }



    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private fun handleActionFoo(param1: String, param2: String) {
        TODO("Handle action Foo")
        Log.i(TAG,"startrd ")
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private fun handleActionBaz(param1: String, param2: String) {
        TODO("Handle action Baz")
        Log.i(TAG,"action baz ")
    }

    companion object {
        /**
         * Starts this service to perform action Foo with the given parameters. If
         * the service is already performing a task this action will be queued.
         *
         * @see IntentService
         */
        // TODO: Customize helper method
        @JvmStatic
        fun startActionFoo(context: Context, param1: String, param2: String) {
            val intent = Intent(context, GpsIntentService::class.java).apply {
                action = ACTION_FOO
                putExtra(EXTRA_PARAM1, param1)
                putExtra(EXTRA_PARAM2, param2)
            }
            context.startService(intent)
        }

        /**
         * Starts this service to perform action Baz with the given parameters. If
         * the service is already performing a task this action will be queued.
         *
         * @see IntentService
         */
        // TODO: Customize helper method
        @JvmStatic
        fun startActionBaz(context: Context, param1: String, param2: String) {
            val intent = Intent(context, GpsIntentService::class.java).apply {
                action = ACTION_BAZ
                putExtra(EXTRA_PARAM1, param1)
                putExtra(EXTRA_PARAM2, param2)
            }
            context.startService(intent)
        }
    }
}
