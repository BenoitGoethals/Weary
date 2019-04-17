package be.mil.weary

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import kotlinx.android.synthetic.main.activity_spot_report.*
import kotlinx.android.synthetic.main.activity_spot_report.view.*
import java.io.ByteArrayOutputStream
import java.util.*
import java.util.UUID.randomUUID



class SpotReportActivity : AppCompatActivity() {

    val CAMERA_REQUEST_CODE=0

    private lateinit var nancy:NancyBftService
  private  lateinit  var locationManager:LocationManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spot_report)
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        nancy= NancyBftService()
        buttonPicture.setOnClickListener{
            val callCameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if(callCameraIntent.resolveActivity(packageManager) !=null){
                startActivityForResult(callCameraIntent,CAMERA_REQUEST_CODE)
            }
        }

        buttonSend.setOnClickListener {
            var image:ByteArray=ByteArray(0)
            if(editTextIcident.text.length>3 ){
                if(photoimageView.drawable!=null){

                    val stream = ByteArrayOutputStream()
                    //  Bitmap image=((BitmapDrawable)imageView.getDrawable()).getBitmap(

                    photoimageView.photoimageView.drawable.toBitmap().compress(Bitmap.CompressFormat.PNG, 90, stream)
                     image = stream.toByteArray()
                }




                //val id:Int, val callSign:String, val BmsAssetId:String, val DateReported: Date, val locationBft:LocationGps,val Icident:String, val photo:ByteArray
                val uuid = UUID.randomUUID()

                var report:SpotReport= SpotReport( uuid.toString(),"1,1","1211",Date(),getLocation()!!,editTextIcident.text.toString(),
                    photo =image
                )
                //      OkHttpHandler(data.extras.get("data") as Bitmap).execute();
                nancy.postSpotReport(report);
            }
            else
            {
                Toast.makeText(this,"Fill in incident", Toast.LENGTH_SHORT).show()
            }



        }

    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            CAMERA_REQUEST_CODE ->{
                if(resultCode == Activity.RESULT_OK && data != null){
                    photoimageView.setImageBitmap(data.extras.get("data") as Bitmap)
                    val bitmap = data.extras.get("data") as Bitmap

                }
            }
            else -> Toast.makeText(this,"Unregconisted requestcode", Toast.LENGTH_SHORT).show()
        }
    }


    @SuppressLint("MissingPermission")
    private fun getPosition(provider:String): LocationGps? {


        val localGpsLocation = locationManager.getLastKnownLocation(provider)
        if (localGpsLocation != null)
            return LocationGps("1",localGpsLocation?.longitude,localGpsLocation?.latitude,localGpsLocation?.altitude,
                Date(),localGpsLocation?.speed,localGpsLocation.accuracy)
        else
            return null;
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

}
