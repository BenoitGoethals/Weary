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
class GpSIntentService : IntentService("GpSIntentService") {
    private val TAG = "GpSIntentService"
    var locationPos: LocationBft? = null
    private var permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
    private lateinit var locationManager: LocationManager
    override fun onHandleIntent(intent: Intent?) {
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
            Log.i(TAG,"stared "+getLocation());
        }while (true)


    }





    @SuppressLint("MissingPermission")
    private fun getPosition(provider:String): LocationBft? {

        locationManager.requestLocationUpdates(provider, 5000, 0F, object :
            LocationListener {
            override fun onLocationChanged(location: Location?) {
                if (location != null) {
                    locationPos = LocationBft(location?.longitude.toString(),location?.latitude.toString(),location?.altitude.toString(),
                        Date().toString(),getDeviceName(),"111",location.accuracy)
                    Log.i(TAG,"stared "+locationPos);
                }
            }
            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
                Log.i(TAG,"stared "+status);
            }
            override fun onProviderEnabled(provider: String?) {
                Log.i(TAG,"stared onProviderEnabled");
            }
            override fun onProviderDisabled(provider: String?) {
                Log.i(TAG,"stared onProviderDisabled");
            }

        }

        )

        val localGpsLocation = locationManager.getLastKnownLocation(provider)
        if (localGpsLocation != null)
            return LocationBft(localGpsLocation?.longitude.toString(),localGpsLocation?.latitude.toString(),localGpsLocation?.altitude.toString(),
                Date().toString(),getDeviceName(),"111",localGpsLocation.accuracy)
        else
            return locationPos
    }


    @SuppressLint("MissingPermission")
    private fun getLocation(): LocationBft? {
        var locationGps: LocationBft? = null
        var locationNetwork: LocationBft? = null
        var location: LocationBft?=null
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
            val intent = Intent(context, GpSIntentService::class.java).apply {
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
            val intent = Intent(context, GpSIntentService::class.java).apply {
                action = ACTION_BAZ
                putExtra(EXTRA_PARAM1, param1)
                putExtra(EXTRA_PARAM2, param2)
            }
            context.startService(intent)
        }
    }
}
